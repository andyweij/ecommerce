package com.ecommerce.vo;

import java.util.List;

import com.ecommerce.entity.BeverageGoods;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class GoodsDataInfo {

	
	private List<BeverageGoods> beverageGoods;
	private GenericPageable genericPageable;
	
}
