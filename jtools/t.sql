CREATE OR REPLACE FUNCTION oscar_quote RETURN VARCHAR2
  AS LANGUAGE JAVA
  NAME 'oscar.quote() return java.lang.String';
/
CREATE OR REPLACE FUNCTION oscar_date RETURN VARCHAR2
  AS LANGUAGE JAVA
  NAME 'oscar.sdate() return java.lang.String';
/
