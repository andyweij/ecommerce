package com.ecommerce.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
@SuperBuilder
@NoArgsConstructor
@Data
@Table(name = "BEVERAGE_MEMBER" ,schema="LOCAL")
@Entity
@ToString
public class BeverageMember {
	
	@Id
	@Column(name="IDENTIFICATION_NO")
	private String memberId;
	
	@Column(name="PASSWORD")
	private String pwd;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	@OneToMany(
			fetch = FetchType.LAZY,
			cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "beverageMember"
		)	
	private List<BeverageOrder> beverageMember;
}
