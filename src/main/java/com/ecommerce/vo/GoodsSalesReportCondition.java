package com.ecommerce.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GoodsSalesReportCondition {
	private String startDate;
	private String endDate;
}
