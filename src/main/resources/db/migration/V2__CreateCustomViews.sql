CREATE VIEW ciw_Issues 
AS
SELECT I.ID
    , I.name
    , I.description
    , I.importance
    , I.closed
    , S.name AS current_step 
    , vIS.responsible AS current_responsible
    , I.created_by
 FROM tbl_Issues AS I
LEFT JOIN tbl_issues_steps AS vIS
    ON I.ID = vIS.Issue_ID 
    AND vIS.completed IS NULL
LEFT JOIN viw_steps AS S
    ON S.id = vIS.step_id;
