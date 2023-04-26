package com.ecommerce.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.BeverageOrder;
import com.ecommerce.vo.GoodsReportSales;

@Repository
public interface BeverageOrderJpaDao extends JpaRepository<BeverageOrder, Long>{

	@Query(value = 
			" Select BO.ORDER_ID AS orderId ," + 
			" BO.ORDER_DATE AS orderDate , " + 
			" BO.CUSTOMER_ID AS customerId, " + 
			" BO.GOODS_ID AS goodId , " + 
			" BO.GOODS_BUY_PRICE AS goodsBuyPrice , " + 
			" BO.BUY_QUANTITY AS buyQuantity , "+
			" bg.goods_name AS goodsName "+
			" from BEVERAGE_ORDER BO INNER JOIN BEVERAGE_GOODS BG ON BO.GOODS_ID=BG.GOODS_ID "+
			" where bo.ORDER_DATE >= to_date( ?1 ,'YYYY-MM-DD HH24:MI:SS') "+
			" AND bo.ORDER_DATE <= to_date( ?2 , 'YYYY-MM-DD HH24:MI:SS') ",
			nativeQuery = true)
	List<GoodsReportSales> queryGoodsSales(String startDate,String endDate);
}
