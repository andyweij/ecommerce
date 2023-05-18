package com.ecommerce.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BEVERAGE_ORDER_SEQ")
    @SequenceGenerator(name = "BEVERAGE_ORDER_SEQ", sequenceName = "BEVERAGE_ORDER_SEQ", allocationSize = 1)
	private int orderId;
	
	@Column(name="ORDER_DATE")
	private Date orderDate;
	
	@Column(name="CUSTOMER_ID")
	private String customerId;

	@Column(name="GOODS_BUY_PRICE")
	private int goodsBuyPrice;
	@Column(name="BUY_QUANTITY")
	private int buyQuantity;
	
	@Column(name="GOODS_ID")
	private Long goodId;
	
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "GOODS_ID", insertable = false, updatable = false)
	private BeverageGoods beverageGoods;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "CUSTOMER_ID", insertable = false, updatable = false)
	private BeverageMember beverageMember;
}
