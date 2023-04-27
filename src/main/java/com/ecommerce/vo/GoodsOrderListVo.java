package com.ecommerce.vo;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GoodsOrderListVo {
		
	List<GoodsOrderVo> goodsReportSalesList;
	GenericPageable genericPageable;
}
