package com.ht.vo;

import sun.plugin2.message.Serializer;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

public class Users{
    private int userid;
    private String username;
    private String password;
    private String password1;
    private String password2;
    private int depid;
    private String jobname;
    private String mobile;
    private String email;
    private String qqcode;
    private String addr;
    private int status;
    private String demo;
    private Dep deps;


    public String toString()
    {
        String str = "(" + this.userid + "," + this.username + "," + this.depid + "," + this.jobname + "," + this.mobile + ",";
        str = str + this.email + "," + this.qqcode + "," + this.addr + "," + this.status + "," + this.demo + ")";
        return str;
    }

    public int getDepid() {
        return depid;
    }

    public int getStatus() {
        return status;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private List<AccModule> right;

    public List<AccModule> getRight() {
        return right;
    }

    public void setRight(List<AccModule> right) {
        this.right = right;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Dep getDeps() {
        return deps;
    }

    public void setDeps(Dep deps) {
        this.deps = deps;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqcode() {
        return qqcode;
    }

    public void setQqcode(String qqcode) {
        this.qqcode = qqcode;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

}
