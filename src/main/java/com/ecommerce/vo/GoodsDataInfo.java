package com.ecommerce.vo;

import java.util.List;

import javax.persistence.Table;

import com.ecommerce.entity.BeverageGoods;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class GoodsDataInfo {
	
	private int goodsID;
	
	private List<BeverageGoods> beverageGoods;
	private GenericPageable genericPageable;
	
}
