
package mks.java.util.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * It is containing different reusable methods to write the data into CsvFile, where the file empty.
 * Even if we want to just append more records/rows into the file, it helps to do so.
 * 
 * @author manjunath.ks (mks)
 *
 */
public class CsvWriter extends CsvReader {
    
    private BufferedWriter bufferedWriter = null;
    
    /**
     * It instantiates the CsvWriter object based on the given CsvFile object
     * 
     * @param csvFile
     *            The instantiated reference object of CsvFile
     * @throws Exception 
     */
    public CsvWriter(CsvFile csvFile) throws Exception {
        super(csvFile);
        bufferedWriter = new BufferedWriter(new FileWriter(csvFile.getCSVFile(), true));
    }
    
    /**
     * Helps to close the written file
     */
    public void closeFileWriter() throws IOException {
        bufferedWriter.close();
    }
    
    /**
     * Helps to write all required headers
     * 
     * @param columnHeaders
     *            Input values of type string array should be in the order that is required to write in file
     * @return Returns this class object
     * @throws Exception 
     */
    public CsvWriter writeHeaders(String... columnHeaders) throws Exception {
        if (isFirstRowDataEmpty()) {
            writeRow(columnHeaders);
        } else {
            throw new Exception("Given file is already containing a header row data!");
        }
        return this;
    }
    
    /**
     * Helps to write all required row values of one row
     * It appends if there is records already existing
     * 
     * @param rowValues
     *            Input values of type string array should be in the order that is required to write in file. If any cell value required to keep blank empty string input helps to do so.
     * @return Returns this class object
     * @throws IOException
     */
    public CsvWriter writeRow(String... rowValues) throws IOException {
        
        for (int i = 0; i < rowValues.length - 1; i++) {
            bufferedWriter.write(rowValues[i] + delimiter);
        }
        bufferedWriter.write(rowValues[rowValues.length - 1] + endOfLine);
        return this;
    }
    
    /**
     * Helps to write all required row values of list of rows
     * 
     * @param rowValues
     *            List of array of string which holds row's cell values that are required to write in file
     * @return Returns this class object
     * @throws IOException
     */
    public CsvWriter writeRows(List<String[]> rowValues) throws IOException {
        
        for (String[] row : rowValues) {
            writeRow(row);
        }
        return this;
    }
    
}
