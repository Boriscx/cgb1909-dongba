package com.tedu.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysUser extends AbstractObject {

    private static final long serialVersionUID = 7720291983270116110L;

    public static final String USERNAME = "username";

    private String password;
    private String salt;
    private String email;
    private String mobile;
    private Integer valid;
    private Integer deptId;

    public String getUsername() {
        return this.name;
    }

    public void setUsername(String username) {
        this.name = username;
    }
}
