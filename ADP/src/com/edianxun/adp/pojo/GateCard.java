package com.edianxun.adp.pojo;

import javax.persistence.*;

/**
 * Created by yu on 2017/6/5.
 */
@Table(name = "gate_card")
@Entity
public class GateCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gateid;
    private int managerId;
    private String name;
    @Column(name = "go_time")
    private int goTime;
    @Column(name = "go_status")
    private int goStatus;
    @Column(name = "out_time")
    private int outTime;
    @Column(name = "out_status")
    private int outStatus;
    private int day;

    public int getGateid() {
        return gateid;
    }

    public void setGateid(int gateid) {
        this.gateid = gateid;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoTime() {
        return goTime;
    }

    public void setGoTime(int goTime) {
        this.goTime = goTime;
    }

    public int getGoStatus() {
        return goStatus;
    }

    public void setGoStatus(int goStatus) {
        this.goStatus = goStatus;
    }

    public int getOutTime() {
        return outTime;
    }

    public void setOutTime(int outTime) {
        this.outTime = outTime;
    }

    public int getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(int outStatus) {
        this.outStatus = outStatus;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public GateCard() {
    }
}
