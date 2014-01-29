CREATE DATABASE IF NOT EXISTS testingsystemdb CHARACTER SET utf8 COLLATE utf8_general_ci;
USE testingsystemdb;

DROP TABLE IF EXISTS siterole;
create table siterole
(
    id INT AUTO_INCREMENT not null,
    role_name varchar(30) not null,
    primary key (id)
)DEFAULT CHARSET=utf8;

INSERT INTO siterole (role_name) VALUES 
 ('Admin'),
 ('Student'),
 ('Tutor');

DROP TABLE IF EXISTS siteuser;
create table siteuser
(
    id INT AUTO_INCREMENT not null,
    first_name varchar(100) not null,
    second_name varchar(100) not null,
    email varchar(100) unique not null,
    login varchar(40) unique not null,
    password varchar(40) not null,
    role_id INT not null,
    primary key (id),
    FOREIGN KEY (role_id) REFERENCES siterole(id)
)DEFAULT CHARSET=utf8;

INSERT INTO siteuser (first_name, second_name, email, login, password, role_id) VALUES 
 ('Evg', 'Vit', 'steplerbush@gmail.com', 'steplerbush', '555', '1'),
 ('testStudent', 'testStudent', 'step1@gmail.com', 'step1', '555', '2'),
 ('testTutor', 'testTutor', 'step2@gmail.com', 'step2', '555', '3');

DROP TABLE IF EXISTS tutor;
create table tutor
(
    id INT AUTO_INCREMENT not null,
    user_id int,
    telephone text,
    info TEXT,
    is_approved boolean,
    primary key (id),
    FOREIGN KEY (user_id) REFERENCES siteuser(id)
)DEFAULT CHARSET=utf8;

INSERT INTO tutor (user_id, info) VALUES 
 ('3', 'testTutorInfo');

DROP TABLE IF EXISTS student_group;
create table student_group
(
    id INT AUTO_INCREMENT not null,
    group_name varchar(60),
    group_number int,
    primary key (id)
)DEFAULT CHARSET=utf8;

INSERT INTO student_group (group_name, group_number) VALUES 
 ('IAm', '31'),
 ('default group', '0');

DROP TABLE IF EXISTS student;
create table student
(
    id INT AUTO_INCREMENT not null,
    group_id INT,
    user_id int,
    primary key (id),
    FOREIGN KEY (user_id) REFERENCES siteuser(id),
    FOREIGN KEY (group_id) REFERENCES student_group(id)
)DEFAULT CHARSET=utf8;

INSERT INTO student (group_id, user_id) VALUES 
 ('1', '2');

DROP TABLE IF EXISTS tests;
create table tests
(
    id INT AUTO_INCREMENT not null,
    title varchar(150) not null,
    description TEXT,
    open_time TIMESTAMP,
    close_time TIMESTAMP,
    duration TIME,
    tutor_id int,
    primary key (id),
    FOREIGN KEY (tutor_id) REFERENCES tutor(id)
)DEFAULT CHARSET=utf8;

INSERT INTO tests (title, description, open_time, close_time, duration, tutor_id) VALUES 
 ('Tested test', 'description blablabla', '2013-06-04 22:00:00', '2014-06-04 22:00:00', '00:40:00', '1');

DROP TABLE IF EXISTS questions;
create table questions
(
    id INT AUTO_INCREMENT not null,
    test_id INT not null,
    q_number INT, 
    description TEXT not null,
    weight int not null,
    primary key (id),
    FOREIGN KEY (test_id) REFERENCES tests(id)
)DEFAULT CHARSET=utf8;

INSERT INTO questions (test_id, q_number, description, weight) VALUES 
 ('1', '1', 'Question 1', '2'),
 ('1', '2', 'Question 2', '3'),
 ('1', '3', 'Question 3', '3');

DROP TABLE IF EXISTS answers;
create table answers
(
    id INT AUTO_INCREMENT not null,
    question_id INT not null,
    is_correct BOOLEAN not null,
    answer_text TEXT,
    answer_image_addr TEXT,
    primary key (id),
    FOREIGN KEY (question_id) REFERENCES questions(id)
)DEFAULT CHARSET=utf8;

INSERT INTO answers (question_id, is_correct, answer_text) VALUES 
 ('1', '1', 'blablablacorrect'),
 ('1', '0', 'blablabla'),
 ('1', '0', 'blablabla'),
 ('2', '0', 'blablabla'),
 ('2', '1', 'blablablacorrect'),
 ('2', '0', 'blablabla'),
 ('3', '1', 'blablablacorrect'),
 ('3', '0', 'blablabla'),
 ('3', '0', 'blablabla');

DROP TABLE IF EXISTS results;
create table results
(
    id INT AUTO_INCREMENT not null,
    student_id INT not null,
    test_id INT not null,
    res_timestamp Timestamp, 
    grade INT not null,
    primary key (id),
    FOREIGN KEY (test_id) REFERENCES tests(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
)DEFAULT CHARSET=utf8;

INSERT INTO results (student_id, test_id, res_timestamp, grade) VALUES 
 ('1', '1', '2014-01-04 22:11:00', '5');

DROP TABLE IF EXISTS group_tests;
create table group_tests
(
    id INT AUTO_INCREMENT not null,
    group_id INT not null,
    test_id INT not null,
    primary key (id),
    FOREIGN KEY (group_id) REFERENCES student_group(id),
    FOREIGN KEY (test_id) REFERENCES tests(id)
)DEFAULT CHARSET=utf8;

INSERT INTO group_tests (group_id, test_id) VALUES 
 ('1', '1');