package com.ecommerce.vo;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;


@Data
public class GoodsVo {

	
	private String goodsName;
	private int goodsPrice;
	private int goodsQuantity;
	private MultipartFile file;
	private String goodsImageName;	
	private String status;	
	private String DESCRIPTION;
}
