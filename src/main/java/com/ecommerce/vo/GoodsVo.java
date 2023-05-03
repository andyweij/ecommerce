package com.ecommerce.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"goodsID"} ) 
@Data
public class GoodsVo {
	
//	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private long goodsID;
	
	private String goodsName;
	private int goodsPrice;
	private int goodsQuantity;
	private MultipartFile file;
	private String goodsImageName;	
	private String status;	
	private String description;
}				   
