/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import testingsystem.model.beans.AnswerLocale;

/**
 *
 * @author mirman
 */
public interface AnswerLocaleDAO {

    AnswerLocale getAnswerLocale(int answerId, int localeId);

    int insert(AnswerLocale answerLocale);

    void update(AnswerLocale answerLocale);

    void delete(AnswerLocale answerLocale);
}
