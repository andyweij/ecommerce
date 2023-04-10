package com.ecommerce.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class GoodsDataInfo {
	
	private int goodsID;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]{5,20}$", message = "text [a-zA-Z] length between 5 ~ 20")
	private String goodsName;
	 
	private String priceSort;
	 
	private int startPrice;
	 
	private int endPrice; 
	 
	private int quantity;
	 
	private String status;
	 
	private int currentPageNo;
	 
	private int pageDataSize;
	 
	private int pagesIconSize;
	
}
