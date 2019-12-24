package com.tedu.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
public class SysMenu extends AbstractObject {

    private static final long serialVersionUID = -2888854243960578136L;
    private String url;
    private Integer type;
    private Integer sort;
    private String note;
    private Integer parentId;
    @NotBlank(message = "授权标识不能为空")
    private String permission;
    private String parentName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMenu sysMenu = (SysMenu) o;
        return Objects.equals(url, sysMenu.url) &&
                Objects.equals(type, sysMenu.type) &&
                Objects.equals(name, sysMenu.name) &&
                Objects.equals(permission, sysMenu.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, type, permission);
    }
}
