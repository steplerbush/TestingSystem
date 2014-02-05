/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import testingsystem.model.beans.StudentGroup;

/**
 *
 * @author mirman
 */
public interface StudentGroupDAO {

    List<StudentGroup> getAllStudentGroups();

    int insert(StudentGroup studentGroup);

    void update(StudentGroup studentGroup);

    void delete(StudentGroup studentGroup);

    public StudentGroup getStudentGroup(String name, int number);

    public StudentGroup getStudentGroup(int id);
}
