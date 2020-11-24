import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.sql.ResultSet

import com.katalon.hive.DBKeywords
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

'Connect to Hive and create table test'
WebUI.callTestCase(findTestCase('2. Create table test'), [:], FailureHandling.STOP_ON_FAILURE)

'Clear all current rows'
DBKeywords.executeStatement('DELETE FROM t1')

'Insert new rows'
DBKeywords.executeStatement("INSERT INTO t1 VALUES (1, 'one'), (2, 'two'), (3, 'three')")

ResultSet rs = DBKeywords.executeQueryAndGetResult("SELECT * FROM t1")
List table = DBKeywords.convertResultSetToTable(rs)
println table

'Verify the return result should have two columns'
assert table.size() == 2

'Verify data of the query result'
List firstColumn = table.get(0)
assert firstColumn == [1, 2, 3]

List secondColumn = table.get(1)
assert secondColumn == ['one', 'two', 'three']

