CREATE TABLE tbl_importances (
importance_Sort_ID INT,
Name VARCHAR(30) NOT NULL UNIQUE,
created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
created_by VARCHAR(30) NULL,
updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_by VARCHAR(30) NULL,
PRIMARY KEY (importance_Sort_ID)
);

CREATE VIEW viw_importances
AS
    SELECT importance_Sort_ID, Name
    FROM tbl_importances;


INSERT INTO tbl_importances (importance_Sort_ID, Name)
VALUES(1, 'Critical')