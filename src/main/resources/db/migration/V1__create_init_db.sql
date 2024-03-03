CREATE TABLE USERS (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE AUTHORITIES (
    username VARCHAR(50) REFERENCES USERS(username),
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES USERS(username),
    CONSTRAINT pk_authorities PRIMARY KEY (username, authority)
);

CREATE TABLE PLANT (
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(64) NOT NULL,
   description TEXT
);

CREATE TABLE QUESTION(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(127) NOT NULL,
    question_text VARCHAR(255) NOT NULL,
    plant_id INT,
    user_id VARCHAR(50),
    --CONSTRAINT fk_question_user FOREIGN KEY(user_id) REFERENCES USERS(username),
    CONSTRAINT fk_question_plant FOREIGN KEY(plant_id) REFERENCES PLANT(id)
);

CREATE TABLE ANSWER(
    id INT PRIMARY KEY AUTO_INCREMENT,
    answer_text VARCHAR(255),
    question_id INT,
    user_id VARCHAR(50),
    --CONSTRAINT fk_answer_user FOREIGN KEY(user_id) REFERENCES USERS(username),
    CONSTRAINT fk_answer_question FOREIGN KEY(question_id) REFERENCES QUESTION(id)
);

CREATE TABLE link (
    id INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(255)
);

CREATE TABLE document  (
     id INT PRIMARY KEY AUTO_INCREMENT,
     brand VARCHAR(20),
     origin VARCHAR(20),
     link_id INT,
     characteristics VARCHAR(30),

    CONSTRAINT fk_document_link FOREIGN KEY (link_id) REFERENCES  link(id)
);