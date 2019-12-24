package com.tedu.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 含有id和name的menuNote
 */
@Getter
@Setter
public class MenuNote implements Serializable {
    private static final long serialVersionUID = -4529407568782523568L;
    private Integer id;
    private String name;
    private Integer parentId;
    private String parentName;
}
