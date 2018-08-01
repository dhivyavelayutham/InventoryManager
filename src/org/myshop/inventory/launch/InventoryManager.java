package org.myshop.inventory.launch;

import java.util.Scanner;
import org.myshop.inventory.create.InventoryCreateService;
import org.myshop.inventory.report.InventoryReport;
import org.myshop.inventory.update.InventoryUpdate;

/**
 * Inventory Management
 * Created for Barclay Design problem 
 *
 * @author dhivya velayutham
 *
 */
public class InventoryManager {

	/**
	 * Method to process various commands
	 * create : create new inventory service
	 * updatebuy : update the quantity bought earlier
	 * updatesell : update the quantity sold before
	 * delete : delete the item from inventory
	 * report : generate the report with profit calculation
	 * 
	 * @param command
	 */
	private void processCommand(String command) {
		String[] commandParameters = command.split(" ");
		String operation = null != commandParameters[0] ? commandParameters[0].toLowerCase() : "";
		
		switch ( operation )
		{
			case "create": {
				
				if ( null != commandParameters &&  commandParameters.length == 4 ) {
					InventoryCreateService inventoryCreateService = new InventoryCreateService(commandParameters);
					inventoryCreateService.createItem();
				} else {
					System.out.println("Invalid create command. USAGE: create <itemname> <costprice> <sellingprice>");
				}
				break;
			}
			case "updatebuy": {
				
				if ( null != commandParameters &&  commandParameters.length == 3 ) {
					InventoryUpdate inventoryUpdate = new InventoryUpdate(commandParameters[1]);
					inventoryUpdate.buyItems(commandParameters[2]);
				} else {
					System.out.println("Invalid create command. USAGE: updateBuy <itemname> <quantity>");
				}
				break;
			}
			case "updatesell": {
				
				if ( null != commandParameters &&  commandParameters.length == 3 ) {
					InventoryUpdate inventoryUpdate = new InventoryUpdate(commandParameters[1]);
					inventoryUpdate.sellItems(commandParameters[2]);
				} else {
					System.out.println("Invalid create command. USAGE: updateSell <itemname> <quantity>");
				}	
				break;
			}
			case "delete": {
				if ( null != commandParameters &&  commandParameters.length == 2 ) {
					InventoryUpdate inventoryUpdate = new InventoryUpdate(commandParameters[1]);
					inventoryUpdate.deleteItem();
				} else {
					System.out.println("Invalid create command. USAGE: delete <itemname>");
				}
				break;
			}
			case "report": {
				InventoryReport inventoryReport = new InventoryReport();
				inventoryReport.generateReport();
				break;
			}
			default:{
				System.out.println("Operation not supported");
			}
		}
		
	}

	/**
	 * main() which read the input command from the user
	 * and initiate the inventory management
	 * 
	 * @param args
	 */
	public static void main ( String[] args ) {
		InventoryManager inventoryManager = new InventoryManager();
		Scanner sc = new Scanner(System.in);
		
        while ( sc.hasNextLine() ) {
        	String command = sc.nextLine();
        	
        	if ( command.equalsIgnoreCase("exit") ) {
        		System.exit(0);
        	}
        	
        	//start inventory management
        	inventoryManager.processCommand(command);
        }
	}

}
