package Assignment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license

 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import static Assignment.Assignment.RED;
import static Assignment.Assignment.RESET;

import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class Tools {

    public static String getCurrentDatetime() {
        //format yyyy-mm-dd hh:mm:ss
        String datetime = java.time.LocalDateTime.now().toString();
        String[] datetimeArr = datetime.split("T");
        String[] dateArr = datetimeArr[0].split("-");
        String[] timeArr = datetimeArr[1].split("\\.")[0].split(":");
        return String.format("%s-%s-%s %s:%s:%s", dateArr[0], dateArr[1], dateArr[2], timeArr[0], timeArr[1], timeArr[2]);
    }

    public static String getIDStrFormat(char character, int id) {
        return String.format("%c%04d", character, id);
    }

    public static ArrayList<String[]> getBookListFromDB() {
        return FileHandler.readFileToArray(FileHandler.BOOK_STORE_DB);
    }

    public static ArrayList<String[]> getOrderListFromDB() {
        return FileHandler.readFileToArray(FileHandler.ORDER_DB);
    }

    public static ArrayList<String[]> getStationaryListFromDB() {
        return FileHandler.readFileToArray(FileHandler.STATIONARY_DB);
    }

    public static Order getOrderObjByID(String orderID) {
        ArrayList<String> orderList = FileHandler.getRowByMainID(FileHandler.ORDER_DB, orderID);
        ArrayList<Integer> bookQuantity = new ArrayList<>();
        ArrayList<Integer> stationaryQuantity = new ArrayList<>();
        
        String id = orderList.get(0);
        String[] bookOrderList = orderList.get(1).split(":");
        String[] bookQuantityList = orderList.get(2).split(":");
        String[] stationaryOrderList = orderList.get(3).split(":");
        String[] stationaryQuantityList = orderList.get(4).split(":");

        double totalPrice = Double.parseDouble(orderList.get(5));

        ArrayList<String> bookOrder, stationaryOrder;
        if (!bookOrderList[0].equals("")) {
            bookOrder = new ArrayList<>(Arrays.asList(bookOrderList));
        } else {
            bookOrder = new ArrayList<>();
        }
        for (String quantity : bookQuantityList) {
            if (quantity.equals("")) {
                break;
            }
            bookQuantity.add(Integer.parseInt(quantity));
        }

        if (!stationaryOrderList[0].equals("")) {
            stationaryOrder = new ArrayList<>(Arrays.asList(stationaryOrderList));
        } else {
            stationaryOrder = new ArrayList<>();
        }
        for (String quantity : stationaryQuantityList) {
            if (quantity.equals("")) {
                break;
            }
            stationaryQuantity.add(Integer.parseInt(quantity));
        }

        return new Order(id, bookOrder, bookQuantity, stationaryOrder, stationaryQuantity, totalPrice);
    }

    public static String getItemNameByID(String ID) {
        if (ID.charAt(0) == 'B') {
            return getBookNameByID(ID);
        } else {
            return getStationaryNameByID(ID);
        }
    }

    public static String getBookNameByID(String ID) {
        ArrayList<String> bookList = FileHandler.getRowByMainID(FileHandler.BOOK_STORE_DB, ID);
        return bookList.get(1);
    }

    public static String getStationaryNameByID(String ID) {
        ArrayList<String> stationaryList = FileHandler.getRowByMainID(FileHandler.STATIONARY_DB, ID);
        return stationaryList.get(1);
    }

    public static double getBookPriceByID(String ID) {
        ArrayList<String> bookList = FileHandler.getRowByMainID(FileHandler.BOOK_STORE_DB, ID);
        return Double.parseDouble(bookList.get(4));
    }

    public static double getStationaryPriceByID(String ID) {
        ArrayList<String> stationaryList = FileHandler.getRowByMainID(FileHandler.STATIONARY_DB, ID);
        return Double.parseDouble(stationaryList.get(4));
    }

    public static ArrayList<String[]> getAvailableBookList(){
        ArrayList<String[]> bookList = Tools.getBookListFromDB();
        bookList.removeIf(book -> parseInt(book[2]) == 0);
        return bookList;
    }

    public static ArrayList<String[]> getAvailableStationaryList(){
        ArrayList<String[]> stationaryList = Tools.getStationaryListFromDB();
        stationaryList.removeIf(stationary -> parseInt(stationary[2]) == 0);
        return stationaryList;
    }

    public static ArrayList<String[]> getAllBookByList(ArrayList<String> bookList){
        ArrayList<String[]> result = new ArrayList<>();
        for (String bookID : bookList) {
            result.add(getSelectedBookByID(bookID).toArray(new String[0]));
        }
        return result;
    }

     public static ArrayList<String> getSelectedBookByID(String bookID){
        return FileHandler.getRowByMainID(FileHandler.BOOK_STORE_DB, bookID);
     }

     public static ArrayList<String[]> getAllStationaryByList(ArrayList<String> stationaryList) {
        ArrayList<String[]> result = new ArrayList<>();
        for (String stationaryID : stationaryList) {
            result.add(getSelectedStationaryByID(stationaryID).toArray(new String[0]));
        }
        return result;
    }

     public static ArrayList<String> getSelectedStationaryByID(String stationaryID){
        return FileHandler.getRowByMainID(FileHandler.STATIONARY_DB, stationaryID);
     }

     public static boolean checkCurrentStockAvailable(String id, int quantity) {
        ArrayList<String> row = FileHandler.getRowByMainID(checkItemTypeByID(id), id);
        return Integer.parseInt(row.get(3)) >= quantity;
     }

     private static String checkItemTypeByID(String id) {
         if (id.charAt(0) == 'S') return FileHandler.STATIONARY_DB;
         else if (id.charAt(0) == 'B') return FileHandler.BOOK_STORE_DB;
         else return "";
     }

     private static int getItemStock(String id) {
        ArrayList<String> row = FileHandler.getRowByMainID(checkItemTypeByID(id), id);
        return Integer.parseInt(row.get(2));
     }

     public static void substractItemStock(String id, int quantity) {
        String filename = checkItemTypeByID(id);
        int currentStock = getItemStock(id);
        if (currentStock < quantity) {
            System.out.println(RED + "Not enough stock" + RESET);
            return;
        }
        quantity = currentStock - quantity;
         
        if (quantity == 0) {
             FileHandler.updateDataByID(filename, id, 5, String.valueOf(false));
        }
         
        try {
            StockFlowReport.writeStockToFile(id,-(currentStock - quantity),new Book().getStockAddDate());
        } catch (IOException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FileHandler.updateDataByID(filename, id, 2, String.valueOf(quantity));
     }

     public static void addItemStock(String id, int quantity) {
        String filename = checkItemTypeByID(id);
        int currentStock = getItemStock(id);
        if (currentStock < quantity) {
            System.out.println(RED + "Not enough stock" + RESET);
            return;
        }
        quantity = currentStock + quantity;
        FileHandler.updateDataByID(filename, id, 2, String.valueOf(quantity));
    }
     
     public static boolean checkItemStock(String id, int quantity) {
        int currentStock = getItemStock(id);
        return currentStock >= quantity;
    }
}
