/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.model.beans;

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
    private int weight;
    private List<Answer> options;
    private QuestionLocale questionLocale;

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

    public QuestionLocale getQuestionLocale() {
        return questionLocale;
    }

    public void setQuestionLocale(QuestionLocale questionLocale) {
        this.questionLocale = questionLocale;
    }
}
