/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.impl.mysql;

/**
 *
 * @author mirman
 */
public interface IMySQLQueries {

    //answer
    public final static String SELECT_ALL_ANSWERS = "SELECT * FROM answers";
    public final static String ID = "id";
    public final static String QUESTION_ID = "question_id";
    public final static String IS_CORRECT = "is_correct";
    public final static String ANSWER_TEXT = "answer_text";
    public final static String INSERT_INTO_ANSWERS 
            = "INSERT INTO answers (question_id, is_correct) VALUES (?, ?)";
    public final static String UPDATE_ANSWERS = "UPDATE answers SET "
            + "question_id = ?, is_correct = ? WHERE id = ?";
    public final static String DELETE_ANSWER 
            = "DELETE FROM answers WHERE id = ?";

    //answerlocale
    public final static String ANSWER_ID = "answer_id";
    public final static String GET_ANSWERLOCALE 
            = "SELECT * FROM answerlocale where answer_id = ? and lang_id = ?";
    public final static String INSERT_INTO_ANSWERLOCALE = 
            "INSERT INTO answerlocale (answer_id, lang_id,"
            + " answer_text, image_src) VALUES (?, ?, ?, ?)";
    public final static String UPDATE_ANSWERLOCALE 
            = "UPDATE answerlocale SET answer_id = ?, lang_id = ?,"
            + " answer_text = ?, image_src = ? WHERE id = ?";
    public final static String DELETE_ANSWERLOCALE 
            = "DELETE FROM answerlocale WHERE id = ?";
    
    //group-test
    public final static String SELECT_ALL_GROUP_TESTS
            = "Select * from group_tests";
    public final static String GROUP_ID = "group_id";
    public final static String TEST_ID = "test_id";
    public final static String LANG_ID = "lang_id";
    public final static String INSERT_INTO_GROUPTESTS
            = "Insert into group_tests (group_id, test_id) values (?, ?)";
    public final static String UPDATE_GROUPTESTS
            = "Update group_tests Set group_id = ?, test_id = ? where id = ?";
    public final static String DELETE_GROUPTEST
            = "DELETE FROM group_tests WHERE id = ?";

    //question
    public final static String SELECT_ALL_QUESTIONS = "Select * from questions";
    public final static String QUESTION_NUMBER = "q_number";
    public final static String DESCRIPTION = "description";
    public final static String WEIGHT = "weight";
    public final static String INSERT_INTO_QUESTIONS = "Insert into questions "
            + "(test_id, q_number, weight) values (?, ?, ?)";
    public final static String UPDATE_QUESTIONS = "Update "
            + "questions Set test_id = ?, q_number = ?, weight = ? where id = ?";
    public final static String DELETE_QUESTION
            = "DELETE FROM questions WHERE id = ?";

    //questionlocale
    public final static String IMAGE_SRC = "image_src";
    public final static String GET_QUESTIONLOCALE 
            = "SELECT * FROM questionlocale "
            + "where question_id = ? and lang_id = ?";
    public final static String INSERT_INTO_QUESTIONLOCALE 
            = "INSERT INTO questionlocale (question_id, lang_id, "
            + "description, image_src) values (?, ?, ?, ?)";
    public final static String UPDATE_QUESTIONLOCALE 
            = "update questionlocale set question_id = ?, "
            + "lang_id = ?, description = ?, image_src = ? where id = ?";
    public final static String DELETE_QUESTIONLOCALE 
            = "DELETE FROM questionlocale WHERE id = ?";
    
    //result
    public final static String SELECT_ALL_RESULTS = "Select * from results";
    public final static String STUDENT_ID = "student_id";
    public final static String RESULT_DATETIME = "res_datetime";
    public final static String GRADE = "grade";
    public final static String INSERT_INTO_RESULTS = "Insert into results "
            + "(student_id, test_id, res_timestamp, grade) values (?, ?, ?, ?)";
    public final static String UPDATE_RESULTS = "Update results Set "
            + "student_id = ?, test_id = ?, res_timestamp = ?, "
            + "grade = ? where id = ?";
    public final static String DELETE_RESULT
            = "DELETE FROM results WHERE id = ?";

    //siterole
    public final static String SELECT_ALL_SITEROLES = "Select * from siterole";
    public final static String ROLE_NAME = "role_name";
    public final static String INSERT_INTO_SITEROLES
            = "Insert into siterole (role_name) values (?)";
    public final static String UPDATE_SITEROLES
            = "Update siterole Set role_name = ? where id = ?";
    public final static String DELETE_SITEROLE
            = "DELETE FROM siterole WHERE id = ?";
    public final static String GET_SITEROLE_BY_ID
            = "select * from siterole where id = ?";

