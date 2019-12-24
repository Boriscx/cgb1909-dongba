package com.tedu.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class SysDept extends AbstractObject {

    private static final long serialVersionUID = -2711558996309118006L;

    private Integer parentId;
    private Integer sort;
    private String note;
    private String parentName;

}
