package com.tedu.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysUserRole extends AbstractObject {

    private static final long serialVersionUID = -718845817221395658L;

    private Integer userId;
    private Integer roleId;

}
