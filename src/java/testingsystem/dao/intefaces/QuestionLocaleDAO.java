/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import testingsystem.model.beans.QuestionLocale;

/**
 *
 * @author mirman
 */
public interface QuestionLocaleDAO {

    QuestionLocale getQuestionLocale(int questionId, int localeId);

    int insert(QuestionLocale questionLocale);

    void update(QuestionLocale questionLocale);

    void delete(QuestionLocale questionLocale);
}
