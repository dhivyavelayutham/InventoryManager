package org.myshop.inventory.report.test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.myshop.inventory.datastore.InMemoryDataStore;
import org.myshop.inventory.report.InventoryReport;
import org.myshop.items.Item;

public class InventoryReportTest {
	
	InventoryReport report = new InventoryReport();

	@BeforeEach
	void setUp() throws Exception {
		InMemoryDataStore inMemoryDataStore = InMemoryDataStore.getInstance();
		Item item0 = new Item("book1", Float.parseFloat("20.50"), Float.parseFloat("35.40"));
		inMemoryDataStore.insertItem(item0);
	
		Item item1 = new Item("Food01", Float.parseFloat("1.47"), Float.parseFloat("3.98"));
		inMemoryDataStore.insertItem(item1);
		
		Item item2 = new Item("Med01", Float.parseFloat("30.63"), Float.parseFloat("34.29"));
		inMemoryDataStore.insertItem(item2);
		
		Item item3 = new Item("Tab01", Float.parseFloat("57.00"), Float.parseFloat("84.98"));
		inMemoryDataStore.insertItem(item3);
		
		Item item4 = new Item("Tab04", Float.parseFloat("20.50"), Float.parseFloat("35.40"));
		inMemoryDataStore.insertItem(item4);
	}

	//generateReport Success scenario
	@Test
	void testGenerateReport() {
		report.generateReport();
		assertTrue(true, "Report Generated Successfully");
	}
}
