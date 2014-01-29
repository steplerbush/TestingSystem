/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.model.beans;

import testingsystem.model.beans.Answer;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mirman
 */
public class Question implements Serializable {

    private int id;
    private int testId;
    private int number;
    private String description;
    private int weight;
    private List<Answer> options;

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Answer> getOptions() {
        return options;
    }

    public void setOptions(List<Answer> options) {
        this.options = options;
    }
}
