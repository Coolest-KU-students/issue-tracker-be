INSERT INTO tbl_users_credentials
(login, password, is_expired, created, created_by, updated, updated_by, Last_Active)
VALUES('SetupUser', '$2a$10$VIZTI71ZbpnNcyDgow1Y4ufCA1MvNMtinxL8vZMpFQlHx5Ud0snA2', 0, '2000-01-01 00:00:00.0', 'root', '2000-01-01 00:00:00.0', 'root', NULL);
;


INSERT INTO tbl_users
(login, first_name, last_name, created, created_by, updated, updated_by)
VALUES('SetupUser', 'Setup', 'User', '2000-01-01 00:00:00.0', 'root', '2000-01-01 00:00:00.0', 'root');
