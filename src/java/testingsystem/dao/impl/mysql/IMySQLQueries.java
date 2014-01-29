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

    public final static String SELECT_ALL_ANSWERS = "SELECT * FROM answers";
    public final static String ID = "id";
    public final static String QUESTION_ID = "question_id";
    public final static String IS_CORRECT = "is_correct";
    public final static String ANSWER_TEXT = "answer_text";
    public final static String ANSWER_IMAGE_ADDRESS = "answer_image_addr";
    public final static String INSERT_INTO_ANSWERS = "INSERT INTO answers "
            + "(question_id, is_correct, answer_text, answer_image_addr) "
            + "VALUES (?, ?, ?, ?)";
    public final static String UPDATE_ANSWERS = "UPDATE answers SET "
            + "question_id = ?, is_correct = ?, answer_text = ?, "
            + "answer_image_addr = ? WHERE id = ?";
    public final static String DELETE_ANSWER = "DELETE FROM answers "
            + "WHERE id = ?";
    public final static String SELECT_ALL_GROUP_TESTS
            = "Select * from group_tests";
    public final static String GROUP_ID = "group_id";
    public final static String TEST_ID = "test_id";
    public final static String INSERT_INTO_GROUPTESTS
            = "Insert into group_tests (group_id, test_id) values (?, ?)";
    public final static String UPDATE_GROUPTESTS
            = "Update group_tests Set group_id = ?, test_id = ? where id = ?";
    public final static String DELETE_GROUPTEST
            = "DELETE FROM group_tests WHERE id = ?";
    public final static String SELECT_ALL_QUESTIONS = "Select * from questions";
    public final static String QUESTION_NUMBER = "q_number";
    public final static String DESCRIPTION = "description";
    public final static String WEIGHT = "weight";
    public final static String INSERT_INTO_QUESTIONS = "Insert into questions "
            + "(test_id, q_number, description, weight) values (?, ?, ?, ?)";
    public final static String UPDATE_QUESTIONS = "Update "
            + "questions Set test_id = ?, q_number = ?, "
            + "description = ?, weight = ? where id = ?";
    public final static String DELETE_QUESTION
            = "DELETE FROM questions WHERE id = ?";
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
    public final static String SELECT_ALL_STUDENTS = "Select * from student";
    public final static String USER_ID = "user_id";
    public final static String INSERT_INTO_STUDENTS
            = "Insert into student (group_id, user_id) values (?, ?)";
    public final static String UPDATE_STUDENTS = "Update student "
            + "Set group_id = ?, user_id = ? where id = ?";
    public final static String DELETE_STUDENTS
            = "DELETE FROM student WHERE id = ?";
    public final static String SELECT_ALL_STUDENTGROUPS
            = "Select * from student_group";
    public final static String GROUP_NAME = "group_name";
    public final static String GROUP_NUMBER = "group_number";
    public final static String INSERT_INTO_STUDENTGROUPS = "Insert into "
            + "student_group (group_name, group_number) values (?, ?)";
    public final static String UPDATE_STUDENTGROUPS 
            = "Update student_group Set group_name = ?, "
            + "group_number = ? where id = ?";
    public final static String DELETE_STUDENTGROUP
            = "DELETE FROM student_group WHERE id = ?";
    public final static String SELECT_ALL_TESTS = "Select * from tests";
    public final static String TITLE = "title";
    public final static String OPEN_TIME = "open_time";
    public final static String CLOSE_TIME = "close_time";
    public final static String DURATION = "duration";
    public final static String INSERT_INTO_TESTS = "Insert into tests "
            + "(title, description, open_time, close_time, "
            + "duration, tutor_id) values (?, ?, ?, ?, ?, ?)";
    public final static String UPDATE_TESTS = "Update tests "
            + "Set title = ?, description = ?, open_time = ?, "
            + "close_time = ?, duration = ?, tutor_id = ? where id = ?";
    public final static String DELETE_TESTS
            = "DELETE FROM tests WHERE id = ?";
    public final static String SELECT_ALL_TUTORS = "Select * from tutor";
    public final static String INFO = "info";
    public final static String TELEPHONE = "telephone";
    public final static String INSERT_INTO_TUTORS
            = "Insert into tutor (user_id, info, telephone) values (?, ?, ?)";
    public final static String UPDATE_TUTORS 
            = "Update tutor Set user_id = ?, info = ?, telephone = ? where id = ?";
    public final static String DELETE_TUTORS
            = "DELETE FROM tutor WHERE id = ?";
}
