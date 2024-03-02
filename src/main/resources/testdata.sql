-- TODO: verplaats naar /src/test/resources en
--  zorg dat target/test-classes wordt toegevoegd aan classpath indien spring boot opstart
--  zodat de testdata niet mee komt in de uiteindelijke application package

INSERT INTO PLANT (id, name, description) VALUES (1, 'Monstera', 'Ook wel de gatenplant genoemd');

INSERT INTO QUESTION(id, title, question_text, plant_id, user_id) VALUES (1, 'Wat te doen tegen bladluis?', 'Mijn monstera zit helemaal onder de bladluis. Ik heb middeltjes geprobeerd die ik bij de plantenshop had gekocht maar mocht niet baten. Heeft iemand een idee?',1, 'testgebruiker');

INSERT INTO ANSWER(id, answer_text, question_id, user_id) VALUES (1, 'Ik heb een mengsel van water, afwasmiddel en spiritus gebruikt. Werkt perfect!', 1, 'testgebruiker2' )