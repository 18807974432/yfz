package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

public class Custstate {
    private int custStateId;
    private String custStateName;

    public int getCustStateId() {
        return custStateId;
    }

    public void setCustStateId(int custStateId) {
        this.custStateId = custStateId;
    }

    public String getCustStateName() {
        return custStateName;
    }

    public void setCustStateName(String custStateName) {
        this.custStateName = custStateName;
    }
}
