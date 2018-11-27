package com.ht.vo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class SaleState {
    private int saleId;
    private String saleName;
    private String bgcolor;

    @Id
    @Column(name = "saleId")
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    @Basic
    @Column(name = "saleName")
    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    @Basic
    @Column(name = "bgcolor")
    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleState saleState = (SaleState) o;
        return saleId == saleState.saleId &&
                Objects.equals(saleName, saleState.saleName) &&
                Objects.equals(bgcolor, saleState.bgcolor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(saleId, saleName, bgcolor);
    }
}
