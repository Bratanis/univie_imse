-- Should return "Bench Press Proper Form [...]" with default insert script
SELECT
    t.name AS tutorial_name,
    vt.url,
    MIN(m.age) AS min_age,
    MAX(m.age) AS max_age,
    COUNT(s.tutorial_id) AS times_saved
FROM
    member m
JOIN
    saved s ON m.member_id = s.member_id
JOIN
    tutorial t ON s.tutorial_id = t.tutorial_id
JOIN
    video_tutorial vt ON t.tutorial_id = vt.tutorial_id
WHERE
    t.difficulty_level = 'beginner'
GROUP BY
    t.name, vt.url
ORDER BY
    times_saved DESC
LIMIT 1;