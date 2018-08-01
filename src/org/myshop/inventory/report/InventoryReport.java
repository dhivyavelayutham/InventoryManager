package org.myshop.inventory.report;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.myshop.inventory.datastore.InMemoryDataStore;
import org.myshop.items.Item;

/**
 * Class to Generate Consolidated Report
 * 
 * @author dhivya velayutham
 *
 */
public class InventoryReport {

	private static float previousReportedProfit = 0;
	private static float deletedItemValue = 0;
	private static float soldItemValues = 0;
	
	private InMemoryDataStore inMemoryDataStore = InMemoryDataStore.getInstance();

	/**
	 * Method that format the report Header
	 */
	private void printReportHeader() {
		System.out.println("\n");
		System.out.println(String.format("%s",
				"----------------------------------------------------------------------------------------------------------------"));
		System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s", "Item Name", "|", "Bought At",
				"|", "Sold At", "|", "AvailabilityQty", "|", "Value"));
		System.out.println(String.format("%s",
				"----------------------------------------------------------------------------------------------------------------"));
	}

	/**
	 * Method to generate the report with profit details
	 */
	public void generateReport() {
		HashMap<String, Item> inventory = inMemoryDataStore.getInventory();
		
     	// sort the result before display 
        TreeMap<String, Item> sortedInventoryDataStore = new TreeMap<>();
        sortedInventoryDataStore.putAll(inventory);
		
		System.out.println("INVENTORY REPORT");
		printReportHeader();
		
		float totalValue =0;
		for (Entry<String, Item> entry : sortedInventoryDataStore.entrySet()) {
			
			Item item = (Item) entry.getValue();
			float value = item.getCostPrice() * item.getStockCount();
			
			System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s %15s %10s", item.getItemName(), "|",
					roundOff(item.getCostPrice()), "|", roundOff(item.getSellingPrice()), "|", item.getStockCount(), "|", roundOff(value)));
			totalValue = totalValue + value;
		}
		
		System.out.println(String.format("%s",
				"----------------------------------------------------------------------------------------------------------------"));
		System.out.println(String.format("%s %91s","Total Value",roundOff(totalValue)));
		float currentProfit = soldItemValues - deletedItemValue;
		System.out.println(String.format("%s %74s","Profit since previous report",roundOff((currentProfit - previousReportedProfit))));
		
		previousReportedProfit = currentProfit;
		deletedItemValue = 0;
		soldItemValues = 0;
	}
	
	private String roundOff( float value ) {
		final DecimalFormat number = new DecimalFormat("#.00");
		return number.format(value).toString();
	}
	
	/**
	 * Update Deleted Item Value
	 * 
	 * @param itemValues
	 */
	public static void updateDeletedItemValue ( float itemValues ) {
		deletedItemValue = deletedItemValue + itemValues;
	}
	
	/** 
	 * Update Sold Item value
	 * 
	 * @param itemValues
	 */
	public static void updateSoldItemValues ( float itemValues ) {
		soldItemValues = soldItemValues + itemValues;
	}
}