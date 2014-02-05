/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import testingsystem.model.beans.Student;

/**
 *
 * @author mirman
 */
public interface StudentDAO {

    List<Student> getAllStudents();

    int insert(Student student);

    void update(Student student);

    void delete(Student student);

    public List<Student> getStudentsByGroupId(int id);
}
