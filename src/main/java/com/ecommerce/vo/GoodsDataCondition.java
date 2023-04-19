package com.ecommerce.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GoodsDataCondition {

	private int goodsID;
	private  String goodsName;
	private int startPrice;
	private int endPrice;
	private String priceSort;
	private int quantity;
	private String status;
	
}
