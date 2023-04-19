package com.ecommerce.vo;

import java.util.List;

import com.ecommerce.entity.BeverageGoods;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GoodsDataInfo {
	
	private List<BeverageGoods> beverageGoods;
	private GenericPageable genericPageable;
	
	
}
