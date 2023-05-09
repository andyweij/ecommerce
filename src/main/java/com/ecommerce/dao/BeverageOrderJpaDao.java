package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.BeverageOrder;
import com.ecommerce.vo.GoodsOrderVo;

@Repository
public interface BeverageOrderJpaDao extends JpaRepository<BeverageOrder, Long>{

	@Query(value = 
			" Select BO.ORDER_ID AS orderId ," + 
			" BO.ORDER_DATE AS orderDate , " + 
			" BO.CUSTOMER_ID AS customerId, " + 
			" BO.GOODS_ID AS goodId , " + 
			" BO.GOODS_BUY_PRICE AS goodsBuyPrice , " + 
			" BO.BUY_QUANTITY AS buyQuantity , "+
			" bg.goods_name AS goodsName , "+
			" BM.CUSTOMER_NAME AS customerName "+
			" from BEVERAGE_ORDER BO INNER JOIN BEVERAGE_GOODS BG ON BO.GOODS_ID=BG.GOODS_ID "+
			" INNER JOIN BEVERAGE_MEMBER BM ON BO.CUSTOMER_ID = BM.IDENTIFICATION_NO "+
			" where bo.ORDER_DATE >= to_date( ?1 ,'YYYY-MM-DD HH24:MI:SS') "+
			" AND bo.ORDER_DATE <= to_date( ?2 , 'YYYY-MM-DD HH24:MI:SS') ",
			nativeQuery = true)
	List<GoodsOrderVo> queryGoodsSales(String startDate,String endDate);
	
	@Query(value =
			" SELECT BEA.* FROM "+
	        " (SELECT ROWNUM ROW_NUM , BE.* FROM "+
	        " (SELECT BO.*,BG.goods_name as goodsName ,BM.CUSTOMER_NAME as customerName"  +
	        " FROM (SELECT order_id AS orderId , order_date AS orderDate,customer_id as customerId , goods_id as goodId , goods_buy_price as goodsBuyPrice ,buy_quantity as buyQuantity "+ 
	        " FROM beverage_order )BO "+ 
	        " LEFT JOIN BEVERAGE_GOODS BG "+  
	        " ON BO.goodId=BG.GOODS_ID "+ 
	        " LEFT JOIN BEVERAGE_MEMBER BM "+ 
	        " ON BO.customerId = BM.IDENTIFICATION_NO "+ 
	        " where bo.orderDate >= to_date( ?1 ,'YYYY-MM-DD HH24:MI:SS') "+  
	        " AND bo.orderDate <= to_date( ?2 , 'YYYY-MM-DD HH24:MI:SS') "+  
	        " ORDER BY orderDate )BE )BEA "+ 
	        " WHERE ROW_NUM >= ?3 AND ROW_NUM <= ?4 ",
			
			nativeQuery = true)
	List<GoodsOrderVo> queryGoodsSalespages(String startDate,String endDate,int startNo,int EndNo);
}