    //siteuser
    public final static String SELECT_ALL_SITEUSERS = "Select * from siteuser";
    public final static String FIRST_NAME = "first_name";
    public final static String SECOND_NAME = "second_name";
    public final static String EMAIL = "email";
    public final static String LOGIN = "login";
    public final static String PASSWORD = "password";
    public final static String ROLE_ID = "role_id";
    public final static String INSERT_INTO_SITEUSERS = "Insert into siteuser "
            + "(first_name, second_name, email, login, password, role_id) "
            + "values (?, ?, ?, ?, ?, ?)";
    public final static String UPDATE_SITEUSERS = "Update siteuser "
            + "Set first_name = ?, second_name = ?, email = ?, "
            + "login = ?, password = ?, role_id = ? where id = ?";
    public final static String DELETE_SITEUSERS
            = "DELETE FROM siteuser WHERE id = ?";
    public final static String GET_SITEUSER_BY_USERNAME
            = "select * from siteuser where login = ?";
    public final static String GET_SITEUSER_BY_EMAIL
            = "select * from siteuser where email = ?";
    public final static String GET_SITEUSER_BY_ID = "select * from siteuser where id = ?";

    //student
    public final static String SELECT_ALL_STUDENTS = "Select * from student";
    public final static String USER_ID = "user_id";
    public final static String INSERT_INTO_STUDENTS
            = "Insert into student (group_id, user_id) values (?, ?)";
    public final static String UPDATE_STUDENTS = "Update student "
            + "Set group_id = ?, user_id = ? where id = ?";
    public final static String DELETE_STUDENTS
            = "DELETE FROM student WHERE id = ?";
    public final static String SELECT_STUDENTS_BY_GROUP_ID
            = "Select * from student where group_id = ?";

    //studentgroup
    public final static String SELECT_ALL_STUDENTGROUPS
            = "Select * from student_group";
    public final static String GET_STUDENTGROUP_BY_ID
            = "Select * from student_group where id = ?";
    public final static String GET_STUDENTGROUP_BY_NAME_NUMBER
            = "Select * from student_group where group_name = ? and group_number = ?";
    public final static String GROUP_NAME = "group_name";
    public final static String GROUP_NUMBER = "group_number";
    public final static String INSERT_INTO_STUDENTGROUPS = "Insert into "
            + "student_group (group_name, group_number) values (?, ?)";
    public final static String UPDATE_STUDENTGROUPS
            = "Update student_group Set group_name = ?, "
            + "group_number = ? where id = ?";
    public final static String DELETE_STUDENTGROUP
            = "DELETE FROM student_group WHERE id = ?";

    //test
    public final static String SELECT_ALL_TESTS = "Select * from tests";
    public final static String TITLE = "title";
    public final static String OPEN_TIME = "open_time";
    public final static String CLOSE_TIME = "close_time";
    public final static String DURATION = "duration";
    public final static String INSERT_INTO_TESTS = "Insert into tests "
            + "(open_time, close_time, "
            + "duration, tutor_id) values (?, ?, ?, ?)";
    public final static String UPDATE_TESTS = "Update tests "
            + "Set open_time = ?, close_time = ?, "
            + "duration = ?, tutor_id = ? where id = ?";
    public final static String DELETE_TESTS
            = "DELETE FROM tests WHERE id = ?";

    //testlocale
    public final static String GET_TESTLOCALE
            = "SELECT * FROM testlocale where test_id = ? and lang_id = ?";
    public final static String INSERT_INTO_TESTLOCALE = "INSERT INTO testlocale"
            + " (test_id, lang_id, title, description) VALUES (?, ?, ?, ?)";
    public final static String UPDATE_TESTLOCALE = "UPDATE testlocale Set "
            + "test_id = ?, lang_id = ?, title = ?, description = ? where id = ?";
    public final static String DELETE_TESTLOCALE
            = "DELETE FROM testlocale WHERE id = ?";

    //tutor
    public final static String SELECT_ALL_TUTORS = "Select * from tutor";
    public final static String SELECT_UNCHECKED_TUTORS = "Select * from tutor where is_approved = false";
    public final static String INFO = "info";
    public final static String TELEPHONE = "telephone";
    public final static String IS_APPROVED = "is_approved";
    public final static String INSERT_INTO_TUTORS
            = "Insert into tutor (user_id, info, telephone, is_approved) values (?, ?, ?, ?)";
    public final static String UPDATE_TUTORS
            = "Update tutor Set user_id = ?, info = ?, telephone = ?, is_approved = ? where id = ?";
    public final static String DELETE_TUTORS
            = "DELETE FROM tutor WHERE id = ?";
    public final static String GET_TUTOR_BY_ID = "Select * from tutor where id = ?";
}
