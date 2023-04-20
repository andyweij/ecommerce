package com.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@Entity
@Table(name = "BEVERAGE_ORDER", schema="LOCAL")
public class BeverageOrder {
	@Id
	@Column(name="ORDER_ID")
	private int orderId;
	@Column(name="ORDER_DATE")
	private String orderDate;
	@Column(name="CUSTOMER_ID")
	private String customerId;

	@Column(name="GOODS_BUY_PRICE")
	private int goodsBuyPrice;
	@Column(name="BUY_QUANTITY")
	private int buyQuantity;
	
	@Column(name="GOODS_ID")
	private int goodId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "GOODS_ID", insertable = false, updatable = false)
	private BeverageGoods beverageGoods;
}
