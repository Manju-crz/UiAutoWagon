
package mks.java.util.csv;

import java.io.File;
import java.io.IOException;

import mks.java.util.Sleep;


/**
 * It is a class to initiate the Csv File object.
 * The Csv file on which we want to perform any action, the file should be converted to an CvsObject initiating from this class.
 * CsvReader, CsvWrite and CsvUpdater classes utilize this CsvFile object to perform any actions accordingly.
 * 
 * @author manjunath.ks (mks)
 *
 */
public class CsvFile {
    
    /**
     * There are four types of Csv files and CsvFile allows to accept all four.
     * Predefined configurations
     * Constant Delimiter character End of line symbols
     * STANDARD_PREFERENCE , \r\n
     * EXCEL_PREFERENCE , \n
     * EXCEL_NORTH_EUROPE_PREFERENCE ; \n
     * TAB_PREFERENCE \t \n
     * Please note: the end of line symbols are only used for writing.
     * 
     * @author manjunath.ks (mks)
     *
     */
    public enum CsvType {
        STANDARD_PREFERENCE,
        EXCEL_PREFERENCE,
        EXCEL_NORTH_EUROPE_PREFERENCE,
        TAB_PREFERENCE;
    }
    
    private File csvFileObj = null;
    private String file = "";
    private String defaultDelimiter = ",";
    private String defaultEOL = "\r\n";
    
    /**
     * If the Csv file type input is not provided, it uses the default type i.e. STANDARD_PREFERENCE of CsvType
     * 
     * @param fileName
     *            The complete file path with name should be provided as string
     * @throws Exception 
     */
    public CsvFile(String fileName) throws Exception {
        file = fileName;
        if (!file.endsWith(".csv"))
            throw new Exception(String.format("Given file '%s' is not a valid CSV file. It should end with .csv", file));
        csvFileObj = new File(file);
    }
    
    /**
     * Based on the given CsvType, it uses the Csvfile object
     * 
     * @param csvType
     *            The enum CsvType declared variable
     * @param fileName
     *            The complete file path with name should be provided as string
     * @throws Exception 
     */
    public CsvFile(CsvType csvType, String fileName) throws Exception {
        this(fileName);
        switch (csvType) {
        case STANDARD_PREFERENCE:
            defaultDelimiter = ",";
            defaultEOL = "\r\n";
            break;
        case EXCEL_PREFERENCE:
            defaultDelimiter = ",";
            defaultEOL = "\n";
            break;
        case EXCEL_NORTH_EUROPE_PREFERENCE:
            defaultDelimiter = ";";
            defaultEOL = "\n";
            break;
        case TAB_PREFERENCE:
            defaultDelimiter = "\t";
            defaultEOL = "\n";
            break;
        default:
        }
    }
    
    /**
     * Helps to find the existence of given CSV file path in the class constructor.
     * 
     * @return If the file existing, it returns true. Else returns false.
     */
    public boolean isCsvFileExist() {
        
        return csvFileObj.exists();
    }
    
    /**
     * Returns the csv file object for further reference
     * 
     * @return
     */
    public File getCSVFile() {
        return csvFileObj;
    }
    
    /**
     * Returns the csv delimiter for further reference
     * 
     * @return
     */
    public String getDemiliter() {
        return defaultDelimiter;
    }
    
    /**
     * Returns the csv end of line for further reference
     * 
     * @return
     */
    public String getEndOfLine() {
        return defaultEOL;
    }
    
    /**
     * It helps to create a new file if at all the file is not already existing in the given location
     * 
     * @return Returns the file object
     */
    public CsvFile createFile() throws IOException {
        if (!isCsvFileExist()) {
            csvFileObj.createNewFile();
        }
        return this;
    }
    
    /**
     * It helps to create a new file, if the file is already existing in the location, it deletes and re creates a empty file
     * 
     * @return Returns the file object
     * @throws Exception 
     */
    public CsvFile createFileForce() throws Exception {
        
        if (!deleteFile()) {
            throw new Exception("Could not delete the existing file from the given location before creating a new one!");
        }
        csvFileObj.createNewFile();
        return this;
    }
    
    /**
     * It helps to delete the existing file if it existing.
     * 
     * @return Even after deleting the file, if it still existing in the given folder, then returns false. If successfully deleted and not found in the folder then returns true.
     */
    public boolean deleteFile() {
        if (csvFileObj.exists()) {
            csvFileObj.delete();
            Sleep.forASecond();
        }
        return (!csvFileObj.exists());
    }
    
}
