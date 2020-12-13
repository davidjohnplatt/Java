
CREATE OR REPLACE FUNCTION jProc_quote RETURN VARCHAR2
  AS LANGUAGE JAVA
  NAME 'jProc.quote() return java.lang.String';
/
CREATE OR REPLACE PROCEDURE jProc_makerow (instring VARCHAR2 )
  AS LANGUAGE JAVA
  NAME 'jProc.makeRow(java.lang.String)';
/
CREATE OR REPLACE PROCEDURE jProc_parseURL (instring VARCHAR2 ) 
  AS LANGUAGE JAVA
  NAME 'jProc.parseURL(java.lang.String)';
/
