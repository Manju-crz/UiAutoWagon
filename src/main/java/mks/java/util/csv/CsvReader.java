
package mks.java.util.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * It is containing different reusable methods to read the data from CsvFile however we want.
 * Mostly it provides all required methods that help in various scenarios
 * 
 * @author manjunath.ks (mks)
 *
 */
public class CsvReader {
    
    protected CsvFile csv = null;
    protected BufferedReader bufferedReader = null;
    protected FileReader fileReader = null;
    
    protected String delimiter = null;
    protected String endOfLine = null;
    
    protected String csvOriginalData = "";
    protected List<String> headers = new ArrayList<>();
    protected String[] rows = null;
    
    /**
     * It instantiates the CsvReader object based on the given CsvFile object
     * 
     * @param csvFile
     *            The instantiated reference object of CsvFile
     * @throws Exception 
     */
    public CsvReader(CsvFile csvFile) throws Exception {
        csv = csvFile;
        if (!csv.isCsvFileExist())
            throw new Exception("The given folder location or file is not existing in the system!");
        delimiter = csv.getDemiliter();
        endOfLine = csv.getEndOfLine();
        bufferedReader = new BufferedReader(new FileReader(csv.getCSVFile()));
    }
    
    /**
     * It helps to close and re open the file
     * 
     * @throws IOException
     */
    private void reloadBuffer() throws IOException {
        bufferedReader.close();
        bufferedReader = new BufferedReader(new FileReader(csv.getCSVFile()));
    }
    
    /**
     * Helps to close the csv file once after reading content.
     * 
     * @throws IOException
     */
    public void closeFileReader() throws IOException {
        bufferedReader.close();
    }
    
    /**
     * Helps to read the complete CSV file data in the form of String
     * 
     * @return Returns complete file data as string.
     * @throws IOException
     */
    public String readCSV() throws IOException {
        int i;
        if (csvOriginalData.length() < 1) {
            reloadBuffer();
            while ((i = bufferedReader.read()) != -1)
                csvOriginalData = csvOriginalData + ((char) i);
        }
        return csvOriginalData;
    }
    
    /**
     * Helps to get all header names of the file.
     * 
     * @return It returns all header names in the form of string of array
     * @throws IOException
     */
    public List<String> getHeaders() throws IOException {
        
        if (headers.size() < 1) {
            String firstLine = "";
            reloadBuffer();
            firstLine = bufferedReader.readLine();
            headers = new ArrayList<>(Arrays.asList(firstLine.split(delimiter)));
        }
        return headers;
    }
    
    /**
     * Helps to read all the column data of given column header name.
     * 
     * @param columnHeader
     *            String of column header name of which all values needs to be retrieved.
     * @return It returns all row values in the form of string of array
     * @throws Exception 
     */
    public List<String> getColumnData(String columnHeader) throws Exception {
        
        List<String> existingHeaders = getHeaders();
        if (!existingHeaders.contains(columnHeader))
            throw new Exception(String.format("Given column name header '%s' is not existing in the given file", columnHeader));
        int headerIndex = existingHeaders.indexOf(columnHeader);
        List<String> columnData = getColumnData(headerIndex);
        columnData.remove(columnData.get(0));
        return columnData;
    }
    
    /**
     * It helps to get the rows in the form of string array
     * 
     * @return
     * @throws IOException
     */
    private String[] getRows() throws IOException {
        if (rows == null) {
            rows = readCSV().split(endOfLine);
        }
        return rows;
    }
    
    /**
     * Helps to read all the column data of given column header index.
     * 
     * @param columnIndex
     *            Integer of column name of which all values needs to be retrieved. Note- Columns' header index starts from zero
     * @return It returns all column values in the form of string of array
     * @throws Exception 
     */
    public List<String> getColumnData(int columnIndex) throws Exception {
        
        // It finds the given header index is existing, if not then here only throws error.
        if (columnIndex >= getHeaders().size()) {
            closeFileReader();
            throw new Exception("The given index value of column is out of existing column indexes");
        }
        String[] rows = getRows(); // It captures all the rows of file
        List<String> columnData = new ArrayList<>();
        for (String row : rows) {
            try {
                columnData.add(row.split(delimiter)[columnIndex]); // Identifies each row's cell value based on the given column index
            } catch (ArrayIndexOutOfBoundsException e) {
                columnData.add(""); // In between the rows, if any row is blank at columnIndex position, then returns the empty string
            }
        }
        return columnData;
    }
    
    /**
     * Helps to read the complete one row data based on given row index
     * 
     * @param rowIndex
     *            Integer of row of which all row data needs to be retrieved. Note- rows' index starts from zero including first row of headers
     * @return It returns all row values in the form of string of array
     * @throws Exception 
     */
    public List<String> getRowDataAt(int rowIndex) throws Exception {
        
        String[] rows = getRows();
        if (rowIndex >= rows.length) {
            closeFileReader();
            throw new Exception("The given index value of row is out of existing row indexes");
        }
        return new ArrayList<>(Arrays.asList(rows[rowIndex].split(delimiter)));
    }
    
