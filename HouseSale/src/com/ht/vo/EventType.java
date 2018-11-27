package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

public class EventType {
    private int eventtypeId;
    private String eventtypename;

    public int getEventtypeId() {
        return eventtypeId;
    }

    public void setEventtypeId(int eventtypeId) {
        this.eventtypeId = eventtypeId;
    }

    public String getEventtypename() {
        return eventtypename;
    }

    public void setEventtypename(String eventtypename) {
        this.eventtypename = eventtypename;
    }

}
