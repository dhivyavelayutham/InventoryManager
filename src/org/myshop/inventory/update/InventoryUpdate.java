package org.myshop.inventory.update;

import org.myshop.inventory.datastore.InMemoryDataStore;
import org.myshop.inventory.report.InventoryReport;
import org.myshop.items.Item;

/**
 * Class to update the inventory when buy or sell operation takes place
 * 
 * @author dhivya velayutham
 *
 */
public class InventoryUpdate {

	String itemName = "";
	InMemoryDataStore inMemoryDataStore = InMemoryDataStore.getInstance();

	public InventoryUpdate ( String itemName ) {
		this.itemName = itemName;
	}

	/**
	 * Method to increase the item quantity while buying
	 * 
	 * @param count
	 */
	public void buyItems ( String count ) {
		try {
			Item item = inMemoryDataStore.getItem(itemName);
			
			if ( null != item ) {
				int quantity = Integer.parseInt(count);
				
				if ( quantity > 0 ) {
					int newCount = item.getStockCount() + quantity;
					item.setStockCount(newCount);
					inMemoryDataStore.update(item);
				} else {
					System.out.println("Failed to update: Buying quantity can not be negative");
				}
			} else {
				System.out.println("Failed to update: Item not found in the Inventory");
			}
		} catch ( NumberFormatException nmEx ) {
			System.out.println("Failed to update the item. Buying quantity format is wrong");
		}
	}

	/**
	 * Method to reduce the item quantity while selling
	 * 
	 * @param count
	 */
	public void sellItems ( String count ) {
		try {
			Item item = inMemoryDataStore.getItem(itemName);
			
			if ( null != item ) {
				// handle the negative case
				int itemQuantity = Integer.parseInt(count);
				if ( itemQuantity > 0 ) {
					int newCount = item.getStockCount() - itemQuantity;
					
					if ( newCount > 0 ) {
						item.setStockCount(newCount);
						inMemoryDataStore.update(item);
						InventoryReport.updateSoldItemValues(itemQuantity * (item.getSellingPrice() - item.getCostPrice()));
					} else {
						System.out.println("Failed to sell : Inventory does not have required quantity");
					}
				} else {
					System.out.println("Failed to update: Selling quantity can not be negative");
				}
			} else {
				System.out.println("Failed to update: Item not found in the Inventory");
			}
		} catch ( NumberFormatException nmEx ) {
			System.out.println("Failed to update the item. Selling Quantity format is wrong");
		}
	}

	/**
	 * Method to delete an item from Inventory
	 */
	public void deleteItem() {
		Item item = inMemoryDataStore.getItem(itemName);
		
		if ( null != item ) {
			inMemoryDataStore.delete(item);
		} else {
			System.out.println("Failed to delete: Item not found in the Inventory");
		}
	}

}
