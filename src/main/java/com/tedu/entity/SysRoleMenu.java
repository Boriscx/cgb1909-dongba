package com.tedu.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 3162951256253049158L;
    private Integer id;
    private Integer roleId;
    private Integer menuId;
}
