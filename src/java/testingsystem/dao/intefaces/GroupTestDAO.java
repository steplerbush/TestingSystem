/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import testingsystem.model.beans.GroupTest;

/**
 *
 * @author mirman
 */
public interface GroupTestDAO {

    List<GroupTest> getAllGroupTests();

    int insert(GroupTest groupTest);

    void update(GroupTest groupTest);

    void delete(GroupTest groupTest);
}
