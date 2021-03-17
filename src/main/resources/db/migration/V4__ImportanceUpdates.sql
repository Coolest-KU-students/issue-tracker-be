ALTER TABLE tbl_importances 
DROP PRIMARY KEY;

ALTER TABLE tbl_importances
ADD ID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE tbl_importances CHANGE importance_Sort_ID Sort_order INT NOT NULL;

ALTER VIEW viw_importances
AS 
    SELECT ID, Sort_Order, Name
    FROM tbl_importances;