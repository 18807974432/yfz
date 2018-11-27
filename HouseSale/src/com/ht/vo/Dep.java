package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Dep {
    private int depid;
    private String depname;
    private int parentid;
    private String chairman;
    private String description;
    private Dep deps;

    public String toString()
    {
        String str = "(" + this.depid + "," + this.depname + "," + this.parentid + "," + this.chairman + "," + this.description + ")";
        return str;
    }

    public void setParentid(int parentid) {

            this.parentid = parentid;
    }

    public Dep getDeps() {
        return deps;
    }

    public void setDeps(Dep deps) {
        this.deps = deps;
    }

    public int getDepid() {
        return depid;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
