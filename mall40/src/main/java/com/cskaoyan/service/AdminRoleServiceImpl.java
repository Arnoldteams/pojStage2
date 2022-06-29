package com.cskaoyan.service;

import com.cskaoyan.bean.MarketPermission;
import com.cskaoyan.bean.MarketPermissionExample;
import com.cskaoyan.bean.MarketRole;
import com.cskaoyan.bean.MarketRoleExample;
import com.cskaoyan.bean.bo.AdminPermissionsBo;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.AdminOptionsVo;
import com.cskaoyan.bean.vo.PermissionChildVo;
import com.cskaoyan.bean.vo.PermissionGrandChildVo;
import com.cskaoyan.bean.vo.PermissionsVo;
import com.cskaoyan.mapper.MarketAdminMapper;
import com.cskaoyan.mapper.MarketPermissionMapper;
import com.cskaoyan.mapper.MarketRoleMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 系统管理-角色管理
 * @since 2022/06/25 20:14
 * @author 帅关
 */

@Service
@Transactional
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    MarketRoleMapper roleMapper;
    @Autowired
    MarketPermissionMapper permissionMapper;
    @Autowired
    MarketAdminMapper adminMapper;
    @Autowired
    RedisTemplate redisTemplate;
    private static final String ROLE_PREFIX = "ROLE:";


    /**
     * @description: 显示所有-搜索 角色
     * @parameter: [param, name]
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.MarketRole>
     * @author: 帅关
     * @createTime: 2022/6/25 21:42
     */
    @Override
    public CommonData<MarketRole> queryAllRoles(BaseParam param, String name) {
        // 配置分页工具
        PageHelper.startPage(param.getPage(), param.getLimit());

        MarketRoleExample example = new MarketRoleExample();
        MarketRoleExample.Criteria or = example.or();

        // 设置排序语句
        String OrderByClause = param.getSort() + " " + param.getOrder();
        example.setOrderByClause(OrderByClause);

        // 查询deleted = false的所有角色
        or.andDeletedEqualTo(false);

        // 角色用户名模糊查询
        if (name != null) {
            String sqlName = "%" + name + "%";
            or.andNameLike(sqlName);
        }

        List<MarketRole> roles = roleMapper.selectByExample(example);


        PageInfo<MarketRole> pageInfo = new PageInfo<>(roles);
        return CommonData.data(pageInfo);
    }


    /**
     * @description: 删除指定角色
     * 没有真正删除，只是将deleted标志位更改为true
     * @parameter: [role]
     * @return: int
     * @author: 帅关
     * @createTime: 2022/6/25 21:42
     */
    @Override
    public int deleteRole(MarketRole role) {
        String id = String.valueOf(role.getId());
        StringBuilder sb = new StringBuilder();
        // 获取所有管理员的角色信息
        String[] lists = adminMapper.selectAllRoleId();

        // 分解角色id成字符串
        for (String list : lists) {
            int length = list.length();
            sb.append(list, 1, length - 1).append(",");
        }
        String[] split = sb.toString().split(",");

        // 判断要删除的roleId是否有被
        for (String s : split) {
            if (s.equals(id)) {
                return 0;
            }
        }
        role.setDeleted(true);
        return roleMapper.updateByPrimaryKey(role);
    }

    /**
     * @description: 更新角色信息
     * @parameter: [role]
     * @return: int
     * @author: 帅关
     * @createTime: 2022/6/25 22:16
     */
    @Override
    @Async
    public void updateRole(MarketRole role) {
        roleMapper.updateByPrimaryKey(role);
    }

    /**
     * @description: 创建新角色
     * @parameter: [role]
     * @return: int
     * @author: 帅关
     * @createTime: 2022/6/25 23:04
     */
    @Override
    public int createRole(MarketRole role) {
        List<String> names = roleMapper.selectAllRoleName();
        if(names.contains(role.getName())){
            return 0;
        }
        return roleMapper.insertSelective(role);
    }

    /**
     * @description: 查询所有权限
     * @parameter: []
     * @return: com.cskaoyan.bean.vo.MarketSystemPermissionsVo
     * @author: 帅关
     * @createTime: 2022/6/26 13:24
     */
    @Override
    public List<PermissionsVo> queryAllRolePermissions() {
        List<PermissionsVo> permissions;
        // 先去redis里获取数据
        if ((permissions = (List<PermissionsVo>) redisTemplate.opsForValue().
                get(ROLE_PREFIX + "permissions")) != null) {
            return permissions;
        }
        // redis中没有数据，去mybatis中读取数据
        // 读第一层
        permissions = permissionMapper.queryAllPermissions();
        Iterator<PermissionsVo> permissionIterator = permissions.iterator();
        // 读第二层
        while (permissionIterator.hasNext()) {
            PermissionsVo permission = permissionIterator.next();
            Integer key = permission.getKey();
            List<PermissionChildVo> children = permissionMapper.selectPermissionChildByPermissionKey(key);
            Iterator<PermissionChildVo> childIterator = children.iterator();
            // 读第三层
            while (childIterator.hasNext()) {
                PermissionChildVo child = childIterator.next();
                Integer childKey = child.getKey();
                List<PermissionGrandChildVo> grandChild = permissionMapper.selectGrandChildByChildKey(childKey);
                child.setChildren(grandChild);
            }
            permission.setChildren(children);
        }
        // 将mybatis中读到的数据存到redis中，300s过期
        redisTemplate.opsForValue().
                set(ROLE_PREFIX + "permissions", permissions,300, TimeUnit.SECONDS);
        return permissions;
    }

    /**
     * @description: 获取角色权限
     * @parameter: [id]
     * @return: java.util.List<java.lang.String>
     * @author: 帅关
     * @createTime: 2022/6/27 21:57
     */
    public List<String> selectRoleApiById(Integer id) {
        List<String> apis;

        if (id == 1) {
            if (redisTemplate.opsForValue().get(ROLE_PREFIX + "apis") == null) {
                apis = permissionMapper.selectAllPermissionApi();
                redisTemplate.opsForValue().set(ROLE_PREFIX + "apis", apis);
            } else {
                apis = (List) redisTemplate.opsForValue().get(ROLE_PREFIX + "apis");
            }
        } else {
            apis = permissionMapper.selectPermissionApiById(id);
        }
        return apis;
    }

    /**
     * @description: 更新角色权限
     * @parameter: [adminPermissions]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/6/26 18:37
     */

    @Override
    @Async
    public void updateRolePermissions(AdminPermissionsBo adminPermissions) {

        // 生成sql语句
        MarketPermissionExample example = new MarketPermissionExample();

        // 获取数据库中指定id中所有的权限
        List<String> strings = permissionMapper.selectAllPermissionApiById(adminPermissions.getRoleId());

        // 获取前端传来的需要更新的权限
        List<String> permissions = adminPermissions.getPermissions();

        // 获取交集 --> 需要更新更新时间的权限
        ArrayList<String> union = new ArrayList<>(strings);
        union.retainAll(permissions);

        MarketPermissionExample.Criteria or = null;
        MarketPermission marketPermission = new MarketPermission();
        Date date = new Date();
        for (String string : union) {
            or = example.or();
            // 更新指定角色id的权限,更新指定角色的指定权限
            or.andRoleIdEqualTo(adminPermissions.getRoleId()).andPermissionEqualTo(string);
        }
        marketPermission.setUpdateTime(date);
        marketPermission.setDeleted(false);
        if (union.size() != 0) {
            permissionMapper.updateByExampleSelective(marketPermission, example);
        }

        // 获取需要删除的权限
        strings.removeAll(union);
        MarketPermission marketPermission2 = new MarketPermission();
        example = new MarketPermissionExample();
        for (String string : strings) {
            or = example.or();
            // 更新指定角色的指定权限
            or.andPermissionEqualTo(string).andRoleIdEqualTo(adminPermissions.getRoleId());
        }
        marketPermission2.setDeleted(true);
        marketPermission2.setUpdateTime(date);
        if (strings.size() != 0) {
            permissionMapper.updateByExampleSelective(marketPermission2, example);
        }

        //获取需要添加的权限
        HashSet<String> hashSet = new HashSet<>(permissions);
        permissions.removeAll(union);
        MarketPermission marketPermission3 = new MarketPermission();
        for (String string : permissions) {
            marketPermission3.setUpdateTime(date);
            marketPermission3.setAddTime(date);
            marketPermission3.setRoleId(adminPermissions.getRoleId());
            marketPermission3.setPermission(string);
            permissionMapper.insertSelective(marketPermission3);
        }

    }


    /**
     * @description: 获取所有角色id 和 name信息，用于管理员界面的小标签
     * @parameter: []
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.AdminOptionsVo>
     * @author: 帅关
     * @createTime: 2022/6/27 16:33
     */
    @Override
    public CommonData<AdminOptionsVo> queryAllRolesWithNoInfo() {
        List<AdminOptionsVo> list = permissionMapper.selectAllPermission();

        // 配置分页工具
        PageInfo<AdminOptionsVo> pageInfo = new PageInfo<>(list);
        PageHelper.startPage(1, pageInfo.getSize());
        return CommonData.data(pageInfo);
    }

    /**
     * @description: 根据角色id获取角色name
     * @parameter: [id]
     * @return: java.lang.String
     * @author: 帅关
     * @createTime: 2022/6/29 16:20
     */
    @Override
    public String selectRoleNameById(Integer id) {
        return permissionMapper.selectNameById(id);
    }
}
