/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import java.util.Map;

/**
 *
 * @author mirman
 */
public interface LocaleDAO {

    Map<Integer, String> getAllLocales();

    int insert(String locale);
}
