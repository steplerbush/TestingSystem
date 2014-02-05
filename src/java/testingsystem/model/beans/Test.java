/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.model.beans;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author mirman
 */
public class Test implements Serializable {

    private int id;
    private int tutorId;
    private Timestamp openTime;
    private Timestamp closeTime;
    private Time duration;
    private TestLocale locale;

    public Test() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public Timestamp getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Timestamp openTime) {
        this.openTime = openTime;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public TestLocale getLocale() {
        return locale;
    }

    public void setLocale(TestLocale locale) {
        this.locale = locale;
    }
}
