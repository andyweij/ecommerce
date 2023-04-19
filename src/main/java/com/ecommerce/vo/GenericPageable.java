package com.ecommerce.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenericPageable {
	private int currentPageNo;
	private int pageDataSize;
	private int pagesIconSize;
}
