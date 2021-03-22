import sys
import os
import csv
import pandas as pd
import glob
import numpy as np
import functools
import pathlib

def main() :
    print("Python : ", sys.version)
    print("Pandas version : ", pd.__version__)
    print("_________________________________")

    global csvFileDir
    global currentCsvFile
    global minDate
    global maxDate
    global Countries
    global normalizedFilesDirPath
    global finalCSVDirPath


    # Initialize global Paths and variables
    initValues()

    print("\n_____PROCCESSING .CSV FILES_____\n")
    for filename in os.listdir(csvFileDir) :
        if filename.endswith(".csv"):
            file_path = os.path.join(csvFileDir, filename)

            # Create Dataframe of the csv's data
            df = pd.read_csv(file_path)
            
            #print(df.head())
            #my_list = list(df.columns)
            #print(len(my_list), my_list)

            # Adding unique countries to the global Countries list 
            countriesFromSingleFile = df['country'].to_list()
            checkCountries(countriesFromSingleFile)

            # Adding unique years to the global Years list
            yearsFromSingleFile = list(df.columns)
            checkYears(yearsFromSingleFile)

        else :
            continue
    print("_______________\n")
    Countries.sort()
    Years.sort()
    print(Countries, "\n")
    print(Years, "\n")

    for filename in os.listdir(csvFileDir) :
        if filename.endswith(".csv"):
            file_path = os.path.join(csvFileDir, filename)

            normalizeCSVfile(file_path)
    
    createFinalCSV()
    

# Initialize global variables
def initValues():
    global Countries
    global minDate
    global maxDate
    global Years
    global normalizedFilesDirPath
    global csvFileDir
    global finalCSVDirPath
    

    # Directory containing the python parser
    parserDir = pathlib.Path(__file__).parent.absolute()
    # Directory containing the datasets
    csvFileDir = os.path.join(str(parserDir),'row_csv_data')

    # Directory containing the normalized csv files
    normalizedFilesDirPath = os.path.join(str(parserDir),'NormalizedData')
    
    # Directory containing the final csv with the data
    finalCSVDirPath = os.path.join(str(parserDir),'FinalCSV')

    Countries = []
    Years = []


# Check if any country from a list not exists in the Countries
def checkCountries(countriesFromASingleFile) :
    global Countries

    for cntry in countriesFromASingleFile : 
        if cntry not in Countries :
            Countries.append(cntry)


# Check if any year from a list not exists in the Years
def checkYears(yearsFromSingleFile):
    global Years
    
    for year in yearsFromSingleFile :
        if year != 'country' :
            if year not in Years :
                Years.append(year) 


# Normalise every single dataset to contain every year and every country
def normalizeCSVfile(csvFilePath):

    print("__________________________________________________________________")
    print("\n___NORMALIZING '", csvFilePath, "' FILE___")

    global Countries
    global Years

    newCountries = []
    columnsCounter = 0
    newYears = []

    df = pd.read_csv(csvFilePath)
    countriesInFile = df['country'].to_list()

    # Check if any country is missing from the csvFile
    for cntry in Countries :
        if cntry not in countriesInFile :
            newCountries.append(cntry)  
    print("* Countries to be added -> ", newCountries)


    colNames = list(df.columns)
    # Check if any year is missing as column from the csvFile
    for year in Years :
        if year not in colNames :
            newYears.append(year)
    print("* Years to be added as Columns : ", newYears, "\n")


    # If not all countries from the global Countries appear in this file
    # insert rows with the extras countries into the dataframe
    if newCountries : 
        for cntry in newCountries :
            new_row = {'country':cntry}
            df = df.append(new_row, ignore_index=True)

        # Sort dataframes alphabetically by column 'country' 
        df = df.sort_values(by=['country'])    

    # Insert years that are missing as columns and set values to naN
    if newYears :
        for year in newYears :
            df[year] = np.nan

    # Sort dataframes by columns' names
    # [country] appears as the last column
    df = df.sort_index(axis=1)
    
    # Fix the [country] column appearance. Now it's the 1st column
    cols = list(df.columns)
    cols = [cols[-1]] + cols[:-1]
    df = df[cols]
    
    # Reset indexing of the rows
    df = df.reset_index(drop=True)

    print("+++++++++++++++")
    print("+++DATAFRAME+++")
    print("+++++++++++++++\n")
    print(df, "\n")
    

    exportDataframeToCSV(df, csvFilePath)



# Generate normalized csv files 
def exportDataframeToCSV(dataframe, csvFilePath) :
    global normalizedFilesDirPath
    
    dirPath, fileName = os.path.split(csvFilePath)

    # Path of the normalized csv file (Name: Norm_'original name of the csv file')
    normalizedFilePath = os.path.join(normalizedFilesDirPath,'Norm_' + fileName)

    print("____EXPORT DATA TO NORMALIZED CSV FILE : ", normalizedFilePath, "____")

    dataframe.to_csv(normalizedFilePath, index = False, header = True)


def createFinalCSV() :
    global finalCSVDirPath
    global Years
    global Countries
    global finalCSVDirPath
    global normalizedFilesDirPath

    # Create dataframe with 2 columns
    # country | years (every possible pair of country-year)
    cntryDF = pd.DataFrame({'country' : Countries})
    yrsDF = pd.DataFrame({'years' : Years})

    cntryDF['key'] = 0
    yrsDF['key'] = 0
    df = ( cntryDF.merge(yrsDF, on='key', how='outer'). drop(columns='key') )
    
    print(df, "\n")

    colCounter=1
    # For every normalized file, add a column to the dataframe so that each row will hold the properly value
    # for the {country-year} pair 
    for normalizedFile in os.listdir(normalizedFilesDirPath) :
        if normalizedFile.endswith(".csv"):
            normalized_filepath = os.path.join(normalizedFilesDirPath, normalizedFile) 

            tempDF = pd.read_csv(normalized_filepath)

            tempDF = tempDF.loc[:, tempDF.columns != 'country']
            rowsList = tempDF.values.tolist()
            
            # Will hold the values from all rows
            flattenRowList = []

            # Flatten the list of lists to a single list
            for sublist in rowsList :
                for value in sublist : 
                    flattenRowList.append(value)
            
            # Make the list with the values a unique column in the dataframe
            df[colCounter] = flattenRowList
            
            
            colCounter += 1
    print("\n\n")
    print("_____________________")
    print("_____________________")
    print("___FINAL DATAFRAME___")
    print("_____________________")
    print("_____________________")
    print(df, "\n")

    print("*** EXPORT FINAL CSV ***")
    print("- Location : ", finalCSVDirPath)

    finalFullPath = os.path.join(finalCSVDirPath,'Final.csv') 
    df.to_csv(finalFullPath, index = False, header = True)

            



main()

