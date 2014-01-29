/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.model.beans;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author mirman
 */
public class Result implements Serializable {

    private int id;
    private int studentId;
    private int testId;
    private Timestamp resultTimestamp;
    private int grade;

    public Result() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Timestamp getResultTimestamp() {
        return resultTimestamp;
    }

    public void setResultTimestamp(Timestamp resultTimestamp) {
        this.resultTimestamp = resultTimestamp;
    }
}
