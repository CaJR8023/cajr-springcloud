DROP PROCEDURE IF EXISTS schema_change;
DELIMITER //
CREATE PROCEDURE schema_change()
BEGIN
    DECLARE  CurrentDatabase VARCHAR(100);
    SELECT DATABASE() INTO CurrentDatabase;
    IF NOT EXISTS(SELECT 1 FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = CurrentDatabase AND TABLE_NAME = 'news' AND COLUMN_NAME = 'news_data_sign')
    then
        ALTER TABLE news add news_data_sign varchar(150) not null default '';
    END IF;
end //
DELIMITER ;
CALL schema_change();
DROP PROCEDURE IF EXISTS schema_change;