-- pk_save is now a composite pk, member_id in this table is also new
CREATE TABLE saved (
  member_id INT NOT NULL, 
  tutorial_id INT NOT NULL,
  PRIMARY KEY (member_id, tutorial_id)
);

-- @block
-- auto increment added, attribute order changed to improve readability, attribute types changed
-- additionally CHECK added to make the difficulty_level more uniform across entries
CREATE TABLE tutorial (
  tutorial_id INT AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  muscle_group VARCHAR(64),
  difficulty_level VARCHAR(32) CHECK (difficulty_level IN ('beginner', 'intermediate', 'advanced')),
  description VARCHAR(2048),
  PRIMARY KEY (tutorial_id)
);

-- @block
-- everything changed
CREATE TABLE video_tutorial (
  tutorial_id INT NOT NULL,
  duration INT,
  url VARCHAR(2048),
  PRIMARY KEY (tutorial_id)
);

-- @block
-- this table was empty in the auto generated code
CREATE TABLE recommend (
    recommender_tutorial_id INT NOT NULL,
    recommended_tutorial_id INT NOT NULL,
    PRIMARY KEY (recommender_tutorial_id, recommended_tutorial_id),
    FOREIGN KEY (recommender_tutorial_id) REFERENCES video_tutorial(tutorial_id),
    FOREIGN KEY (recommended_tutorial_id) REFERENCES video_tutorial(tutorial_id)
);

-- @block
-- auto increment added, attribute order changed to improve readability, attribute types changed
CREATE TABLE member (
  member_id INT AUTO_INCREMENT, 
  name VARCHAR(32),
  age INT CHECK(age > 16),
  password VARCHAR(32),
  location_id INT,
  PRIMARY KEY (member_id)
);

-- @block
-- pk changed, foreign keys added
CREATE TABLE bank_details (
  member_id INT NOT NULL, 
  card_number VARCHAR(32),
  exp_date VARCHAR(32),
  PRIMARY KEY (member_id),
  FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

-- @block
-- auto increment added, attribute order changed to improve readability, attribute types changed
CREATE TABLE location (
  location_id INT AUTO_INCREMENT,
  manager_name VARCHAR(32),
  address VARCHAR(255),
  PRIMARY KEY (location_id)
);


-- Adding the foreign keys

-- @block
-- completely new
-- For the visit 1:m binary relation (adding the location pk to the member variables)
ALTER TABLE member ADD CONSTRAINT fk_visit_location FOREIGN KEY (location_id) REFERENCES location(location_id);

-- @block
-- names of attributes and tables changed
-- For the save m:m binary relation
ALTER TABLE saved ADD CONSTRAINT fk_save_tutorial FOREIGN KEY (tutorial_id) REFERENCES tutorial(tutorial_id);

-- @block
-- completely new
ALTER TABLE saved ADD CONSTRAINT fk_save_member FOREIGN KEY (member_id) REFERENCES member(member_id);

-- @block
-- completely new
-- For the weak entity bank_details
ALTER TABLE bank_details ADD CONSTRAINT fk_bank_details_member_id FOREIGN KEY (member_id)
REFERENCES member (member_id) ON DELETE CASCADE;

-- @block
-- completely new
-- For the recommend unary relation
ALTER TABLE recommend ADD CONSTRAINT fk_recommender_video FOREIGN KEY (recommender_tutorial_id) REFERENCES video_tutorial(tutorial_id);

-- @block
-- completely new
ALTER TABLE recommend ADD CONSTRAINT fk_recommended_video FOREIGN KEY (recommended_tutorial_id) REFERENCES video_tutorial(tutorial_id);

-- @block
-- completely new
ALTER TABLE video_tutorial ADD CONSTRAINT fk_video_tutorial FOREIGN KEY (tutorial_id) REFERENCES tutorial(tutorial_id);

DROP TABLE IF EXISTS bank_details;  
 DROP TABLE IF EXISTS saved;                               
 DROP TABLE IF EXISTS member; 
 DROP TABLE IF EXISTS location;                    
 DROP TABLE IF EXISTS recommend;                     
 DROP TABLE IF EXISTS video_tutorial;                     
 DROP TABLE IF EXISTS tutorial;                     
