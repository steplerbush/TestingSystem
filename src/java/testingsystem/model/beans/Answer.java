/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.model.beans;

import java.io.Serializable;

/**
 *
 * @author mirman
 */
public class Answer implements Serializable {

    private int id;
    private int questionId;
    private boolean correct;
    private AnswerLocale answerLocale;

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setIsCorrect(boolean correct) {
        this.correct = correct;
    }

    public AnswerLocale getAnswerLocale() {
        return answerLocale;
    }

    public void setAnswerLocale(AnswerLocale answerLocale) {
        this.answerLocale = answerLocale;
    }
}
