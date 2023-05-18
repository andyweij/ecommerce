package com.ecommerce.dao;

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
	        List<Predicate> condi=new ArrayList<>();
	        if(null!=condition.getGoodsID()) {
	        Predicate goodsId = cb.like(beverageGoods.get("goodsId").as(String.class),  "%"+condition.getGoodsID()+"%");
	        condi.add(goodsId);
	        }
	        if(null!=condition.getGoodsName()) {
	        Predicate goodsName = cb.like(cb.lower(beverageGoods.get("goodsName")), "%"+condition.getGoodsName().toLowerCase()+"%");
	        condi.add(goodsName);
	        
	        }
	        if(null!=condition.getStartPrice()) {
	        Predicate startPrice = cb.greaterThan(beverageGoods.get("goodsPrice"), condition.getStartPrice());
	        condi.add(startPrice);
	        }
	        if(null!=condition.getEndPrice()) {
	        Predicate endPrice = cb.lessThan(beverageGoods.get("goodsPrice"), condition.getEndPrice());
	        condi.add(endPrice);
	        }
	       
	        Predicate status = cb.equal(beverageGoods.get("status"), condition.getStatus());
	        condi.add(status);
	        
	        if(null!=condition.getQuantity()) {
	        Predicate quantity = cb.lessThan(beverageGoods.get("goodsQuantity"), condition.getQuantity());
	        condi.add(quantity);
	        }
	        
	        //判斷條件數量
	        Predicate restriction=null;
	        switch(condi.size()) {
	        case 1:{
	        	restriction=condi.get(0);
	        	break;
	        }
	        case 2:{
	        	restriction=cb.and(condi.get(0),condi.get(1));
	        	break;
	        }
	        case 3:{
	        	restriction=cb.and(condi.get(0),condi.get(1),condi.get(2));
	        	break;
	        }
	        case 4:{
	        	restriction=cb.and(condi.get(0),condi.get(1),condi.get(2),condi.get(3));
	        	break;
	        }
	        case 5:{
	        	restriction=cb.and(condi.get(0),condi.get(1),condi.get(2),condi.get(3),condi.get(4));
	        	break;
	        }
	        }
	        // 組合查尋條件
	        Order order=null;
	      	// 排序  ORDER BY
	        if(condition.getStatus()=="1") {
	        	order = cb.desc(beverageGoods.get("goodsPrice")); 
	        }else if(condition.getStatus()=="0"){
	        	order = cb.asc(beverageGoods.get("goodsPrice"));
	        }else {
	        	order =cb.asc(beverageGoods.get("goodsId"));
	        }	        
	        // 放入全部查詢條件
	        // PS:select(storeInfo)可省略
	        cq.select(beverageGoods).where(restriction).orderBy(order);
	        	        
	        // 執行查詢
	        TypedQuery<BeverageGoods> queryTotalgoodscount =entityManager.createQuery(cq);//查詢總筆數
	        int goodsamount=GoodsDataInfo.builder().beverageGoods(queryTotalgoodscount.getResultList()).build().getBeverageGoods().size();
	        int totalPages=genericPageable.totalPages(genericPageable, goodsamount);
	        genericPageable.setPagination(genericPageable.pagination(genericPageable, totalPages));
	        genericPageable.setEndPage(totalPages);
	        List<Integer> rownum=genericPageable.rownum(genericPageable);
	        TypedQuery<BeverageGoods> query = entityManager.createQuery(cq).setFirstResult(rownum.get(0)).setMaxResults(genericPageable.getPageDataSize());//查詢分頁比數
	        GoodsDataInfo goodsDataInfo=GoodsDataInfo.builder().beverageGoods(query.getResultList()).genericPageable(genericPageable).build();
	        
	        return goodsDataInfo;
	}

	
}
