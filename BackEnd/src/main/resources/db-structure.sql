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
INSERT INTO groups(id, title, created_at) VALUES (3, '211', '2019-03-09 21:35');

INSERT INTO subjects(id, title) VALUES (1, 'Math');
INSERT INTO subjects(id, title) VALUES (2, 'Chemistry');
INSERT INTO subjects(id, title) VALUES (3, 'Science');
INSERT INTO subjects(id, title) VALUES (4, 'English');
INSERT INTO subjects(id, title) VALUES (5, 'Music');
INSERT INTO subjects(id, title) VALUES (6, 'Geography');
INSERT INTO subjects(id, title) VALUES (7, 'History');
INSERT INTO subjects(id, title) VALUES (8, 'Art');

INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (1, 'Bob', 'Roy', 'bobroy@gmail.com', '2020-04-04 14:50', 1);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (2, 'Jim', 'Jackson', 'jimjackson@gmail.com', '2020-04-03 10:30', 1);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (3, 'Tomas', 'Woody', 'tomaswoody@gmail.com', '2020-07-09 18:32', 2);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (4, 'Tony', 'Montana', 'tonymontana@gmail.com', '2020-07-11 12:20', 2);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (5, 'Alex', 'Turner', 'alexturner@gmail.com', '2019-03-07 18:23', 3);
INSERT INTO students(id, first_name, last_name, email, created_at, group_id) VALUES (6, 'Mary', 'Jonson', 'maryjonson@gmail.com', '2019-03-05 19:10', 3);

INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (1, 1, 60);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (1, 2, 80);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (1, 3, 99);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (2, 1, 75);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (2, 2, 34);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (2, 3, 65);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (3, 4, 89);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (3, 5, 45);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (3, 6, 90);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (4, 4, 67);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (4, 5, 98);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (4, 6, 78);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (5, 1, 90);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (5, 2, 96);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (5, 7, 87);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (5, 8, 67);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (6, 1, 58);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (6, 2, 75);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (6, 7, 60);
INSERT INTO students_subjects(student_id, subject_id, mark) VALUES (6, 8, 89);


