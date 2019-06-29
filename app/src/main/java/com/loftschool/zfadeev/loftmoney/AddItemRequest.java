package com.loftschool.zfadeev.loftmoney;

class AddItemRequest {

	private int price;
	
	private String name;
	
	private String type;
	
	public AddItemRequest(final int price, final String name, final String type) {
		this.price = price;
		this.name = name;
		this.type = type;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(final int price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(final String type) {
		this.type = type;
	}
}
