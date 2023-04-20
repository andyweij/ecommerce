package com.ecommerce.dao;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.ecommerce.entity.BeverageGoods;
import com.ecommerce.vo.GenericPageable;
import com.ecommerce.vo.GoodsDataCondition;
import com.ecommerce.vo.GoodsDataInfo;

@Repository
public class BackEndDao {
	@PersistenceContext(name = "oracleEntityManager")
	private EntityManager entityManager;
	
	public  GoodsDataInfo queryGoodsDatabyKey(GoodsDataCondition condition,GenericPageable genericPageable) {
		 	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<BeverageGoods> cq = cb.createQuery(BeverageGoods.class);
	        Root<BeverageGoods> beverageGoods = cq.from(BeverageGoods.class);
//	        SELECT * FROM ( SELECT ROWNUM ROW_NUM,BG.* FROM beverage_goods BG WHERE goods_id 
//	        like '%1%' and  lower(goods_name) like lower('%a%') and status = 1 and QUANTITY < 20 
//	        and price between 10 and 150 order by price desc ) 
//	        WHERE ROW_NUM >=0 AND ROW_NUM < 6;
	      
	        Predicate goodsId = cb.equal(beverageGoods.get("goodsId"), condition.getGoodsID());
	        
	     
	        Predicate goodsName = cb.like(beverageGoods.get("goodsName"), "%"+condition.getGoodsName()+"%");

//	        Predicate startPrice = cb.greaterThan(beverageGoods.get("goodsPrice"), condition.getStartPrice());
//	        Predicate endPrice = cb.greaterThan(beverageGoods.get("goodsPrice"), condition.getEndPrice());	        
//	        Predicate status = cb.greaterThan(beverageGoods.get("status"), condition.getStatus());
	        // 組合查尋條件
	        
	        
	        Predicate restriction = cb.and(goodsId, goodsName);
	        
	        // 排序  ORDER BY
//	        Order order = cb.desc(beverageGoods.get("goodsPrice"));        
	        // 放入全部查詢條件
	        // PS:select(storeInfo)可省略
	        cq.select(beverageGoods).where(restriction);        
	        
	        // 執行查詢
	        TypedQuery<BeverageGoods> query = entityManager.createQuery(cq);
	        GoodsDataInfo goodsDataInfo=GoodsDataInfo.builder().beverageGoods(query.getResultList()).build();
	        
	        return goodsDataInfo;

	}
}
