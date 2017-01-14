package org.oliot.tutorials;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
	//private static final String DB_CONNECTION = "jdbc:oracle:thin:@143.248.57.21:1521:orcl";
	private static final String DB_USER = "c##yale";//c##yale
	private static final String DB_PASSWORD = "yale";//yale

	public static void main(String[] args) {
		
		BufferedReader br = null;
		FileReader fr = null;

		try {
			String path="C:/Project/epcis/workspace/accessingApp/src/resource/createTables.txt";
			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));
			String createTableSQL="";
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.contains(";")){
					sCurrentLine=sCurrentLine.replace(';', ' ');
					createTableSQL+=sCurrentLine+"\n";
					//System.out.println("Statment:");
					//System.out.println(createTableSQL);
					createTable(createTableSQL);
					createTableSQL="";
				}else{
					createTableSQL+=sCurrentLine+"\n";
				}
				
				
			}
			
			

		} catch (IOException  | SQLException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	
	private static void createTable(String createTableSQL) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;


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
