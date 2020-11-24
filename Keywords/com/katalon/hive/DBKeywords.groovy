package com.katalon.hive

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.Statement;
import java.util.ArrayList
import java.util.List

import com.kms.katalon.core.db.ListObjectResultSetHandler
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.util.KeywordUtil

public class DBKeywords {

	private static java.sql.Connection connection;

	public static startConnection(String host, String port, String databaseName) {
		startConnection(host, port, databaseName, '', '')
	}

	public static startConnection(String host, String port, String databaseName, String username, String password) {
		println username
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		String connectionURL = "jdbc:hive2://${host}:${port}/${databaseName}";

		KeywordUtil.logInfo("Connecting to ${connectionURL}...")
		connection = DriverManager.getConnection(connectionURL, username, password)
	}

	public static close() {
		if (connection != null && !connection.isClosed()) {
			connection.close()
		}
	}

	public static void executeStatement(String sql) {
		Statement statement = connection.createStatement()
		statement.execute(sql)
	}

	public static ResultSet executeQueryAndGetResult(String query) {
		Statement statement = connection.createStatement()
		ResultSet rs = statement.executeQuery(query)
		if (rs == null) {
			return null;
		}
		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();
		int rowCount = rs.getRow();
		KeywordUtil.logInfo("Result metadata: Row count: ${rowCount}, Column count: ${columnCount}")

		return rs
	}

	public static List<List<Object>> convertResultSetToTable(ResultSet rs) {
		int columnCount = rs.getMetaData().getColumnCount();
		List<List<Object>> result = new ArrayList<List<Object>>();
		while (rs.next()) {
			List<Object> row = new ArrayList<Object>();
			for (int i = 1; i <= columnCount; i++) {
				row.add(rs.getObject(i));
			}
			result.add(row);
		}
		return result;
	}
}
