set heading off
set pagesize 0

select 'pstmt.setString('||column_id||','||lower(column_name)||');'
   from user_tab_columns
  where table_name = 'IAF_ACCOUNTS'
/
select 'String '||lower(column_name)||' = '||'"'||'";'
   from user_tab_columns
  where table_name = 'IAF_ACCOUNTS'
/
select lower(column_name)||','
   from user_tab_columns
  where table_name = 'IAF_ACCOUNTS'
/
