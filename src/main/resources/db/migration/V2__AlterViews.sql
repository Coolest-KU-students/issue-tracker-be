ALTER VIEW viw_issues
AS
    SELECT id, closed, name, description, importance
    FROM tbl_issues;

    
ALTER VIEW viw_steps
AS
    SELECT id, sort_order, name
    FROM tbl_steps;