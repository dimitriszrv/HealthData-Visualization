import sys
import os
import pathlib
import csv

import mysql.connector as conn
from mysql.connector import errorcode

global user
global password 
global dataBase_Name
global table_Data_Name
global TABLES

#sotiris pc
user = 'root'
password = 'sql123456'

# Directory containing the python parser
parserDir = pathlib.Path(__file__).parent.absolute()
# Directory containing the final csv with the data
finalCSVDirPath = os.path.join(str(parserDir),'FinalCSV')
# Full path of Final.csv
finalFullPath = os.path.join(finalCSVDirPath,'Final.csv') 

#Our Database name
dataBase_Name= 'AdvancedDBs_v01'
#Our main Table name with all the measurments
table_Data_Name = 'Data'

#An array where we store all table creation queries
TABLES = {}


def Main():

    CreateDataTableQuery()
  
    #Try to open an connection to Mysql server
    try:
        con = conn.connect(host="localhost",user=user,password=password)
        print('Connected to DB: {}'.format('127.0.0.1'))
        # Create cursor.
        cursor = con.cursor()

        CreateDB( dataBase_Name, cursor)

        CreateTable(table_Data_Name, cursor)


        ################          S O S          ##############
        #Uncommended Only ONE ( 1  ) Time

        #LoadData(cursor)
        
        
        
        # Make sure data is committed to the database
        con.commit()

        con.database = dataBase_Name

    except Exception as e:
        print('Error in Main Function: {}'.format(str(e)))
        sys.exit(1)

    con.close()



"""This function execute SQL command."""
def CreateDB(DB_NAME,cursor):
    try:
        cursor.execute("USE {}".format(DB_NAME))

    
    except conn.Error as err:
        print("Database {} does not exists.".format(DB_NAME))
        if err.errno == errorcode.ER_BAD_DB_ERROR:

            try:
                cursor.execute(
                    "CREATE DATABASE {} DEFAULT CHARACTER SET 'utf8'".format(DB_NAME))
            except conn.Error as err:
                print("Failed creating database: {}".format(err))
                exit(1)


            print("Database {} created successfully.".format(DB_NAME))
            
        else:
            print(err)
            exit(1)


def CreateTable(table_name,cursor):
    global TABLES

    for table_name in TABLES:
        table_query = TABLES[table_name]
        try:
            print("Creating table {}: ".format(table_name), end='')
            cursor.execute(table_query)
        except conn.Error as err:
            if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
                print("already exists.")
            else:
                print(err.msg)
        else:
            print("OK")


#Creates the creation query for ur Basic Table
def CreateDataTableQuery():
    global finalFullPath
    global TABLES
    global table_Data_Name 

    final_query = ''
    columns = ''
    final_str = ''
    
    with open(finalFullPath, 'r') as csvFile:
        reader = csv.reader(csvFile)
        for row in reader:
            columns = row
            break
    csvFile.close()


    count = 0
    for a in columns:
        if count >= 2:
            final_str = final_str + "`" + a + "`" + " VARCHAR(30),"
        else:
            final_str = final_str + a + " VARCHAR(30),"
        count += 1
    final_str = final_str[:-1]


    TABLES[table_Data_Name]=( "CREATE TABLE " + str(table_Data_Name)+ " ( " + final_str + " , PRIMARY KEY (country,years) ) ENGINE=InnoDB")




def LoadData(cursor):
    insertQuery = ''

    with open(finalFullPath, 'r') as csvFile:
        reader = csv.reader(csvFile)
        
        lineCounter = 0

        atrtributes = "("

        for row in reader:
            
            if(lineCounter == 0):
                
                #Creating the attribute line for sql Query
                # e.g. : (`country`,`years`,`1`,`2`,`3`,`4`,`5`,`6`,`7`,`8`,`9`,`10`,`11`,`12`,`13`,`14`)
                for a in row:
                    atrtributes = atrtributes + "`" + a + "`" +","
                
                atrtributes = atrtributes[:-1] 
                atrtributes = atrtributes + ")"
                
                lineCounter += 1

            else:
                insertQuery = ("INSERT INTO Data " + atrtributes +  " VALUES ( %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)")
                
                cursor.execute(insertQuery,row)
                
                print('Line %d is executed.'%(lineCounter))

                lineCounter += 1
                
        
    print("The insertion of data in Database is DONE.")
    csvFile.close()

'''  THE MAIN EXECUTION PROGRAM  '''
Main()
