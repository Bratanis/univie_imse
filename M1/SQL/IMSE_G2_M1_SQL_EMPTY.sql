-- @block
TRUNCATE TABLE saved;


-- @block
TRUNCATE TABLE recommend;

-- @block
TRUNCATE TABLE bank_details;

-- @block
DELETE FROM member;
ALTER TABLE member AUTO_INCREMENT = 1;


-- @block
DELETE FROM location;
ALTER TABLE location AUTO_INCREMENT = 1;

-- @block
DELETE FROM video_tutorial;

-- @block
DELETE FROM tutorial;
ALTER TABLE tutorial AUTO_INCREMENT = 1;

