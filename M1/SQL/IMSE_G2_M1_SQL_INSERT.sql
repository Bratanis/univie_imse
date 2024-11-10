-- Example input generated from ChatGPT:

-- @block
-- Filling the location table
INSERT INTO location (location_id, manager_name, address)
VALUES
(1, 'John Smith', '123 Main St'),
(2, 'Jane Doe', '456 Elm St'),
(3, 'Michael Johnson', '789 Oak St'),
(4, 'Emily Davis', '321 Pine St'),
(5, 'David Wilson', '654 Maple St');

-- @block
-- Filling the member table
INSERT INTO member (member_id, name, age, password, location_id)
VALUES
(1, 'Alice Brown', 25, 'password123', 1),
(2, 'Bob White', 30, 'securepass', 2),
(3, 'Charlie Green', 22, 'mypassword', 3),
(4, 'Diana Black', 28, 'pass789', 4),
(5, 'Ethan Blue', 35, 'bluepass', 5),
(6, 'Fiona Red', 27, 'red123', 1),
(7, 'George Yellow', 31, 'yellow456', 2),
(8, 'Hannah Gray', 26, 'gray321', 3),
(9, 'Ian Silver', 24, 'silver789', 4),
(10, 'Julia Gold', 29, 'gold999', 5);

-- @block
-- Filling the bank_details table
INSERT INTO bank_details (member_id, card_number, exp_date)
VALUES
(1, 1234567890123456, '12/2025'),
(2, 2345678901234567, '06/2024'),
(3, 3456789012345678, '11/2026'),
(4, 4567890123456789, '08/2023'),
(5, 5678901234567890, '01/2027');

-- @block
-- Filling the tutorial table
INSERT INTO tutorial (tutorial_id, name, muscle_group, difficulty_level, description)
VALUES
(1, 'Leg Workout', 'Legs', 'beginner', 'A simple leg workout for beginners.'),
(2, 'Arm Strength', 'Arms', 'intermediate', 'Intermediate level arm strength training.'),
(3, 'Core Training', 'Core', 'advanced', 'Advanced core training routine.'),
(4, 'Back Stretch', 'Back', 'beginner', 'A beginner-level back stretching routine.'),
(5, 'Shoulder Mobility', 'Shoulders', 'intermediate', 'Shoulder mobility exercises.'),
(6, 'Chest Pump', 'Chest', 'advanced', 'Advanced chest workout for muscle building.'),
(7, 'Full Body Warm-Up', 'Full Body', 'beginner', 'A full body warm-up for all levels.'),
(8, 'HIIT Cardio', 'Cardio', 'advanced', 'High-Intensity Interval Training for cardio.'),
(9, 'Glute Activation', 'Glutes', 'beginner', 'Beginner glute activation exercises.'),
(10, 'Upper Body Strength', 'Upper Body', 'intermediate', 'Upper body strength training.');

-- @block
-- Filling the saved table
INSERT INTO saved (member_id, tutorial_id)
VALUES
(1, 1),
(1, 3),
(2, 4),
(3, 2),
(4, 5),
(5, 6),
(6, 1),
(2, 7),
(8, 8),
(9, 9),
(10, 10),
(2, 1),
(3, 5),
(4, 6),
(5, 7);



-- Manually added example input


-- Filling the video_tutorial table

--@block
-- First add the 11th tutorial
INSERT INTO tutorial (name, muscle_group, difficulty_level, description)
VALUES ('Full-body awakening yoga', 'Full Body', 'advanced', 'This is an advanced fast-paced 30-minute yoga flow filled with heart, shoulder and hip-openers, lots of dolphins, as well as opportunities to go upside down. As this is a fast-paced class, I recommend pausing the video whenever you need to work on the transitions and poses. Feel free to do a short warm up of sun salutations before you begin class, and repeat this as many times as needed for you not to rely on the video anymore.')
;
-- @block
-- Then add the video tutorial elements
INSERT INTO video_tutorial(tutorial_id, duration, url)
VALUES (11, 32, 'https://www.youtube.com/watch?v=Sf24_x-Godo');
;

--@block
-- First add the 12th tutorial
INSERT INTO tutorial (name, muscle_group, difficulty_level, description)
VALUES ('Bench Press Proper Form', 'Chest', 'beginner', 'Bench Press is one of the best exercises in your arsenal; youve just gotta know how to do it right. In this video we show you how, going over technique, form and how to perform the perfect Bench Press!')
;
-- @block
-- Then add the video tutorial elements
INSERT INTO video_tutorial(tutorial_id, duration, url)
VALUES (12, 3, 'https://www.youtube.com/watch?v=gRVjAtPip0Y');
;

--@block
-- First add the 13th tutorial
INSERT INTO tutorial (name, muscle_group, difficulty_level, description)
VALUES ('Barbell Hack Squats', 'Quads', 'beginner', 'Barbell Hacksquat is one of the best exercises in your arsenal; youve just gotta know how to do it right. In this video we show you how, going over technique, form and how to perform the perfect Barbell Hacksquat!')
;
-- @block
-- Then add the video tutorial elements
INSERT INTO video_tutorial(tutorial_id, duration, url)
VALUES (13, 1.5, 'https://www.youtube.com/watch?v=EdtaJRBqwes');
;

--@block
-- First add the 14th tutorial
INSERT INTO tutorial (name, muscle_group, difficulty_level, description)
VALUES ('Preacher Curls', 'Biceps', 'intermediate', 'Preacher curls work well to hit the lower portion of the biceps muscle fibers... if you do them properly. Watch this trick for building bigger biceps.')
;
-- @block
-- Then add the video tutorial elements
INSERT INTO video_tutorial(tutorial_id, duration, url)
VALUES (14, 1.5, 'https://www.youtube.com/watch?v=vngli9UR6Hw');
;


--@block
-- Filling the recommend table
INSERT INTO recommend (recommender_tutorial_id, recommended_tutorial_id)
VALUES
(14, 12),
(14, 13),
(12, 13)
;


-- @block
-- Inserting some video tutorials in the saved table
INSERT INTO saved (member_id, tutorial_id)
VALUES
(1, 12),
(1, 13),
(1, 14),
(3, 11),
(4, 12),
(4, 13),
(6, 11),
(7, 11),
(8, 11),
(8, 12),
(9, 11),
(10, 11);

--@block
-- Insert some more saved tutorials for report
INSERT INTO saved (member_id, tutorial_id)
VALUES
(5, 1),
(4, 2),
(2, 2),
(9, 7),
(7, 7),
(8, 7),
(1, 7);