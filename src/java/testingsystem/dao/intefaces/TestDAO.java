/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import testingsystem.model.beans.Test;

/**
 *
 * @author mirman
 */
public interface TestDAO {

    List<Test> getAllTests();

    int insert(Test test);

    void update(Test test);

    void delete(Test test);
}
