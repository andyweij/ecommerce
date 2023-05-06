package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.BeverageOrder;
@Repository
public interface BeverageOrderDao extends JpaRepository<BeverageOrder, Long>{
	@Modifying
	@Query(value = "INSERT INTO BEVERAGE_ORDER VALUES (BEVERAGE_ORDER_SEQ.nextval , to_date(?1,'YYYY-MM-DD'), ?2, ?3, ?4,?5 )" ,nativeQuery = true)
	int insertBeverageOrder(String orderDate,String customerId,Long goodsId,Integer goodsBuyPrice,Integer buyQuantity);
}
