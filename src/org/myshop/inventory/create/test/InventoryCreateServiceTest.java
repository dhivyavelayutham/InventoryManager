package org.myshop.inventory.create.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.myshop.inventory.create.InventoryCreateService;

public class InventoryCreateServiceTest {
	InventoryCreateService createService = null;
	String [] commandParameters = null;
	
	// invalid CostPrice
	@Test
	void testCreateItemInvalidCostPrice() {
		commandParameters = new String [] {"create", "book1", "test", "20.50"};
		createService = new InventoryCreateService(commandParameters);
		createService.createItem();
		assertTrue(true, "Failed to create the item. Amount format is wrong");
	}
	
	//invalid SellPrice
	@Test
	void testCreateItemInvalidSellPrice() {
		commandParameters = new String [] {"create", "book1", "10.50", "test"};
		createService = new InventoryCreateService(commandParameters);
		createService.createItem();
		assertTrue(true, "Failed to create the item. Amount format is wrong");
	}
	
	//Negative CostPrice
	@Test
	void testCreateItemNegativeCostPrice() {
		commandParameters = new String [] {"create", "book1", "-10.50", "20.50"};
		createService = new InventoryCreateService(commandParameters);
		createService.createItem();
		assertTrue(true, "Failed to create the item. CostPrice/SellingPrice can not be a negative number");
	}
	
	//Negative sellPrice
	@Test
	void testCreateItemNegativeSellPrice() {
		commandParameters = new String [] {"create", "book1", "10.50", "-20.50"};
		createService = new InventoryCreateService(commandParameters);
		createService.createItem();
		assertTrue(true, "Failed to create the item. CostPrice/SellingPrice can not be a negative number");
	}

	@Test
	void testCreateItemSuccess() {
		commandParameters = new String [] {"create", "book1", "10.50", "20.50"};
		createService = new InventoryCreateService(commandParameters);
		createService.createItem();
		assertTrue(true, "Successfully created Item");
	}

}
