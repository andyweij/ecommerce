package com.ecommerce.vo;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class GoodsReportSalesInfo {
		
	List<GoodsReportSales> goodsReportSalesList;
	GenericPageable genericPageable;
}
