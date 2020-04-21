DROP PROCEDURE IF EXISTS schema_change;
DELIMITER //
CREATE PROCEDURE schema_change()
BEGIN
    DECLARE  CurrentDatabase VARCHAR(100);
    SELECT DATABASE() INTO CurrentDatabase;

    IF NOT EXISTS(SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = CurrentDatabase AND TABLE_NAME = 'news' AND COLUMN_NAME = 'user_id')
    then
        ALTER TABLE news add `user_id` int(11) not null ;
    END IF;
    IF EXISTS(SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = CurrentDatabase AND TABLE_NAME = 'user_like_review' AND COLUMN_NAME = 'news_id')
    then
        ALTER TABLE user_like_review drop `news_id` ;
    END IF;
end //
DELIMITER ;
CALL schema_change();
DROP PROCEDURE IF EXISTS schema_change;