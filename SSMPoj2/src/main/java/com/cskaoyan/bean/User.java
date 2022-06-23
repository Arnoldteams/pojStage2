package com.cskaoyan.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author Rain.k
 * @description:
 * @date 2022/6/10 17:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String nickname;
    private String pwd;
    private String email;
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String phone;
    private String recipient;
}
