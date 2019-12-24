package com.tedu.entity;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysLog extends AbstractObject {

    private static final long serialVersionUID = -6910476240666123836L;

    private String operation;
    private String method;
    private String params;
    private Integer time;
    private String ip;

    public SysLog(String username, String operation, String method, String params, Long time, String ip) {
        this.name = username;
        this.operation = operation;
        this.method = method;
        this.params = params;
        this.time = Math.toIntExact(time);
        this.ip = ip;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public String getUsername() {
        return this.name;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "operation='" + operation + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", time=" + time +
                ", ip='" + ip + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", createdUser='" + createdUser + '\'' +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
