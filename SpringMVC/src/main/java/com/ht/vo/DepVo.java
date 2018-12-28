package com.ht.vo;

import java.util.List;

public class DepVo {
    private int depId;
    private String depName;
    private List<UsersVo> usersList;

    public List<UsersVo> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UsersVo> usersList) {
        this.usersList = usersList;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
