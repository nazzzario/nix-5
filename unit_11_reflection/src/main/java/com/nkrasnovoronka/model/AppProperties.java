package com.nkrasnovoronka.model;

import com.nkrasnovoronka.annotation.PropertyMapper;

public class AppProperties {

    @PropertyMapper("connections.limit")
    private Integer connectionLimit;

    @PropertyMapper("name")
    private String name;

    @PropertyMapper("url")
    private String url;

    @PropertyMapper("open")
    private boolean open;

    @PropertyMapper("password")
    private String password;

    @PropertyMapper("role")
    private Role role;

    @Override
    public String toString() {
        return "AppProperties{" +
                "connectionLimit=" + connectionLimit +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", open=" + open +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
