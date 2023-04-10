package com.ecommerce.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecommerce.vo.GoodsReportSalesInfo;

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
@Table(name = "BEVERAGE_GOODS" ,schema="LOCAL_DB")
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
	
	@OneToMany(
			fetch = FetchType.LAZY,
//			cascade = {CascadeType.MERGE, CascadeType.REMOVE},
			cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "geography"
		)
//		@JoinColumn(name="GEOGRAPHY_ID")
		private List<GoodsReportSalesInfo> goodsReportSalesInfo;

	
}
