/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import testingsystem.model.beans.Result;

/**
 *
 * @author mirman
 */
public interface ResultDAO {

    List<Result> getAllResults();

    int insert(Result result);

    void update(Result result);

    void delete(Result result);
}
