package com.ecommerce.vo;

import java.util.List;

import com.ecommerce.entity.BeverageGoods;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductGoodsInfo {
	
	
	private List<BeverageGoods> orderGoodList;
	private GenericPageable genericPageable;
	
	
}
