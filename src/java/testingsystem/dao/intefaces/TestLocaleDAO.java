/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import testingsystem.model.beans.TestLocale;

/**
 *
 * @author mirman
 */
public interface TestLocaleDAO {

    TestLocale getTestLocale(int testId, int localeId);

    int insert(TestLocale testLocale);

    void update(TestLocale testLocale);

    void delete(TestLocale testLocale);
}
