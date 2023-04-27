package com.ecommerce.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ecommerce.vo.GoodsOrderListVo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = {"goodsId"})
@Table(name = "BEVERAGE_GOODS" ,schema="LOCAL")
@Entity
public class BeverageGoods {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BEVERAGE_GOODS_SEQ")
    @SequenceGenerator(name = "BEVERAGE_GOODS_SEQ", sequenceName = "BEVERAGE_GOODS_SEQ", allocationSize = 1)
	@Column(name = "GOODS_ID")
	private Long goodsId;
	
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
			cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "beverageGoods"
		)	
	private List<BeverageOrder> beverageOrders;

	
}
