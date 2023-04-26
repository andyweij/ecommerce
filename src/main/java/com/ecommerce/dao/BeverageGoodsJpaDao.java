package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.BeverageGoods;

@Repository
public interface BeverageGoodsJpaDao extends JpaRepository<BeverageGoods, Long>{

	
	
}
