package com.ht.vo;

public class CheckVo {
    private int checkId;
    private String checkName;
    private float score;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public float getScore(float score) {
        return this.score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
