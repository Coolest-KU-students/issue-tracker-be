CREATE VIEW ciw_users
AS
SELECT U.created
    , U.created_by
    , U.updated
    , U.updated_by
    , U.login
    , U.first_name
    , U.last_name
    , UC.is_expired 
FROM tbl_users AS U
INNER JOIN tbl_users_credentials AS UC
	ON U.Login = UC.Login