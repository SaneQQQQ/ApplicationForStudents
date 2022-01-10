DROP TABLE IF EXISTS groups CASCADE;
DROP TABLE IF EXISTS subjects CASCADE;
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS students_subjects CASCADE;

CREATE TABLE groups(
    id BIGSERIAL NOT NULL,
    title VARCHAR(5) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
);

CREATE TABLE subjects(
    id BIGSERIAL NOT NULL,
    title VARCHAR(128) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE students(
    id BIGSERIAL NOT NULL,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    group_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (group_id) REFERENCES groups(id)
);

CREATE TABLE students_subjects(
    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    mark INT CHECK (0 <= mark AND mark <= 100),
    CONSTRAINT student_subject_pk PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (subject_id) REFERENCES subjects(id)
);

INSERT INTO groups(id, title, created_at) VALUES (1, '111', '2020-04-05 12:30');
INSERT INTO groups(id, title, created_at) VALUES (2, '112', '2020-07-11 13:23');
INSERT INTO groups(id, title, created_at) VALUES (3, '211', '2019-05-09 11:15');
INSERT INTO groups(id, title, created_at) VALUES (4, '223', '2019-07-12 13:35');
INSERT INTO groups(id, title, created_at) VALUES (5, '223-A', '2019-12-05 21:04');
INSERT INTO groups(id, title, created_at) VALUES (6, '332-B', '2018-11-02 14:01');
INSERT INTO groups(id, title, created_at) VALUES (7, '356-C', '2018-01-04 22:26');
INSERT INTO groups(id, title, created_at) VALUES (8, '415', '2017-04-01 21:57');
INSERT INTO groups(id, title, created_at) VALUES (9, '546', '2016-08-01 18:56');

INSERT INTO subjects(id, title) VALUES (1, 'Mathematics');
INSERT INTO subjects(id, title) VALUES (2, 'Chemistry');
INSERT INTO subjects(id, title) VALUES (3, 'Science');
INSERT INTO subjects(id, title) VALUES (4, 'English');
INSERT INTO subjects(id, title) VALUES (5, 'Music');
INSERT INTO subjects(id, title) VALUES (6, 'Geography');
INSERT INTO subjects(id, title) VALUES (7, 'History');
INSERT INTO subjects(id, title) VALUES (8, 'Art');
INSERT INTO subjects(id, title) VALUES (9, 'Accounting');
INSERT INTO subjects(id, title) VALUES (10, 'Astronomy');
INSERT INTO subjects(id, title) VALUES (11, 'Biology');
INSERT INTO subjects(id, title) VALUES (12, 'Computer Science');
INSERT INTO subjects(id, title) VALUES (13, 'Economics');
INSERT INTO subjects(id, title) VALUES (14, 'French');
INSERT INTO subjects(id, title) VALUES (15, 'Management');
INSERT INTO subjects(id, title) VALUES (16, 'Marketing');
INSERT INTO subjects(id, title) VALUES (17, 'Philosophy');
INSERT INTO subjects(id, title) VALUES (18, 'Psychology');
INSERT INTO subjects(id, title) VALUES (19, 'Sociology');
INSERT INTO subjects(id, title) VALUES (20, 'Spanish');

INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (1, 'Bob', 'Roy', 'bobroy@gmail.com', '2020-04-04 14:50', 1);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (2, 'Jim', 'Jackson', 'jimjackson@gmail.com', '2020-04-03 10:30', 1);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (3, 'Tomas', 'Woody', 'tomaswoody@gmail.com', '2020-07-09 18:32', 1);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (4, 'Tony', 'Montana', 'tonymontana@gmail.com', '2020-07-11 12:20', 2);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (5, 'Alex', 'Turner', 'alexturner@gmail.com', '2019-03-07 18:23', 2);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (6, 'Mary', 'Jonson', 'maryjonson@gmail.com', '2019-03-05 19:10', 2);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (7, 'Darcie', 'Acosta', 'darcieacosta@gmail.com', '2019-03-05 19:10', 3);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (8, 'Cayson', 'Valencia', 'caysonvalencia@gmail.com', '2019-03-05 19:10', 3);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (9, 'Seren', 'Huffman', 'serenhuffman@gmail.com', '2019-03-05 19:10', 3);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (10, 'Lance', 'Rigby', 'lancerigby@gmail.com', '2019-03-05 19:10', 4);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (11, 'Elle-May', 'Weber', 'elle-mayweber@gmail.com', '2019-03-05 19:10', 4);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (12, 'Barnaby', 'Blundell', 'barnabyblundell@gmail.com', '2019-03-05 19:10', 4);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (13, 'Aaliya', 'Watson', 'aaliyawatson@gmail.com', '2019-03-05 19:10', 5);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (14, 'Poppie', 'Boyle', 'poppieboyle@gmail.com', '2019-03-05 19:10', 5);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (15, 'Georgia', 'Cooley', 'georgia_ooley@gmail.com', '2019-03-05 19:10', 5);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (16, 'Katya', 'Muir', 'katya_muir@gmail.com', '2019-03-05 19:10', 6);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (17, 'Piers', 'Dunne', 'piersdunne@gmail.com', '2019-03-05 19:10', 6);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (18, 'Mario', 'Plummer', 'mario@gmail.com', '2019-03-05 19:10', 6);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (19, 'Kylo', 'Carrillo', 'carrillo@gmail.com', '2019-03-05 19:10', 7);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (20, 'Masuma', 'Farrington', 'masumaaaaa@gmail.com', '2019-03-05 19:10', 7);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (21, 'Ismail', 'Harvey', 'ismail1243@gmail.com', '2019-03-05 19:10', 8);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (22, 'Myah', 'Kemp', 'kemp54@gmail.com', '2019-03-05 19:10', 8);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (23, 'Kaisha', 'Paine', 'kaishapaine@gmail.com', '2019-03-05 19:10', 8);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (24, 'Ramone', 'Devine', 'ramone_devine@gmail.com', '2019-03-05 19:10', 9);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (25, 'Saqlain', 'Moss', 'saqlain_moss@gmail.com', '2019-03-05 19:10', 9);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (1, 1, 60);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (1, 2, 80);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (1, 3, 99);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (1, 5, 99);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (2, 1, 75);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (2, 2, 34);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (2, 3, 65);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (2, 5, 89);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (3, 1, 45);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (3, 2, 90);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (3, 3, 67);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (3, 5, 98);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (4, 6, 78);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (4, 1, 90);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (4, 2, 96);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (4, 7, 87);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (5, 6, 67);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (5, 1, 58);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (5, 2, 75);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (5, 7, 60);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (6, 6, 89);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (6, 1, 78);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (6, 2, 98);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (6, 7, 86);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (7, 15, 56);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (7, 17, 48);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (7, 8, 76);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (7, 4, 76);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (8, 15, 57);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (8, 17, 49);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (8, 8, 87);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (8, 4, 72);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (9, 15, 90);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (9, 17, 88);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (9, 8, 76);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (9, 4, 87);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (10, 20, 90);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (10, 18, 88);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (10, 13, 76);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (10, 9, 87);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (11, 20, 67);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (11, 18, 76);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (11, 13, 85);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (11, 9, 69);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (12, 20, 60);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (12, 18, 84);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (12, 13, 63);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (12, 9, 90);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (13, 19, 55);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (13, 16, 47);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (13, 14, 58);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (13, 10, 69);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (14, 19, 65);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (14, 16, 46);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (14, 14, 69);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (14, 10, 89);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (15, 19, 98);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (15, 16, 96);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (15, 14, 93);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (15, 10, 90);


INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (16, 11, 76);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (16, 12, 37);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (16, 14, 75);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (16, 13, 68);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (17, 11, 74);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (17, 12, 95);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (17, 14, 73);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (17, 13, 94);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (18, 11, 73);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (18, 12, 84);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (18, 14, 84);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (18, 13, 73);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (19, 5, 83);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (19, 3, 84);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (19, 18, 86);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (19, 10, 87);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (20, 5, 78);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (20, 3, 48);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (20, 18, 85);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (20, 10, 85);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (21, 13, 100);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (21, 9, 96);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (21, 2, 98);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (21, 3, 95);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (22, 13, 67);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (22, 9, 60);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (22, 2, 57);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (22, 3, 58);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (23, 13, 48);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (23, 9, 49);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (23, 2, 46);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (23, 3, 60);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (24, 1, 68);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (24, 6, 59);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (24, 14, 85);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (24, 8, 90);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (25, 1, 85);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (25, 6, 58);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (25, 14, 73);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (25, 8, 90);