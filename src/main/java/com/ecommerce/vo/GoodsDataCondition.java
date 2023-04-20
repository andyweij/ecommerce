package com.ecommerce.vo;

import javax.annotation.Nullable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
public class GoodsDataCondition {

	private Integer goodsID;

	private String goodsName;

	private Integer startPrice;

	private Integer endPrice;

	private String priceSort;

	private Integer quantity;

	private String status;
	
}
