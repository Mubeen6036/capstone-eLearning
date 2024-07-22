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

CREATE TABLE IF NOT EXISTS capstone_learning.user_videos (
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

-- Business Management Videos
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'Introduction to Business Management', 'http://example.com/business_management_1.mp4', cb47a7c1-0b3c-469e-822f-c4c41e150cb8, 1);
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'Advanced Business Strategies', 'http://example.com/business_management_2.mp4', cb47a7c1-0b3c-469e-822f-c4c41e150cb8, 2);
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'Business Management Case Studies', 'http://example.com/business_management_3.mp4', cb47a7c1-0b3c-469e-822f-c4c41e150cb8, 3);

-- Civil Engineering Videos
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'Introduction to Civil Engineering', 'http://example.com/civil_engineering_1.mp4', 4e15e2b9-2837-442b-908b-249d3e755265, 1);
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'Structural Engineering Basics', 'http://example.com/civil_engineering_2.mp4', 4e15e2b9-2837-442b-908b-249d3e755265, 2);
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'Civil Engineering Case Studies', 'http://example.com/civil_engineering_3.mp4', 4e15e2b9-2837-442b-908b-249d3e755265, 3);

-- Computer Science Videos
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'Introduction to Computer Science', 'http://example.com/computer_science_1.mp4', 116580e1-07fd-4661-a6b7-c7fd1d302622, 1);
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'Data Structures and Algorithms', 'http://example.com/computer_science_2.mp4', 116580e1-07fd-4661-a6b7-c7fd1d302622, 2);
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'Computer Science Case Studies', 'http://example.com/computer_science_3.mp4', 116580e1-07fd-4661-a6b7-c7fd1d302622, 3);

-- IT Management Videos
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'Introduction to IT Management', 'http://example.com/it_management_1.mp4', d16e2574-f8d1-488c-b243-6624054beea7, 1);
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'IT Project Management', 'http://example.com/it_management_2.mp4', d16e2574-f8d1-488c-b243-6624054beea7, 2);
INSERT INTO capstone_learning.videos (videoId, videoTitle, videoUrl, courseUuid, position) VALUES (uuid(), 'IT Management Case Studies', 'http://example.com/it_management_3.mp4', d16e2574-f8d1-488c-b243-6624054beea7, 3);

CREATE INDEX ON capstone_learning.videos(courseUuid);

CREATE TABLE capstone_learning.user_quizzes (
    username TEXT,
    courseId UUID,
    quizId UUID,
    completed BOOLEAN,
    PRIMARY KEY (username, courseId, quizId)
);

select * from capstone_learning.registrations;
delete from capstone_learning.user_videos where username='s.mubeenmusthafa@gmail.com';