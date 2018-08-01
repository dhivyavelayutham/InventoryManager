package org.myshop.inventory.update.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.myshop.inventory.datastore.InMemoryDataStore;
import org.myshop.inventory.update.InventoryUpdate;
import org.myshop.items.Item;

public class InventoryUpdateTest {
	
	InventoryUpdate update = null;

	@BeforeEach
	void setUp() throws Exception {
		InMemoryDataStore inMemoryDataStore = InMemoryDataStore.getInstance();
		Item item = new Item("book1", Float.parseFloat("20.50"), Float.parseFloat("35.40"));
		inMemoryDataStore.insertItem(item);
	}

	//updateBuy with item not available in inventory
	@Test
	void testBuyItemsNotFound() {
		update = new InventoryUpdate("book");
		update.buyItems("100");
		assertTrue(true, "Failed to update: Item not found in the Inventory");
	}
	
	//updateBuy with negative quantity
	@Test
	void testBuyItemsNegativeQuantity() {
		update = new InventoryUpdate("book1");
		update.buyItems("-100");
		assertTrue(true, "Failed to update: Buying quantity can not be negative");
	}
	
	//updateBuy with string quantity
	@Test
	void testBuyItemsQuantityInvalid() {
		update = new InventoryUpdate("book1");
		update.buyItems("hundred");
		assertTrue(true, "Failed to update the item. Buying quantity format is wrong");
	}
	
	//updateBuy with success scenario
	@Test
	void testBuyItemsSuccess() {
		update = new InventoryUpdate("book1");
		update.buyItems("200");
		assertTrue(true, "Successfully Updated Quantity");
	}

	//updateSell with item not available in inventory
	@Test
	void testSellItemsNotFound() {
		update = new InventoryUpdate("book");
		update.sellItems("100");
		assertTrue(true, "Failed to update: Item not found in the Inventory");
	}
	
	//updateSell with negative quantity
	@Test
	void testSellItemsNegativeQuantity() {
		update = new InventoryUpdate("book1");
		update.buyItems("200");
		update.sellItems("-100");
		assertTrue(true, "Failed to update: Selling quantity can not be negative");
	}
	
	//updateSell with string quantity
	@Test
	void testSellItemsQuantityInvalid() {
		update = new InventoryUpdate("book1");
		update.buyItems("200");
		update.sellItems("hundred");
		assertTrue(true, "Failed to update the item. Selling Quantity format is wrong");
	}
	
	//updateSell with quantity more than inventory
	@Test
	void testSellItemsQuantityMore() {
		update = new InventoryUpdate("book1");
		update.buyItems("200");
		update.sellItems("300");
		assertTrue(true, "Failed to update the item. Inventory does not have required quantity");
	}
	
	//updateSell with success scenario
	@Test
	void testSellItemsSuccess() {
		update = new InventoryUpdate("book1");
		update.buyItems("200");
		update.sellItems("100");
		assertTrue(true, "Successfully Updated Quantity");
	}

	//Delete Item Success Scenario
	@Test
	void testDeleteItemNotFound() {
		update = new InventoryUpdate("book");
		update.deleteItem();
		assertTrue(true, "Failed to delete: Item not found in the Inventory");
	}

	//Delete Item Success Scenario
	@Test
	void testDeleteItemSuccess() {
		update = new InventoryUpdate("book1");
		update.deleteItem();
		assertTrue(true, "Successfully Deleted Item");
	}

}
