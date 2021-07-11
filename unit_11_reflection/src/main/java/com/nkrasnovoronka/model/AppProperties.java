package com.nkrasnovoronka.model;

import com.nkrasnovoronka.annotation.PropertyKey;

public class AppProperties {

    @PropertyKey("connections.limit")
    private Integer connectionLimit;

    @PropertyKey("name")
    private String name;

    @PropertyKey("url")
    private String url;

    @PropertyKey("open")
    private boolean open;

    @PropertyKey("password")
    private String password;

    @PropertyKey("role")
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
