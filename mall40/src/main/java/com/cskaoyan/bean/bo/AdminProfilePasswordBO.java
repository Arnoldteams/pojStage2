package com.cskaoyan.bean.bo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 修改密码VO
 *
 * @author sprinkle
 * @since 2022/06/30 21:20
 */
@Data
public class AdminProfilePasswordBO {

    private String oldPassword;
    @Length(min=6,max=16)
    private String newPassword;

    private String newPassword2;
}
