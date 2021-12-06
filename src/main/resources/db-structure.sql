DROP TABLE IF EXISTS groups CASCADE;
DROP TABLE IF EXISTS subjects CASCADE;
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS students_subjects CASCADE;

CREATE TABLE groups(
    id BIGSERIAL NOT NULL,
    title VARCHAR(16) NOT NULL UNIQUE,
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

INSERT INTO groups(id, title, created_at) VALUES (1, '111', '1996-04-01 12:30');
INSERT INTO groups(id, title, created_at) VALUES (2, '112', '2002-07-11 18:23');
INSERT INTO groups(id, title, created_at) VALUES (3, '113', '2014-03-09 21:35');
INSERT INTO groups(id, title, created_at) VALUES (4, '114', '2021-02-04 01:50');



