package com.ecommerce.vo;

import java.time.LocalDateTime;

public interface GoodsReportSales {
	
	Long getGoodId();	
	Long getOrderId();	
	LocalDateTime getOrderDate();	
	String getCustomerName();	
	Long getGoodsBuyPrice();	
	Long getBuyQuantity();	
	String getGoodsName();
}
