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
 ('default','1'),
 ('IAm', '31'),
 ('ZIA', '31');

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

DROP TABLE IF EXISTS lang;
create table lang
(
    id INT AUTO_INCREMENT not null,
    loccode varchar(7) not null,
    primary key (id)
)DEFAULT CHARSET=utf8;

insert into lang (loccode) values
('en-US'), ('ru-RU'), ('ua-UA');

DROP TABLE IF EXISTS tests;
create table tests
(
    id INT AUTO_INCREMENT not null,
    open_time TIMESTAMP,
    close_time TIMESTAMP,
    duration TIME,
    tutor_id int,
    primary key (id),
    FOREIGN KEY (tutor_id) REFERENCES tutor(id)
)DEFAULT CHARSET=utf8;

INSERT INTO tests (open_time, close_time, duration, tutor_id) VALUES 
 ('2013-06-04 22:00:00', '2014-06-04 22:00:00', '00:40:00', '1');

DROP TABLE IF EXISTS testlocale;
create table testlocale
(
    id INT AUTO_INCREMENT not null,
    test_id int not null,
    lang_id int not null,
    title varchar(150) not null,
    description TEXT,
    primary key (id),
    FOREIGN KEY (test_id) REFERENCES tests(id),
    FOREIGN KEY (lang_id) REFERENCES lang(id)
)DEFAULT CHARSET=utf8;

INSERT INTO testlocale (test_id, lang_id, title, description) VALUES 
 ('1','1','Tested test', 'description blablabla in english');


DROP TABLE IF EXISTS questions;
create table questions
(
    id INT AUTO_INCREMENT not null,
    test_id INT not null,
    q_number INT,
    weight int not null,
    partedrating boolean not null,         
    primary key (id),
    FOREIGN KEY (test_id) REFERENCES tests(id)
)DEFAULT CHARSET=utf8;

INSERT INTO questions (test_id, q_number, weight, partedrating) VALUES 
 ('1', '1', '2', '1'),
 ('1', '2', '3', '0'),
 ('1', '3', '3', '1');

DROP TABLE IF EXISTS questionlocale;
create table questionlocale
(
    id INT AUTO_INCREMENT not null,
    question_id int not null,
    lang_id int not null,
    description TEXT not null,
    image_src text,
    primary key (id),
    FOREIGN KEY (question_id) REFERENCES questions(id),
    FOREIGN KEY (lang_id) REFERENCES lang(id)
)DEFAULT CHARSET=utf8;

INSERT INTO questionlocale (question_id, lang_id, description, image_src) VALUES 
 ('1','1','Question 1 test 1', 'C:/blabla1.jpg'),
 ('2','1','Question 2 test 1', 'C:/blabla2.jpg'),
 ('3','1','Question 3 test 1', 'C:/blabla3.jpg');

DROP TABLE IF EXISTS answers;
create table answers
(
    id INT AUTO_INCREMENT not null,
    question_id INT not null,
    is_correct BOOLEAN not null,
    primary key (id),
    FOREIGN KEY (question_id) REFERENCES questions(id)
)DEFAULT CHARSET=utf8;

INSERT INTO answers (question_id, is_correct) VALUES 
 ('1', '1'),
 ('1', '0'),
 ('1', '0'),
 ('2', '1'),
 ('2', '1'),
 ('2', '0'),
 ('3', '1'),
 ('3', '0'),
 ('3', '0');

DROP TABLE IF EXISTS answerlocale;
create table answerlocale
(
    id INT AUTO_INCREMENT not null,
    answer_id int not null,
    lang_id int not null,
    answer_text TEXT,
    image_src TEXT,
    primary key (id),
    FOREIGN KEY (answer_id) REFERENCES answers(id),
    FOREIGN KEY (lang_id) REFERENCES lang(id)
)DEFAULT CHARSET=utf8;

INSERT INTO answerlocale (answer_id, lang_id, answer_text, image_src) VALUES 
 ('1','1','answer 1 correct', 'C:/blabla1.jpg'),
 ('2','1','answer 2 uncorrect', 'C:/blabla2.jpg'),
 ('3','1','answer 3 uncorrect', 'C:/blabla3.jpg'),
 ('4','1','answer 1 correct', 'C:/blabla1.jpg'),
 ('5','1','answer 2 correct', 'C:/blabla2.jpg'),
 ('6','1','answer 3 uncorrect', 'C:/blabla3.jpg'),
 ('7','1','answer 1 correct', 'C:/blabla1.jpg'),
 ('8','1','answer 2 uncorrect', 'C:/blabla2.jpg'),
 ('9','1','answer 3 uncorrect', 'C:/blabla3.jpg');


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