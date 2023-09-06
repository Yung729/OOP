/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author user
 */
public class FileHandler {
    private static final String filePath = "src/sales/";
    public static final String ORDER_DB = "orders";
    public static final String BOOK_STORE_DB = "book_store";
    public static final String STATIONARY_DB = "stationary_store";
    public static final String AUTHOR_DB = "author";
    public static final String TRANSACTION_DB = "transaction";
    public static final String MEMBER_DB = "members";

    public static ArrayList<String> readFile(String path){
        ArrayList<String> content = new ArrayList<>();
        try{
            File myFile = new File(path);
            Scanner myReader = new Scanner(myFile);

            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                content.add(data);
            }
            myReader.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return content;
    }

    public static ArrayList<String[]> readFileToArray(String filename){
        String path = filePath + filename + ".txt";
        ArrayList<String[]> content = new ArrayList<>();
        ArrayList<String> fileContent = readFile(path);
        for(String ele : fileContent){
            content.add(ele.split("\\|"));
        }
        return content;
    }

    public static void writeFile(String filename, String content){
        try{
            String path = filePath + filename + ".txt";
            File myFile = new File(path);
            if(!myFile.exists()){
                myFile.createNewFile();
            }
            FileWriter myWriter = new FileWriter(path, true);
            myWriter.write(content + "\n");
            myWriter.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void writeArrToFile(String filename, String[] data){
        String content = String.join("|", data);
        writeFile(filename, content);
    }

    public static boolean writeArrToFile(String filename, ArrayList<String[]> data){
        for(String[] ele : data){
            writeArrToFile(filename, ele);
        }
        return true;
    }

    public static boolean updateDataByID(String filename, String id, String type, String data){
        ArrayList<String[]> fileContent = readFileToArray(filename);
        String[] header = fileContent.get(0);
        int index = getDataColumn(header, type);

        for(int i = 1; i < fileContent.size(); i++){
            if(fileContent.get(i)[0].equals(id)){
                fileContent.get(i)[index] = data;
                break;
            }
        }
        return rewriteFile(filename, fileContent);
    }

    public static ArrayList<String> getRowByMainID(String filename, String id) {
        ArrayList<String[]> fileContent = readFileToArray(filename);
        ArrayList<String> row = new ArrayList<>();
        for(int i = 1; i < fileContent.size(); i++){
            if(fileContent.get(i)[0].equals(id)){
                Collections.addAll(row, fileContent.get(i));
                break;
            }
        }
        return row;
    }

    public static ArrayList<String> getRowByType(String filename, String type, String value) {
        ArrayList<String[]> fileContent = readFileToArray(filename);
        ArrayList<String> row = new ArrayList<>();
        int index = getDataColumn(fileContent.get(0), type);
        for(int i = 1; i < fileContent.size(); i++){
            if(fileContent.get(i)[index].equals(value)){
                row.addAll(Arrays.asList(fileContent.get(i)));
                break;
            }
        }
        return row;
    }

    public static ArrayList<String> getColumnByType(String filename, String type) {
        ArrayList<String[]> fileContent = readFileToArray(filename);
        ArrayList<String> column = new ArrayList<>();
        int index = getDataColumn(fileContent.get(0), type);
        for(int i = 1; i < fileContent.size(); i++){
            column.add(fileContent.get(i)[index]);
        }
        return column;
    }

    public static boolean rewriteFile(String filename, ArrayList<String[]> data){
        File myFile = new File(filePath + filename + ".txt");
        myFile.delete();
        return writeArrToFile(filename, data);
    }

    public static int getDataColumn(String[] header, String type){
        int index = 0;
        for(int i = 0; i < header.length; i++){
            if(header[i].equals(type)){
                index = i;
                break;
            }
        }
        return index;
    }

    public static boolean checkIDExist(String filename, String id){
        ArrayList<String> fileContent = getRowByMainID(filename, id);
        return fileContent.size() > 0;
    }

    public static int getLastRowID(String filename){
        ArrayList<String[]> fileContent = readFileToArray(filename);
        //remove header
        fileContent.remove(0);
        if (fileContent.size() == 0) {
            return 0;
        }
        String lastLineID = fileContent.get(fileContent.size() - 1)[0];
        String lastID = lastLineID.replaceAll("[^0-9]", "");
        return Integer.parseInt(lastID);
    }
}