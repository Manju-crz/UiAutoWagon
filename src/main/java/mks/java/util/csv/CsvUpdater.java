
package mks.java.util.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * It is containing different reusable methods to update the data into existing CsvFile wherever we want within the file.
 * Mostly it provides all required methods that help in various scenarios like update any cell value, append any new column, remove row or column and many as such
 * 
 * @author manjunath.ks (mks)
 *
 */
public class CsvUpdater extends CsvReader {
    
    private BufferedWriter bufferedWriter = null;
    private String csvDataToUpdate = "";
    
    /**
     * It instantiates the CsvUpdater object based on the given CsvFile object
     * As per the functionality, original file content will be stored in string and erase file data.
     * Later updates the data as per called methods of this class. Once after updating required data, file should be closed.
     * 
     * @param csvFile
     *            The object reference of CSVFile
     * @throws Exception 
     */
    public CsvUpdater(CsvFile csvFile) throws Exception {
        super(csvFile);
        if (isFirstRowDataEmpty())
            throw new Exception("There is no data found in the file to update further. Kindly write data into the file using CsvWriter!");
        csvDataToUpdate = readCSV();
        closeFileReader();
        bufferedWriter = new BufferedWriter(new FileWriter(csvFile.getCSVFile(), false));
        closeFileReader();
    }
    
    /**
     * It helps to retrieve the headers of the file if given header name exists in the file.
     * If the given header name is not found in the file, it throws Exception.
     * 
     * @param existingHeaderName
     *            Name of the header required to check
     * @throws Exception 
     */
    private List<String> getHeadersIfGivenHeaderExists(String existingHeaderName) throws Exception {
        List<String> headers = new ArrayList<>(Arrays.asList(csvDataToUpdate.split(endOfLine)[0].split(delimiter)));
        if (!headers.contains(existingHeaderName)) {
            keepOrgFile();
            throw new Exception("Given existing header name is not found!");
        }
        return headers;
    }
    
    /**
     * It helps to replace the existing header name to a newer name
     * 
     * @param existingHeaderName
     *            Input of existing header name
     * @param newHeaderName
     *            Input string of new column header name
     * @return Returns this class object
     * @throws Exception 
     */
    public CsvUpdater replaceHeaderColumnNameTo(String existingHeaderName, String newHeaderName) throws Exception {
        
        List<String> tempHeaders = getHeadersIfGivenHeaderExists(existingHeaderName);
        int idx = tempHeaders.indexOf(existingHeaderName); // It captures the index of header name
        tempHeaders.set(idx, newHeaderName);
        // Since the captured tempHeaders are in the form of List<String>, converts the data with header name in the required format to update
        String headerStrToReplace = tempHeaders.toString().replace(delimiter + " ", delimiter).replace("[", "").replace("]", "");
        csvDataToUpdate = csvDataToUpdate.replace(csvDataToUpdate.split(endOfLine)[0], headerStrToReplace);
        return this;
    }
    
    /**
     * It helps to add the new column header name at the end of all columns
     * 
     * @param newHeaderName
     *            Input string of new column header name
     * @return Returns this class object
     */
    public CsvUpdater appendNewColumnHeader(String newHeaderName) {
        String existingHeaderLine = csvDataToUpdate.split(endOfLine)[0];
        String newHeaderLine = existingHeaderLine + delimiter + newHeaderName;
        csvDataToUpdate = csvDataToUpdate.replace(existingHeaderLine, newHeaderLine);
        return this;
    }
    
