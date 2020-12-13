/*@lineinfo:filename=TestInstallSQLJ*//*@lineinfo:user-code*//*@lineinfo:1^1*//* Import SQLExceptions class.  The SQLException comes from
   JDBC. Executable #sql clauses result in calls to JDBC, so methods
   containing executable #sql clauses must either catch or throw
   SQLException.  
 */
import java.sql.SQLException ;
import oracle.sqlj.runtime.Oracle;


// iterator for the select

/*@lineinfo:generated-code*//*@lineinfo:12^1*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

class MyIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public MyIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    ITEM_NAMENdx = findColumn("ITEM_NAME");
  }
  public String ITEM_NAME() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(ITEM_NAMENdx);
  }
  private int ITEM_NAMENdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:12^39*/

class TestInstallSQLJ 
{
  //Main method
  public static void main (String args[]) 
  {
    try
    { 
      /* if you're using a non-Oracle JDBC Driver, add a call here to
          DriverManager.registerDriver() to register your Driver
      */

      // set the default connection to the URL, user, and password
      // specified in your connect.properties file
      Oracle.connect(TestInstallSQLJ.class, "connect.properties");

      TestInstallSQLJ ti = new TestInstallSQLJ();
      ti.runExample(); 
    }
    catch (SQLException e)
    { 
      System.err.println("Error running the example: " + e);
    }
    finally
    {
      try { Oracle.close(); } catch (SQLException e) { }
    }

  } //End of method main

  //Method that runs the example
  void runExample() throws SQLException
  {
      //Issue SQL command to clear the SALES table
    /*@lineinfo:generated-code*//*@lineinfo:47^5*/

//  ************************************************************
//  #sql { DELETE FROM SALES  };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = sqlj.runtime.ref.DefaultContext.getDefaultContext();
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TestInstallSQLJ_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:47^30*/
    /*@lineinfo:generated-code*//*@lineinfo:48^5*/

//  ************************************************************
//  #sql { INSERT INTO SALES(ITEM_NAME) VALUES ('Hello, SQLJ!') };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = sqlj.runtime.ref.DefaultContext.getDefaultContext();
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TestInstallSQLJ_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:48^64*/

    MyIter iter;
    /*@lineinfo:generated-code*//*@lineinfo:51^5*/

//  ************************************************************
//  #sql iter = { SELECT ITEM_NAME FROM SALES  };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = sqlj.runtime.ref.DefaultContext.getDefaultContext();
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TestInstallSQLJ_SJProfileKeys.getKey(0), 2);
    try 
    {
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      iter = new MyIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:51^47*/

    while (iter.next()) {
      System.out.println(iter.ITEM_NAME());
    }
  }
}/*@lineinfo:generated-code*/class TestInstallSQLJ_SJProfileKeys 
{
  private static TestInstallSQLJ_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TestInstallSQLJ_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TestInstallSQLJ_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = sqlj.runtime.ref.DefaultContext.getProfileKey(loader, "TestInstallSQLJ_SJProfile0");
  }
}
