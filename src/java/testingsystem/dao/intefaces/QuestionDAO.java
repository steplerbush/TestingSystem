/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import testingsystem.model.beans.Question;

/**
 *
 * @author mirman
 */
public interface QuestionDAO {

    List<Question> getAllQuestions();

    int insert(Question question);

    void update(Question question);

    void delete(Question question);
}
