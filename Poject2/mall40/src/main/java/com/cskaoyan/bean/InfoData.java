package com.cskaoyan.bean;

import lombok.Data;

import java.util.List;

@Data
public class InfoData {
    private String name;
    private String avatar;
    private List<String> roles;
    private List<String> perms;
}
