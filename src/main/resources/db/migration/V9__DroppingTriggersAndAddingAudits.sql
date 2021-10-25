
ALTER VIEW viw_importances AS
select
    I.created,
    I.created_by,
    I.updated,
    I.updated_by,
    I.`ID` AS `ID`, 
    I.`Sort_order` AS `Sort_Order`, 
    I.`Name` AS `Name`
 from `tbl_importances` AS I;

ALTER VIEW viw_issues AS
select 
    I.created,
    I.created_by,
    I.updated,
    I.updated_by,
    I.`id` AS `id`, 
    I.`closed` AS `closed`, 
    I.`name` AS `name`, 
    I.`description` AS `description`, 
    I.`importance` AS `importance` 
from `tbl_issues` AS I;

ALTER VIEW viw_issues_steps AS
select 
    I.created,
    I.created_by,
    I.updated,
    I.updated_by,
    I.`id` AS `id`, 
    I.`issue_id` AS `issue_id`, 
    I.`responsible` AS `responsible`, 
    I.`step_id` AS `step_id`, 
    I.`comment` AS `comment`, 
    I.`completed` AS `completed` 
from `tbl_issues_steps` AS I;

ALTER VIEW viw_steps AS
select 
    I.created,
    I.created_by,
    I.updated,
    I.updated_by,
    I.`id` AS `id`, 
    I.`sort_order` AS `sort_order`, 
    I.`name` AS `name` 
from `tbl_steps` AS I;

ALTER VIEW viw_users AS
select
    I.created,
    I.created_by,
    I.updated,
    I.updated_by,
    I.`login` AS `login`, 
    I.`first_name` AS `first_name`, 
    I.`last_name` AS `last_name` 
from `tbl_users` AS I;

ALTER VIEW viw_users_credentials AS
select 
    I.created,
    I.created_by,
    I.updated,
    I.updated_by,
    I.`login` AS `login`, 
    I.`password` AS `password`, 
    I.`is_expired` AS `is_expired`, 
    I.`Last_Active` AS `Last_Active` 
from `tbl_users_credentials` AS I;