    /**
     * Helps to update the existing row cell value with a new value
     * Say we have a csv file with with data
     * CustomerId,CustomerName,Country,PinCode,Email
     * 10003,Blue,France,330003,ghi@gmail.com
     * 10004,Reddy,Jermany,440004,abc@gmail.com
     * 10008,WhoAmI
     * 10009,Bharat,,,
     * 10013,Satty,India,110001,abc@gmail.com
     * 
     * Case 1:
     * If we want to replace the Pincode value in the first row with "00000"
     * Then the row gets updated to
     * 10003,Blue,France,00000,ghi@gmail.com
     * 
     * Case 2:
     * If we want to replace the Pincode value in the fourth row with "00000"
     * Then the row gets updated to
     * 10009,Bharat,,00000,
     * 
     * Case 3:
     * If we want to replace the Pincode value in the third row with "00000"
     * Then the row gets updated to
     * 10008,WhoAmI,,00000,
     * (In this case, if any row data has lesser values without even delimiter, then also it should help to update the row.)
     * 
     * @param rowIndex
     *            Integer value of row index at which the cell value needs to be updated. Note- Index value of rows starts from zero including very first headers' row.
     * @param columnHeaderName
     *            The cell value on which data needs to be updated, that cell header name should be provided as input; While updating file with the same CsvUpdater reference, if the header name is already renamed, then the renamed header name should
     *            be provided as input.
     * @param cellValue
     *            The row cell value require which is require to be updated.
     * @return Returns this class object
     * @throws Exception 
     */
    public CsvUpdater replaceCellValueAt(int rowIndex, String columnHeaderName, String cellValue) throws Exception {
        
        // Find if the given header name in csv, if doesn't find, throws exception there itself. If finds then retrieves all headers and finds the given header index.
        int columnIdx = getHeadersIfGivenHeaderExists(columnHeaderName).indexOf(columnHeaderName);
        
        // Checks weather the given row index is exceeding the limit of total rows existence.
        String[] rowsData = csvDataToUpdate.split(endOfLine);
        if (!(rowIndex <= (rowsData.length - 1))) {
            keepOrgFile();
            throw new Exception("Given row index value is exceeding the existing limit of row indexes");
        }
        
        // It checks if the given row index is having data. If finds the empty/blank row, then throws exception
        if (rowsData[rowIndex].length() < 1) {
            keepOrgFile();
            throw new Exception("The complete row data of given input row index is empty!");
        }
        
        StringBuilder newDataSet = new StringBuilder();
        int headersLastIndex = rowsData[0].split(delimiter).length - 1; // Picking up the last index of header row
        String resultedRow = ""; // Declared to store the row at which the given cell value needs to be replaced
        List<String> replacerRowList = new ArrayList<>(Arrays.asList(rowsData[rowIndex].replace(endOfLine, "").split(delimiter)));
        for (int i = 0; i <= headersLastIndex; i++) {
            try {
                replacerRowList.get(i);
            } catch (IndexOutOfBoundsException e) {
                replacerRowList.add(i, "");
            } // Try catch block helps to update the empty cell value if the cell is not found at given column index
            if (i == columnIdx)
                replacerRowList.set(i, cellValue); // Replaces the existing value / empty valued cell to new cell value
            resultedRow = resultedRow + replacerRowList.get(i) + delimiter; // The row under operation is re prepared with new cell value
        }
        
        for (int i = 0; i < rowsData.length; i++) {
            if (i == rowIndex) {
                // This if condition helps to replace the old row data with new generated row data
                newDataSet.append(resultedRow.substring(0, resultedRow.lastIndexOf(delimiter)) + endOfLine);
                // Since the new resulted row holds the delimiter at the end as per above generated row data, it removes the last delimiter
            } else
                newDataSet.append(rowsData[i] + endOfLine);
        }
        csvDataToUpdate = newDataSet.toString();
        return this;
    }
    
    /**
     * It helps to save the file with given updates and then closes
     * 
     * @return Returns this class object
     * @throws IOException
     */
    public void closeWithUpdates() throws IOException {
        bufferedWriter.write(csvDataToUpdate);
        bufferedWriter.close();
    }
    
    /**
     * In case of any exceptions while updating the file, it just helps to keep the original data in the file
     * 
     * @throws IOException
     */
    private void keepOrgFile() throws IOException {
        bufferedWriter.write(csvOriginalData);
        bufferedWriter.close();
    }
    
