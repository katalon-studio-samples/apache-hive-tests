# apache-hive-tests
Sample demo project in integrate Katalon Studio with Apache Hive

## Prerequisites
- Install Hadoop and Apache Hive successfully.
<p> Note: It's not easy to setup Hadoop and Apache Hive completely. Please check carefully Hadoop and Hive services are up such as: localhost:9870 and localhost:10002 before using with Katalon.</p>
- Install Gradle

## How to use
- Clone the project
- Before opening project, open terminate under the project location and type `gradle katalonCopyDendencies`
- Open the project in Katalon Studio
- Modify the `default` profile by changing default variable values as your database settings
```
GlovalVariable.g_host = <HiveServer2 url> //default is localhost
GlovalVariable.g_port = <HiveServer2 port> //default is 10000
GlovalVariable.g_database = <Database name> //default is 'default'
GlovalVariable.g_username = <Hadoop username or logged username> //default is '' as logged username
GlovalVariable.g_password = <password of the username> //default is ''
```
- Open the `All Tests` test suites and run the sample suite