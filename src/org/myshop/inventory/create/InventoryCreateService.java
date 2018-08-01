package org.myshop.inventory.create;

import org.myshop.inventory.datastore.InMemoryDataStore;
import org.myshop.items.Item;

/**
 * Class to create an Inventory
 * 
 * @author dhivya velayutham
 *
 */
public class InventoryCreateService {
	
	String itemName = "";
	String costPrice = "";
	String sellingPrice = "";
	
	InMemoryDataStore inMemoryDataStore = InMemoryDataStore.getInstance();
	
	public InventoryCreateService(){}
	
	public InventoryCreateService(String[] commandParameters) {
		this.itemName = commandParameters[1];
		this.costPrice = commandParameters[2];
		this.sellingPrice = commandParameters[3];
	}

	/**
	 * Method to create an Item
	 * 
	 * 1. Create the item with the price details mentioned
	 * 2. Update the In Memory Data Store for report generation 
	 */
	public void createItem() {
		
		try {
			float costPrc = Float.parseFloat(costPrice);
			float sellPrc = Float.parseFloat(sellingPrice);
			
			if ( costPrc > 0 && sellPrc > 0 ) {
				Item item = new Item(itemName, costPrc, sellPrc);
				inMemoryDataStore.insertItem(item);
			} else {
				System.out.println("Failed to create the item. CostPrice/SellingPrice can not be a negative number");
			}
		} catch ( NumberFormatException nmEx ) {
			System.out.println("Failed to create the item. Amount format is wrong");
		}
	}
}