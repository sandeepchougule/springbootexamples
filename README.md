# springbootexamples
This is to create an artifact of codes
Problem Statement

Notes:
•	The folder inputFiles.zip has multiple files for input data. 
•	The format is loosely based on key-value pair. Please analyze the lines to get more idea on the format. 
•	the value after the key DATETIME= denotes the time on the log line
•	the value after the key OBJECTNAME= denotes the Filename on the log line
•	the value after the key USER_NAME= denotes the Username on the log line


• 8th Column is pipe delimited file : 8th column is an IP address so can i consider 8th or 11th column as an IP
• 12th Col is the IpAddresses : 12th Column contains date-time 
• 2nd Col is the account name

Requirements
•	Create an angular(or similar) app, that will accept the included zip file as an input. 
•	After the zip file is uploaded, read though the individual files to generate 3 widgets below

Widgets.
o	Trend of activity by hour. This needs to be a graph, Either a line graph or a bargraph. 
API : http://localhost:8080/api/activitiy/trend
o	Top 5 Usernames
API : http://localhost:8080/api/top/users?topLimit=5
Note: You can modify the topLimit
o	Top 5 Filenames
API : http://localhost:8080/api/top/files?topLimit=5

Home Page
http://localhost:8080/static/index.html

To run the project
 String zipFilePath = "/opt/tmp/input/inputFiles.zip"
Extract of files are
 String destDir = "/opt/tmp/output";
