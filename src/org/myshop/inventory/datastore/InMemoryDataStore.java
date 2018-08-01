package org.myshop.inventory.datastore;

import java.util.HashMap;

import org.myshop.inventory.report.InventoryReport;
import org.myshop.items.Item;

/**
 * Data source to maintain the inventory details and
 * calculate profit since last report
 * 
 * @author dhivya velayutham
 */
public class InMemoryDataStore {
	
	private static InMemoryDataStore dataStore = new InMemoryDataStore();
	
	private HashMap<String, Item> inventory = new HashMap<>();
	
	private float profitSinceLastReport = 0;
	
	public static InMemoryDataStore getInstance() {
		return dataStore;
	}
	
	/**
	 * Insert item
	 * @param item
	 */
	public void insertItem ( Item item ) {
		inventory.put(item.getItemName(), item);
	}

	/**
	 * Update item quantity
	 * @param item
	 */
	public void update ( Item item ) {
		insertItem(item);
	}

	/**
	 * Delete the item from inventory
	 * 
	 * @param itemName
	 */
	public void delete ( Item item ) {
		float deletedItemValue = item.getCostPrice() * item.getStockCount();
		InventoryReport.updateDeletedItemValue(deletedItemValue);
		inventory.remove(item.getItemName());
	}

	/**
	 * Get property from last report
	 * 
	 * @return <float>
	 */
	public float getProfitSinceLastReport() {
		return profitSinceLastReport;
	}

	/**
	 * Set previous profit into data source
	 *  
	 * @param profitSinceLastReport
	 */
	public void setProfitSinceLastReport ( float profitSinceLastReport ) {
		this.profitSinceLastReport = profitSinceLastReport;
	}

	/**
	 * Get Inventory Map
	 * 
	 * @return <HashMap>
	 */
	public HashMap<String, Item> getInventory(){
		return inventory;
	}
	
	/** 
	 * Get Item from Inventory
	 * 
	 * @param itemName
	 * @return <Item>
	 */
	public Item getItem ( String itemName ) {
		return inventory.get(itemName);		
	}
}