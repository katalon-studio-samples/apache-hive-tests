import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import com.katalon.hive.DBKeywords as DBKeywords
import com.kms.katalon.core.testdata.DBData as DBData
import internal.GlobalVariable as GlobalVariable

'Connect to Apache Hive'
DBKeywords.startConnection(GlobalVariable.g_host, GlobalVariable.g_port, GlobalVariable.g_database, GlobalVariable.g_username, 
    GlobalVariable.g_password)

'Query all tables'
DBKeywords.executeQueryAndGetResult('SHOW TABLES')