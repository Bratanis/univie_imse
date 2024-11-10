/*  */-- Will use MySQL


-- Relations

-- @block
CREATE TABLE saved (
  member_id INT NOT NULL,
  tutorial_id INT NOT NULL,
  CONSTRAINT pk_save PRIMARY KEY (member_id, tutorial_id)
);


-- @block
CREATE TABLE recommend (
    recommender_tutorial_id INT NOT NULL,
    recommended_tutorial_id INT NOT NULL,
    CONSTRAINT pk_recommend PRIMARY KEY (recommender_tutorial_id, recommended_tutorial_id)
);


-- Entities

-- @block
CREATE TABLE member (
  member_id INT AUTO_INCREMENT,
  name VARCHAR(32),
  age INT CHECK(age > 16),
  password VARCHAR(32),
  location_id INT,
  CONSTRAINT pk_member PRIMARY KEY (member_id)
);

-- @block
CREATE TABLE bank_details (
  member_id INT NOT NULL, 
  card_number VARCHAR(19),
  exp_date VARCHAR(32),
  CONSTRAINT pk_bank_details PRIMARY KEY (member_id),
  FOREIGN KEY (member_id) REFERENCES member(member_id)
);

-- @block
CREATE TABLE location (
  location_id INT NOT NULL,
  manager_name VARCHAR(32),
  address VARCHAR(32),
  CONSTRAINT pk_location PRIMARY KEY (location_id)
);

-- @block
CREATE TABLE tutorial (
  tutorial_id INT AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  muscle_group VARCHAR(32),
  difficulty_level VARCHAR(32) CHECK (difficulty_level IN ('beginner', 'intermediate', 'advanced')),
  description VARCHAR(2048),
  CONSTRAINT pk_tutorial PRIMARY KEY (tutorial_id)
);

-- @block
CREATE TABLE video_tutorial (
  tutorial_id INT NOT NULL,
  duration INT,
  url VARCHAR(2083),
  CONSTRAINT pk_video_tutorial PRIMARY KEY (tutorial_id)
);


-- Adding the foreign keys

-- @block
-- For the visit 1:m binary relation (adding the location pk to the member variables)
ALTER TABLE member ADD CONSTRAINT fk_visit_location FOREIGN KEY (location_id) REFERENCES location(location_id);

-- @block
-- For the save m:m binary relation
ALTER TABLE saved ADD CONSTRAINT fk_save_tutorial FOREIGN KEY (tutorial_id) REFERENCES tutorial(tutorial_id);

-- @block
ALTER TABLE saved ADD CONSTRAINT fk_save_member FOREIGN KEY (member_id) REFERENCES member(member_id);

-- @block
-- For the recommend unary relation
ALTER TABLE recommend ADD CONSTRAINT fk_recommender_video FOREIGN KEY (recommender_tutorial_id) REFERENCES video_tutorial(tutorial_id);

-- @block
ALTER TABLE recommend ADD CONSTRAINT fk_recommended_video FOREIGN KEY (recommended_tutorial_id) REFERENCES video_tutorial(tutorial_id);

-- @block
ALTER TABLE video_tutorial ADD CONSTRAINT fk_video_tutorial FOREIGN KEY (tutorial_id) REFERENCES tutorial(tutorial_id);
