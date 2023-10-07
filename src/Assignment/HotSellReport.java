/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author user
 */
import static Assignment.Assignment.clearScreen;
import static Assignment.Assignment.logo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class HotSellReport extends Report {

    private final ArrayList<Order> orderList;
    private final HashMap<String, Integer> itemsMap;

    public HotSellReport() {
        super();
        orderList = new ArrayList<>();
        itemsMap = new HashMap<>();
        initOrderList();
    }

    private void initOrderList() {
        for (Transaction transaction : super.getTransactionList()) {
            orderList.add(transaction.getOrder());
        }
        analyseItemsData();
    }

    private void analyseItemsData() {
        for (Order order : orderList) {
            for (int i = 0; i < order.getBookOrder().size(); i++) {
                String bookID = order.getBookOrder().get(i);
                if (itemsMap.containsKey(bookID)) {
                    itemsMap.put(bookID, itemsMap.get(bookID) + order.getBookQuantity().get(i));
                } else {
                    if (order.getBookQuantity().size() > 0) {
                        itemsMap.put(bookID, order.getBookQuantity().get(i));
                    }
                }
            }
            for (int i = 0; i < order.getStationaryOrder().size(); i++) {
                String stationaryID = order.getStationaryOrder().get(i);
                if (itemsMap.containsKey(stationaryID)) {
                    itemsMap.put(stationaryID, itemsMap.get(stationaryID) + order.getStationaryQuantity().get(i));
                } else {
                    if (order.getStationaryQuantity().size() > 0) {
                        itemsMap.put(stationaryID, order.getStationaryQuantity().get(i));
                    }
                }
            }
        }
    }

    private HashMap<String, Integer> getFilteredMap() {
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        itemsMap.entrySet()
                .stream()
                .sorted((HashMap.Entry.<String, Integer>comparingByValue().reversed()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        return sortedMap;
    }

    @Override
    public void printReport() {
        HashMap<String, Integer> filteredMap = getFilteredMap();
        int count = 0;
        clearScreen();
        logo();
        System.out.println("==================================================");
        System.out.println("=                 Hot Sell Report                =");
        System.out.println("==================================================");
        System.out.printf("= %-8s\t%-20s\t%-8s  =\n", "Item ID", "Item Name", "Quantity");
        System.out.println("==================================================");
        for (String itemID : filteredMap.keySet()) {
            if (count == 5) {
                break;
            }
            System.out.printf("| %-8s\t%-20s\t%-8d  |\n", itemID, Tools.getItemNameByID(itemID), filteredMap.get(itemID));
            count++;
        }
        System.out.println("==================================================");
        Assignment.systemPause();
    }
}
