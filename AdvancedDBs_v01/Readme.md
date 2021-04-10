# Configure JavaFx

### Creating new User Library to include Javafx
Preferences>>Java>>Build Path>>User Libraries>>New>>'Name it javafx'
Add jars to the created javafx user library (there are stored in lib subdirectory of the JavaFX directory)

After creating the library
'right click on project'>>Properties>>Java Build Path>>Libraries>>Classpath>>Add Library>>User Library>>javafx

# Run project

Right click on project>>Run Configurations>>Arguments>>VM arguments>>'write this': --module-path "JavaFX\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml
