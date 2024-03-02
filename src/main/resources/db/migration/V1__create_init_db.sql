CREATE TABLE USERS (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) REFERENCES USERS(username),
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES USERS(username),
    CONSTRAINT pk_authorities PRIMARY KEY (username, authority)
);

CREATE TABLE PLANT (
   id INT PRIMARY KEY,
   name VARCHAR(64) NOT NULL,
   description TEXT
);

CREATE TABLE QUESTION(
    id INT PRIMARY KEY,
    title VARCHAR(127) NOT NULL,
    question VARCHAR(255) NOT NULL,
    plant_id INT,
    user_id VARCHAR(50),
    CONSTRAINT fk_question_plant FOREIGN KEY(plant_id) REFERENCES PLANT(id),
    CONSTRAINT fk_question_user FOREIGN KEY(plant_id) REFERENCES USERS(username)
);

CREATE TABLE ANSWER(
    id INT PRIMARY KEY,
    answer VARCHAR(255),
    question_id INT,
    user_id VARCHAR(50),
    CONSTRAINT fk_answer_question FOREIGN KEY(question_id) REFERENCES QUESTION(id),
    CONSTRAINT fk_answer_user FOREIGN KEY(user_id) REFERENCES USERS(username)
);