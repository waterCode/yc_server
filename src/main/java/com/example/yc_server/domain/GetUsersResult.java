package com.example.yc_server.domain;

import java.util.List;

public class GetUsersResult extends BaseResult {
    List<SysUser> users;

    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }
}
