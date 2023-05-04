package com.ecommerce.vo;

import java.util.List;

import com.ecommerce.entity.BeverageGoods;
import com.ecommerce.entity.BeverageMember;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckoutCompleteInfo {
	
	private BeverageMember customer;
	private List<BeverageGoods> orderGoodList;

}
