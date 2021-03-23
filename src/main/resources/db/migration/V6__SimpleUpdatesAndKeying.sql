ALTER TABLE `tbl_issues` 
ADD CONSTRAINT `con_steps_importance` 
FOREIGN KEY (`importance`) REFERENCES `tbl_importances`(`ID`) 
ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `tbl_steps` 
ADD UNIQUE `uni_steps_name` (`name`);