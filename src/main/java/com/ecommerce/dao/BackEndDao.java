package com.ecommerce.dao;

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
	
	public GoodsDataInfo queryGoodsDatabyKey(GoodsDataCondition condition,GenericPageable genericPageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BeverageGoods> cq = cb.createQuery(BeverageGoods.class);
        Root<BeverageGoods> beverageGoods = cq.from(BeverageGoods.class);

        // 商店地區編號 AND
        Predicate goodsID = cb.equal(beverageGoods.get("goodsID"), condition.getGoodsID());
        // 商店營業額 AND
        Predicate goodsName = cb.greaterThanOrEqualTo(beverageGoods.get("goodsName"), condition.getGoodsName());
        // 商店營業日期  OR   
        Predicate startPrice = cb.greaterThan(beverageGoods.get("goodsPrice"), condition.getStartPrice());
        Predicate endPrice = cb.greaterThan(beverageGoods.get("goodsPrice"), condition.getEndPrice());
        
        Predicate status = cb.greaterThan(beverageGoods.get("status"), condition.getStatus());
        // 組合查尋條件
        Predicate restriction = cb.or(cb.and(goodsID, goodsName), startPrice);
        
        // 排序  ORDER BY
        Order order = cb.desc(beverageGoods.get("goodsPrice"));        
        // 放入全部查詢條件
        // PS:select(storeInfo)可省略
        cq.select(beverageGoods).where(restriction).orderBy(order);        
        
        // 執行查詢
        TypedQuery<BeverageGoods> query = entityManager.createQuery(cq);
        GoodsDataInfo goodsDataInfo=GoodsDataInfo.builder().beverageGoods(query.getResultList()).build();
        
        /*
			SELECT * FROM STORE_INFORMATION
			WHERE GEOGRAPHY_ID = 2
			AND SALES >= 1500
			OR STORE_DATE > '2018-04-01 00:00:00'
			ORDER BY SALES DESC;
        */
        return goodsDataInfo;
	}
}
