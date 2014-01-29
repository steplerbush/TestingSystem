/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import testingsystem.model.beans.Answer;

/**
 *
 * @author mirman
 */
public interface AnswerDAO {

    List<Answer> getAllAnswers();

    int insert(Answer answer);

    void update(Answer answer);

    void delete(Answer answer);
}
