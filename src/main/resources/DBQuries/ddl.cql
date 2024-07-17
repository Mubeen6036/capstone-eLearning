CREATE TABLE security.users (
    gmail text PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    mobile_num TEXT,
    password TEXT,
    role_names set<text>
) WITH comment='users table';

CREATE TABLE IF NOT EXISTS security.courses (
    courseId UUID PRIMARY KEY,
    courseName TEXT,
    available BOOLEAN
);

CREATE TABLE IF NOT EXISTS security.registrations (
    username TEXT,
    courseId UUID,
    PRIMARY KEY (username, courseId)
);

CREATE TABLE IF NOT EXISTS security.videos (
    videoId UUID PRIMARY KEY,
    videoTitle TEXT,
    videoUrl TEXT,
    courseUuid UUID,
    position INT
);

CREATE TABLE IF NOT EXISTS security.user_videos (
    username TEXT,
    videoId UUID,
    watched BOOLEAN,
    progress INT,
    PRIMARY KEY (username, videoId)
);

INSERT INTO security.courses (courseId, courseName, available)
VALUES (uuid(), 'Computer Science', true);

INSERT INTO security.courses (courseId, courseName, available)
VALUES (uuid(), 'Business Management', true);

INSERT INTO security.courses (courseId, courseName, available)
VALUES (uuid(), 'Civil Engineering', true);

INSERT INTO security.courses (courseId, courseName, available)
VALUES (uuid(), 'IT Management', true);

CREATE INDEX ON capstone_learning.courses(available);

