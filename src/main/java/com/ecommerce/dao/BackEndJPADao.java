package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.BeverageGoods;

@Repository
public interface BackEndJPADao extends JpaRepository<BeverageGoods, Long>{

	
	
}
