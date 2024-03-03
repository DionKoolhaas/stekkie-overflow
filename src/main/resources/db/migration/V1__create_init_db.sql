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
     title VARCHAR(255),
     -- de locatie die behoort tot dit document
     link_id INT,

    CONSTRAINT fk_document_link FOREIGN KEY (link_id) REFERENCES  link(id)
);

CREATE TABLE text (
    id INT PRIMARY KEY AUTO_INCREMENT,
    text VARCHAR(255) NOT NULL,
    css_query VARCHAR(52) NOT NULL
);

CREATE TABLE document_text (
    id INT PRIMARY KEY AUTO_INCREMENT,
    text_id INT,
    document_id INT,
    CONSTRAINT fk_document_phrase_phrase FOREIGN KEY (text_id) REFERENCES  text(id),
    CONSTRAINT fk_document_phrase_document FOREIGN KEY (document_id) REFERENCES  document(id)
);

-- links die in een document zijn gevonden
CREATE TABLE document_link (
  id INT PRIMARY KEY AUTO_INCREMENT,
  link_id INT,
  document_id INT,
  CONSTRAINT fk_document_link_link FOREIGN KEY (link_id) REFERENCES  link(id),
  CONSTRAINT fk_document_link_document FOREIGN KEY (document_id) REFERENCES  document(id)
);