package com.tedu.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class SysRole extends AbstractObject {
    private static final long serialVersionUID = -1194786435901451855L;
    private String note;
    private List<SysRoleMenu> sysRoleMenus;
    private List<Integer> menuIds;
}
