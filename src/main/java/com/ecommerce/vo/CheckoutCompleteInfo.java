package com.ecommerce.vo;

import java.util.List;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class CheckoutCompleteInfo {
	
	private OrderCustomer customer;
	private List<GoodsVo> orderGoodList;
}
