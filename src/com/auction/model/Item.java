/**
 * 
 */
package com.auction.model;

/**
 * @author Karansinh
 *
 */
public class Item {
	
	private int itemId;
	private String itemName;
	private String description;
	private int bidPrice;
	private String itemImage;
		
	public Item(String itemName, String description, int bidPrice, String itemImage) {
		this.itemName = itemName;
		this.description = description;
		this.bidPrice = bidPrice;
		this.itemImage = itemImage;
	}

	public Item(int itemId, String itemName, String description, int bidPrice, String itemImage) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.bidPrice = bidPrice;
		this.itemImage = itemImage;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", description=" + description + ", bidPrice="
				+ bidPrice + ", itemImage=" + itemImage + "]";
	}
	
}
