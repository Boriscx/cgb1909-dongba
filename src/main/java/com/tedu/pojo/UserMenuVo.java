package com.tedu.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Setter
@Getter

public class UserMenuVo implements Serializable {

    private static final long serialVersionUID = -2545186407205015795L;

    private Integer id;
    private String name;
    private String url;
    private List<UserMenuVo> childMenus;  // 子菜单集合

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMenuVo that = (UserMenuVo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url);
    }
}
