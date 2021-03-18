ALTER TABLE tbl_users_credentials
ADD Last_Active TIMESTAMP NULL;

ALTER VIEW viw_users_credentials
AS
    SELECT login,password,is_expired,Last_Active
    FROM tbl_users_credentials;