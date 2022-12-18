package common;

import java.io.Serializable;

public class ProductInStock implements Serializable{
	
	private String productName;
	private int amount;
	
	public ProductInStock(String productName, int amount)
	{
		this.productName=productName;
		this.amount=amount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	

}