    /**
     * Helps to find all the matching row indexes available in the CSV file.
     * Rows indexes are identified based on the given input row data matching with available row data in csv file
     * Say below is my CSV file data.
     * 
     * Mail, Company, City (row index - 0) (first row)
     * a@mail.com aaa xxx (row index - 1)
     * b@mail.com bbb yyy (row index - 2)
     * c@mail.com ccc zzz (row index - 3)
     * a@mail.com aaa pqr (row index - 4)
     * 
     * case 1:
     * If we want to identify the combination:
     * Mail = a@mail.com; Company = aaa; City = xxx
     * Then, it should return me the index value 1 in the form of List<Integer>.
     * Here the input will be in the form of
     * Map<String, String> ipMap = new HashMap<String, String>();
     * ipMap.put("Mail", "a@mail.com");
     * ipMap.put("Company", "aaa");
     * ipMap.put("City", "xxx");
     * This input ipMap indicates one record to check its existence.
     * 
     * case 2:
     * Similarly we can reduce the input combination to 2 values also
     * ipMap.put("Mail", "a@mail.com");
     * ipMap.put("Company", "aaa");
     * Then, it should return me two row indexes - 1 and 4
     * This returned values will be in List<Integer>
     * 
     * case 3:
     * If at all use non matching input record like below-
     * ipMap.put("Mail", "a@mail.com");
     * ipMap.put("Company", "bbb");
     * ipMap.put("City", "zzz");
     * It returns empty List<String>
     * 
     * @param rowData
     *            Input map of a CSV data should have the grid header string as a map key the respected value as map value;
     *            Syntax: map.put("First", "<lead_first_name>"); map.put("email", "<lead_email_address>"); Here, First & email are the CSV column headers.
     *            This map input can have all required column headers and values combination.
     * @return Returns all the indexes of rows with matching input rowData, where the index starts from zero
     * @throws Exception 
     */
    public List<Integer> getMatchingRowIndexes(Map<String, String> rowData) throws Exception {
        
        // This code will check if all given input headers are existing in the CSV.
        List<String> csvHeaders = getHeaders();
        Set<String> expHeaders = rowData.keySet();
        if (!csvHeaders.containsAll(expHeaders))
            throw new Exception("Given input csv headers are not present in the provided csv file");
        
        // Below code will retrieve indexes of all matching headers' row indexes if value is matching as per input data
        Map<String, List<Integer>> csvColumnValuesIndexes = new HashMap<String, List<Integer>>();
        for (String header : expHeaders) {
            List<String> headerValues = getColumnData(header);
            List<Integer> tempList = IntStream.range(0, headerValues.size()).boxed().filter(i -> headerValues.get(i).equals(rowData.get(header))).collect(Collectors.toList());
            csvColumnValuesIndexes.put(header, tempList);
        }
        
        Map<String, List<Integer>> csvColumnValuesIndexesCopy = new HashMap<String, List<Integer>>(csvColumnValuesIndexes);
        
        // Picking up the first input header index list retrieved from above steps.
        List<Integer> foundRows = new ArrayList<>();
        boolean foundMatch = false;
        String header = expHeaders.iterator().next();
        List<Integer> firstHeaderIndexList = csvColumnValuesIndexes.get(header);
        
        // Checks the existence of headers' row values index list, in all other headers index list. If finds in all then captures that row index value into foundRows list.
        for (Integer value : firstHeaderIndexList) {
            for (String key : csvColumnValuesIndexesCopy.keySet()) {
                
                List<Integer> secListIndexes = csvColumnValuesIndexesCopy.get(key);
                if (secListIndexes.contains(value)) {
                    foundMatch = true;
                } else {
                    foundMatch = false;
                    break;
                }
            }
            if (foundMatch) {
                foundRows.add(value);
            }
            foundMatch = false;
        }
        return foundRows;
    }
    
    /**
     * Csv file should have first row always as header row, so it helps to check the first row has some data.
     * 
     * @return Returns true if the first row is empty, else returns false.
     * @throws IOException
     */
    public boolean isFirstRowDataEmpty() throws IOException {
        String firstRecord = bufferedReader.readLine();
        if ((firstRecord == null) || (firstRecord.trim().length() < 1)) {
            closeFileReader();
            return true;
        }
        return false;
    }
    
    /**
     * Checks if all the given headers are present in the file.
     * 
     * @return If all given headers are existing then returns true. Else any of the header is not existing, then returns false.
     * @throws IOException
     */
    public boolean isHeadersPresent(String... headers) throws IOException {
        return getHeaders().containsAll(new ArrayList<>(Arrays.asList(headers)));
    }
    
    /**
     * Checks only the given headers are present in the file with the same order.
     * 
     * @return If all the total headers present in file and total headers of input not matching returns false. Only if the header values is same with the same order, then returns true.
     * @throws IOException
     */
    public boolean isHeadersIdentical(String... headers) throws IOException {
        return getHeaders().equals(new ArrayList<>(Arrays.asList(headers)));
    }
    
    /**
     * Gives total number of records (rows, except first row which is a header row.)
     * 
     * @return The integer of total records count.
     * @throws IOException
     */
    public int getTotalRecords() throws IOException {
        return getRows().length - 1;
    }
    
}