    /**
     * If at all required to remove any of the existing record completely, then this method helps to perform the same.
     * 
     * @param rowIndex
     *            All the required row indexes needs to be removed.
     * @throws Exception 
     */
    public CsvUpdater removeRecords(Integer... rowIndex) throws Exception {
        
        List<Integer> ipRowIndexList = new ArrayList<>(Arrays.asList(rowIndex)); // Picking up given index values in List
        String[] rows = csvDataToUpdate.split(endOfLine); // Getting all the rows in array
        int lastRowIndex = rows.length - 1; // Finding out the last row index
        // If any input row index is exceeding the limit of last index row of the file, then it throws the exception.
        if(ipRowIndexList.stream().anyMatch(i -> i > lastRowIndex)) {
            keepOrgFile();
            throw new Exception("Among given input row indices, there are invalid/ exceeding indices found campared to all existing row indices of file.");
        }
        StringBuilder replacedData = new StringBuilder();
        for (int i = 0; i <= lastRowIndex; i++) {
            if (!ipRowIndexList.contains(i)) {
                // String all the rows data into the new string builder replacedData
                // As per if condition, it will not append the row if it is a part of input row index list
                replacedData.append(rows[i] + endOfLine);
            }
        }
        csvDataToUpdate = replacedData.toString();
        return this;
    }
    
    /**
     * If at all required to remove any of the existing column completely (column values including column header), then this method helps to perform the same.
     * 
     * @param columnIndex
     *            All the required column indexes needs to be removed.
     * @throws Exception 
     */
    public CsvUpdater removeColumns(Integer... columnIndex) throws Exception {
        
        String[] rows = csvDataToUpdate.split(endOfLine); // Getting all the rows in array
        int headersLastIndex = rows[0].split(delimiter).length - 1;
        // Below code identifies if any of the column index is exceeding the limit of last column index in the file, then throws exception.
        List<Integer> ipColumnIndexes = new ArrayList<>(Arrays.asList(columnIndex));
        if(ipColumnIndexes.stream().anyMatch(i -> i > headersLastIndex)) {
            keepOrgFile();
            throw new Exception("Given input column index array is having invalid or non-existing column indexes in the file, current maximum column index is- " + ipColumnIndexes);
        }
        
        // The below code is to remove the cell value of each row's column index.
        StringBuilder replacedData = new StringBuilder();
        for (String row : rows) {
            // Loops through each row and picks each cell values into cells[]
            String resultedRow = ""; // It helps to store the one row data after removing each matching column index's cell value of that row
            List<String> cells = new ArrayList<>(Arrays.asList(row.replace(endOfLine, "").split(delimiter)));
            if (cells.get(0).length() > 0) { // If the row is completely empty, then it wont take for further process.
                for (int index = 0; index <= headersLastIndex; index++) {
                    try {
                            cells.get(index);
                    } catch (IndexOutOfBoundsException e) {
                            cells.add(""); // If the row is having lesser column indices than the given column index, considering the empty cell
                    }
                    if (!ipColumnIndexes.contains(index)) {
                            resultedRow = resultedRow + cells.get(index) + delimiter; // If given column index is not matching, then only re generates the row data
                    }
                }
                resultedRow = resultedRow.substring(0, resultedRow.lastIndexOf(delimiter)) + endOfLine; // Avoiding an delimiter in the end of row
            } else {
                    // If the complete row is empty, it should not be disturbed, and so maintaining the same empty line as it is.
                    resultedRow = endOfLine;
            }
            replacedData.append(resultedRow);
        }
        csvDataToUpdate = replacedData.toString();
        return this;
    }
    

    /**
     * If at all required to remove any of the existing columns completely, then this method helps to perform the same.
     * 
     * @param columnIndex
     *            All the required column header names needs to be removed.
     * @throws Exception 
     */
    public CsvUpdater removeColumns(String... columnHeaderNames) throws Exception {
        
        Integer[] ipColumnHeadersIndexes = new Integer[columnHeaderNames.length];
        int i = 0;
        for (String column : columnHeaderNames) {
            ipColumnHeadersIndexes[i] = getHeadersIfGivenHeaderExists(column).indexOf(column);
            i++;
        }
        return removeColumns(ipColumnHeadersIndexes);
    }
    
}
