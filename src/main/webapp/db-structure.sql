CREATE TABLE groups(
    id BIGSERIAL NOT NULL,
    title VARCHAR(128) NOT NULL UNIQUE,
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
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(256) NOT NULL,
    email VARCHAR(128) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    group_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (group_id) REFERENCES groups(id)
);
CREATE TABLE students_classes(
    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    rating INT CHECK (0 <= rating AND rating <= 100),
    CONSTRAINT student_class_pk PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (subject_id) REFERENCES subjects(id)
)


-- average_rank
-- SELECT *, RANK() OVER(ORDER BY points DESC) AS average_rank FROM student_group;
-- SELECT *, ROW_NUMBER() OVER(ORDER BY points DESC) AS average_rank FROM student_group
-- SELECT student_id, AVG(points) AS average_rank FROM student_group GROUP BY student_id