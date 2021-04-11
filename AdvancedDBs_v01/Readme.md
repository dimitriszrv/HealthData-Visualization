# Configure JavaFx & MySQL

### Creating new User Library to include Javafx
Preferences>>Java>>Build Path>>User Libraries>>New>>'Name it javafx'
Add jars to the created javafx user library (there are stored in lib subdirectory of the JavaFX directory)

After creating the library
'right click on project'>>Properties>>Java Build Path>>Libraries>>Classpath>>Add Library>>User Library>>javafx

### Configure mysql-connector
Find the path of jar file 'mysql-connector-java-8.0.23.jar' at  '/javafx-sdk-16/lib/mysql-connector/'.

Right click on project name>>Build Path>>Configure Build Path>>Libraries>>Modulepath>>Add JARS>>Select .jar file>>OK>>Apply and Close

# Run project

Right click on project>>Run Configurations>>Arguments>>VM arguments>>'write this': --module-path "JavaFX\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml
