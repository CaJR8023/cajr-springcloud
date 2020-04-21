DROP PROCEDURE IF EXISTS schema_change;
DELIMITER //
CREATE PROCEDURE schema_change()
BEGIN
    DECLARE CurrentDatabase VARCHAR(100);
    SELECT DATABASE() INTO CurrentDatabase;

    IF NOT EXISTS(SELECT 1 FROM information_schema.STATISTICS WHERE TABLE_SCHEMA = CurrentDatabase AND TABLE_NAME = 'news' AND INDEX_NAME = 'index_module_id')
    then
        ALTER TABLE news ADD INDEX index_module_id(module_id);
    END IF;

    IF NOT EXISTS(SELECT 1 FROM information_schema.STATISTICS WHERE TABLE_SCHEMA = CurrentDatabase AND TABLE_NAME = 'news' AND INDEX_NAME = 'index_created_at')
    then
        ALTER TABLE news ADD INDEX index_created_at(created_at);
    END IF;

end //
DELIMITER ;
CALL schema_change();
DROP PROCEDURE IF EXISTS schema_change;