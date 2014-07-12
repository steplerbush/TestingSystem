/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import testingsystem.model.beans.Tutor;

/**
 *
 * @author mirman
 */
public interface TutorDAO {

    List<Tutor> getAllTutors();
    
    List<Tutor> getUncheckedTutors();

    int insert(Tutor tutor);

    void update(Tutor tutor);

    void delete(Tutor tutor);

    public Tutor getById(int id);

    public Tutor getByUserId(int userId);
}
