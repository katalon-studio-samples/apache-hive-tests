import java.sql.ResultSet

import com.katalon.hive.DBKeywords

import internal.GlobalVariable

'Connect to Apache Hive'
DBKeywords.startConnection(GlobalVariable.g_host, GlobalVariable.g_port, GlobalVariable.g_database, GlobalVariable.g_username, 
    GlobalVariable.g_password)

'Create a sample table if not exists'
DBKeywords.executeStatement("CREATE TABLE IF NOT EXISTS t1 (x INT, y STRING) stored as orc TBLPROPERTIES ('transactional'='true')")

'Query all tables'
ResultSet rs = DBKeywords.executeQueryAndGetResult('SHOW TABLES')

List table = DBKeywords.convertResultSetToTable(rs)
println table

'Verify the return result should have one column'
assert table.size() == 1

'Verify the return result should contain new table new'
List tableNames = table.get(0)
assert tableNames.contains('t1')
