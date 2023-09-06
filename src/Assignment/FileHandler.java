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
    public static final String ORDER_DB = "orders";
    public static final String BOOK_STORE_DB = "Book";
    public static final String STATIONARY_DB = "Stationary";
    public static final String TRANSACTION_DB = "transaction";
    public static final String MEMBER_DB = "Member";

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
        String path = filename + ".txt";
        ArrayList<String[]> content = new ArrayList<>();
        ArrayList<String> fileContent = readFile(path);
        for(String ele : fileContent){
            content.add(ele.split("\\|"));
        }
        return content;
    }

    public static void writeFile(String filename, String content){
        try{
            String path = filename + ".txt";
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

    public static boolean updateDataByID(String filename, String id, int index, String data){
        ArrayList<String[]> fileContent = readFileToArray(filename);
        for (String[] strings : fileContent) {
            if (strings[0].equals(id)) {
                strings[index] = data;
                break;
            }
        }
        return rewriteFile(filename, fileContent);
    }

    public static ArrayList<String> getRowByMainID(String filename, String id) {
        ArrayList<String[]> fileContent = readFileToArray(filename);
        ArrayList<String> row = new ArrayList<>();
        for (String[] strings : fileContent) {
            if (strings[0].equals(id)) {
                Collections.addAll(row, strings);
                break;
            }
        }
        return row;
    }

    public static ArrayList<String> getColumnByType(String filename, int index) {
        ArrayList<String[]> fileContent = readFileToArray(filename);
        ArrayList<String> column = new ArrayList<>();
        for (String[] strings : fileContent) {
            column.add(strings[index]);
        }
        return column;
    }

    public static boolean rewriteFile(String filename, ArrayList<String[]> data){
        File myFile = new File(filename + ".txt");
        myFile.delete();
        return writeArrToFile(filename, data);
    }

    public static boolean checkIDExist(String filename, String id){
        ArrayList<String> fileContent = getRowByMainID(filename, id);
        return fileContent.size() > 0;
    }

    public static int getLastRowID(String filename){
        ArrayList<String[]> fileContent = readFileToArray(filename);
        if (fileContent.size() == 0) {
            return 0;
        }
        String lastLineID = fileContent.get(fileContent.size() - 1)[0];
        String lastID = lastLineID.replaceAll("[^0-9]", "");
        return Integer.parseInt(lastID);
    }
}