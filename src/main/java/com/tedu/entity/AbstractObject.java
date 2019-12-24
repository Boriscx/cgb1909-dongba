package com.tedu.entity;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class AbstractObject implements Serializable {
    private static final long serialVersionUID = 8500980471846022232L;
    protected Integer id;
    @NotBlank(message = "名称/名字不能为空")
    protected String name;
    protected String createdUser;
    protected String modifiedUser;
    protected Date createdTime;
    protected Date modifiedTime;
}
