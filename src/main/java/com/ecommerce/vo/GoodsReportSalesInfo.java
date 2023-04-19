package com.ecommerce.vo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GoodsReportSalesInfo {
	

	private int orderId;

	private String orderDate;

	private String customerId;

	private int goodId;

	private int goodsBuyPrice;

	private int buyQuantity;
	
	
}
