package com.ecommerce.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = {"goodsID"})
@Table(name = "BEVERAGE_GOODS")
@Entity
public class BeverageGoods {
	
	@Id
	@Column(name = "GOODS_ID")
	private String goodsID;
	
	@Column(name = "GOODS_NAME")
	private String goodsName;
	
	@Column(name = "PRICE")
	private int goodsPrice;
	
	@Column(name = "QUANTITY")
	private int goodsQuantity;
	
	@Column(name = "IMAGE_NAME")
	private String goodsImageName;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "DESCRIPTION")
	private String DESCRIPTION;
	
}
