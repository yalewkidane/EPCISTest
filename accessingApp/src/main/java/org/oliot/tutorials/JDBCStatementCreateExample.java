package org.oliot.tutorials;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class JDBCStatementCreateExample {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	//private static final String DB_CONNECTION = "jdbc:oracle:thin:@143.248.57.21:1521:orcl";
	private static final String DB_USER = "c##yale";//c##yale
	private static final String DB_PASSWORD = "yale";//yale

	public static void main(String[] argv) {

		try {

			//createDbUserTable();
			//dropDbUserTable();
			//insertDbUserTable();
			selectfromDbUserTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

	}
	
	private static  void insertDbUserTable()throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "insert into DBUSER (CREATED_BY, CREATED_DATE, USERNAME, USER_ID) "
				+ "values (?,?, ?, ?)";
		
		
		try {
			dbConnection = getDBConnection();
			//statement = dbConnection.createStatement();
			
			PreparedStatement ps = dbConnection.prepareStatement(createTableSQL);
			//ps.setInt(4,5);
			ps.setString(1,"Yalew");
			ps.setDate(2, new  java.sql.Date(0));
			ps.setString(3,"Yale");
			ps.execute();
			ps.close();
			
			
			//dbConnection = getDBConnection();
			//statement = dbConnection.createStatement();

			System.out.println(createTableSQL);
                        // execute the SQL stetement
			//statement.execute(createTableSQL);

			System.out.println("data is inserted to \"dbuser\" !");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}
	
	private static  void selectfromDbUserTable()throws SQLException{
		Connection dbConnection = null;
		Statement statement = null;
		String createTableSQL = "select * from  DBUSER ";
		
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(createTableSQL);
                        // execute the SQL stetement
			ResultSet rs=statement.executeQuery(createTableSQL);
			
			while(rs.next()){
				String userid=rs.getString("USER_ID");
				System.out.println("USER_ID :"+userid);
			}

			System.out.println("Table \"dbuser\" is created!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}
	private static void createDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String createTableSQL = "CREATE TABLE DBUSER ("+
							"USER_ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,"+
							"USERNAME      VARCHAR2 (20)  NOT NULL,"+
							"CREATED_BY    VARCHAR2 (20)  NOT NULL,"+
							"CREATED_DATE  DATE          NOT NULL);";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
 
			System.out.println(createTableSQL);
                        // execute the SQL stetement
			statement.execute(createTableSQL);

			System.out.println("Table \"dbuser\" is created!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}
	
	private static void createSequenceTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String createTableSQL = "CREATE SEQUENCE TAB_EPCList_SEQ START WITH 1"+
	"MAXVALUE 999999999999999999999999999 	MINVALUE 1 	NOCYCLE 	CACHE 20 	NOORDER;";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(createTableSQL);
                        // execute the SQL stetement
			statement.execute(createTableSQL);

			System.out.println("Table \"dbuser\" is created!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}
	
	private static void dropDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String createTableSQL = "DROP TABLE DBUSER";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(createTableSQL);
                        // execute the SQL stetement
			statement.execute(createTableSQL);

			System.out.println("Table \"dbuser\" is droped!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}
	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
					DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}
	
	 

}