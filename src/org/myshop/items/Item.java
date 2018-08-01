package org.myshop.items;

/**
 * Item Object
 * 
 * @author dhivya velayutham
 *
 */
public class Item {

	private String itemName = "";
	private float costPrice = 0;
	private float sellingPrice = 0;
	private int stockCount = 0;
	
	public Item ( String itemName2, float costPrice2, float sellingPrice2 ) {
		this.itemName = itemName2;
		this.costPrice = costPrice2;
		this.sellingPrice = sellingPrice2;
	}
	
	/**
	 * Get Item Name
	 * 
	 * @return <String>
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * Set ItemName
	 * 	
	 * @param itemName
	 */
	public void setItemName ( String itemName ) {
		this.itemName = itemName;
	}
	
	/**
	 * Get Item Price
	 * 
	 * @return <float>
	 */
	public float getCostPrice() {
		return costPrice;
	}
	
	/**
	 * Set Item Price
	 * 
	 * @param costPrice
	 */
	public void setCostPrice ( float costPrice ) {
		this.costPrice = costPrice;
	}
	
	/**
	 * Get Selling Price of an Item
	 * 
	 * @return <float>
	 */
	public float getSellingPrice() {
		return sellingPrice;
	}
	
	/**
	 * Set Selling Price of an Item
	 * 
	 * @param sellingPrice
	 */
	public void setSellingPrice ( float sellingPrice ) {
		this.sellingPrice = sellingPrice;
	}
	
	/**
	 * Get Stock Count
	 * 
	 * @return <int>
	 */
	public int getStockCount() {
		return stockCount;
	}

	/**
	 * Set Stock Count
	 * 
	 * @param stockCount
	 */
	public void setStockCount ( int stockCount ) {
		this.stockCount = stockCount;
	}
	
	@Override
	public boolean equals ( Object obj ) {
		return this.itemName.equals(((Item)obj).itemName);
	}
	
	@Override
	public int hashCode() {
		return this.itemName.length();
	}

	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", costPrice=" + costPrice + ", sellingPrice=" + sellingPrice
				+ ", stockCount=" + stockCount + "]";
	}
	
}
