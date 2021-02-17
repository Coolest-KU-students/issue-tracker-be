CREATE FUNCTION fnc_user_login ()
RETURNS varchar(30)
RETURN LEFT(SYSTEM_USER(), locate('@', SYSTEM_USER())-1);

CREATE TABLE tbl_users_credentials (
login VARCHAR(30) NOT NULL PRIMARY KEY,
password VARCHAR(254) NOT NULL,
is_expired BOOLEAN NOT NULL DEFAULT 0,
created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
created_by VARCHAR(30) NOT NULL,
updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_by VARCHAR(30) NOT NULL
);

CREATE TRIGGER itr_users_credentials
  BEFORE INSERT ON tbl_users_credentials
  FOR EACH ROW
  SET new.created_by = fnc_user_login(), new.updated_by = fnc_user_login();

CREATE TRIGGER utr_users_credentials
  BEFORE UPDATE ON tbl_users_credentials
  FOR EACH ROW
  SET new.updated_by = fnc_user_login();

CREATE VIEW viw_users_credentials
AS
    SELECT login,password,is_expired
    FROM tbl_users_credentials;

CREATE TABLE tbl_users (
login VARCHAR(30) NOT NULL,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
created_by VARCHAR(30) NOT NULL,
updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_by VARCHAR(30) NOT NULL,
PRIMARY KEY (login),
FOREIGN KEY (login) REFERENCES tbl_Users_Credentials(login)
);

ALTER TABLE tbl_users
ADD COLUMN full_name VARCHAR(60)
GENERATED ALWAYS AS (`first_name` + " " + `last_name`) VIRTUAL;

CREATE TRIGGER itr_users
  BEFORE INSERT ON tbl_users
  FOR EACH ROW
  SET new.created_by = fnc_user_login(), new.updated_by = fnc_user_login();

CREATE TRIGGER utr_users
  BEFORE UPDATE ON tbl_users
  FOR EACH ROW
  SET new.updated_by = fnc_user_login();

CREATE VIEW viw_users
AS
    SELECT login, first_name, last_name, full_name
    FROM tbl_users;

CREATE TABLE tbl_steps (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
sort_order INT(6) UNSIGNED NOT NULL,
name VARCHAR(30) NOT NULL,
created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
created_by VARCHAR(30) NOT NULL,
updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_by VARCHAR(30) NOT NULL
);

CREATE TRIGGER itr_steps
  BEFORE INSERT ON tbl_steps
  FOR EACH ROW
  SET new.created_by = fnc_user_login(), new.updated_by = fnc_user_login();

CREATE TRIGGER utr_steps
  BEFORE UPDATE ON tbl_steps
  FOR EACH ROW
  SET new.updated_by = fnc_user_login();

CREATE VIEW viw_steps
AS
    SELECT id, sort_order, "name"
    FROM tbl_steps;

CREATE TABLE tbl_issues (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
closed TIMESTAMP NULL DEFAULT NULL,
name VARCHAR(30) NOT NULL,
description VARCHAR(254) NULL DEFAULT NULL,
importance INT(6) UNSIGNED NOT NULL,
created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
created_by VARCHAR(30) NOT NULL,
updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_by VARCHAR(30) NOT NULL
);

CREATE TRIGGER itr_issues
  BEFORE INSERT ON tbl_issues
  FOR EACH ROW
  SET new.created_by = fnc_user_login(), new.updated_by = fnc_user_login();

CREATE TRIGGER utr_issues
  BEFORE UPDATE ON tbl_issues
  FOR EACH ROW
  SET new.updated_by = fnc_user_login();

CREATE VIEW viw_issues
AS
    SELECT id, closed, "name", description, importance
    FROM tbl_issues;

CREATE TABLE tbl_issues_steps (
issue_id INT(6) UNSIGNED NOT NULL,
responsible VARCHAR(30) NOT NULL,
step_id INT(6) UNSIGNED NOT NULL,
comment VARCHAR(254),
completed TIMESTAMP NULL DEFAULT NULL,
created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
created_by VARCHAR(30) NOT NULL,
updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_by VARCHAR(30) NOT NULL,
PRIMARY KEY (issue_id, responsible, step_id),
FOREIGN KEY (issue_id) REFERENCES tbl_Issues(id) ON DELETE CASCADE,
FOREIGN KEY (responsible) REFERENCES tbl_Users_Credentials(login),
FOREIGN KEY (step_id) REFERENCES tbl_Steps(id)
);

CREATE TRIGGER itr_issues_steps
  BEFORE INSERT ON tbl_issues_steps
  FOR EACH ROW
  SET new.created_by = fnc_user_login(), new.updated_by = fnc_user_login();

CREATE TRIGGER utr_issues_steps
  BEFORE UPDATE ON tbl_issues_steps
  FOR EACH ROW
  SET new.updated_by = fnc_user_login();

CREATE VIEW viw_issues_steps
AS
    SELECT issue_id, responsible, step_id, comment, completed
    FROM tbl_issues_steps;
