CREATE VIEW ciw_Issues_Steps
AS
SELECT I.id
    , I.issue_id
    , I.responsible
    , S.name
    , I.comment
    , I.completed
FROM tbl_issues_steps AS I
INNER JOIN tbl_steps AS S
	ON I.step_id = S.id