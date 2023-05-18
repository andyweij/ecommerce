package com.ecommerce.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.BeverageOrderDao;
import com.ecommerce.dao.FrontEndDao;
import com.ecommerce.entity.BeverageGoods;
import com.ecommerce.entity.BeverageMember;
import com.ecommerce.entity.BeverageOrder;
import com.ecommerce.vo.CheckoutCompleteInfo;
import com.ecommerce.vo.GenericPageable;
import com.ecommerce.vo.GoodsVo;
import com.ecommerce.vo.MemberInfo;
import com.ecommerce.vo.OrderCustomer;
import com.ecommerce.vo.ProductGoodsInfo;
import com.sun.el.parser.ParseException;



@Service
public class FrontendService {

@Autowired
private FrontEndDao fronEndDao;
@Autowired	
private BeverageOrderDao beverageOrderDao;
	public ProductGoodsInfo queryGoodsData(String searchKeyword , GenericPageable genericPageable) {
		List<BeverageGoods> beverageGoods=new ArrayList<>();
		List<Integer> rownum = genericPageable.rownum(genericPageable);
		
		if(null==searchKeyword) {
			searchKeyword="";
			beverageGoods=fronEndDao.queryGoodsDataByKeyAndrowNum(searchKeyword,rownum.get(0),rownum.get(1));
		}else {
		beverageGoods=fronEndDao.queryGoodsDataByKeyAndrowNum(searchKeyword,rownum.get(0),rownum.get(1));
		}		
		ProductGoodsInfo productGoodsInfo=ProductGoodsInfo.builder().orderGoodList(beverageGoods).build();
		int totalPages=genericPageable.totalPages(genericPageable, fronEndDao.queryGoodsData(searchKeyword).size());
		genericPageable.setPagination(genericPageable.pagination(genericPageable,totalPages ));
		genericPageable.setEndPage(totalPages);
		productGoodsInfo.setGenericPageable(genericPageable);
		
		return productGoodsInfo;
	}
	
	public CheckoutCompleteInfo checkoutGoods(MemberInfo sessionMemberInfo,OrderCustomer customer,List<GoodsVo> cartGoods) {
		
		Map<Long,Integer> goodIds=new HashMap<>();
		for(GoodsVo cartGood:cartGoods) {
		goodIds.put(cartGood.getGoodsID(),cartGood.getGoodsQuantity());		
		}
		LocalDateTime dateValue = LocalDateTime.now();// your LocalDateTime
		java.util.Date utilDate = null;
		String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern(dateFormat);
		SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat);
		try {
			utilDate = sdf1.parse(dateValue.format(dtf1));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		List<BeverageGoods> dbGoodsList=fronEndDao.findAllById(goodIds.keySet());
		List<BeverageGoods> buyGoodsList=new ArrayList<>();
		List<BeverageOrder> beverageOrderList=new ArrayList<>();
		for(BeverageGoods dbGoods:dbGoodsList) {		
			if(dbGoods.getGoodsQuantity()>=goodIds.get(dbGoods.getGoodsId())) {				
				dbGoods.setGoodsQuantity(dbGoods.getGoodsQuantity()-goodIds.get(dbGoods.getGoodsId()));
						
					BeverageOrder beverageOrder = BeverageOrder.builder().goodId(dbGoods.getGoodsId())
							.goodsBuyPrice(dbGoods.getGoodsPrice()).buyQuantity(goodIds.get(dbGoods.getGoodsId())).orderDate(sqlDate)
							.customerId("A124243295").build();
					beverageOrderList.add(beverageOrder);
					buyGoodsList.add(dbGoods);
			}else if(dbGoods.getGoodsQuantity()<goodIds.get(dbGoods.getGoodsId())&&dbGoods.getGoodsQuantity()!=0) {
				dbGoods.setGoodsQuantity(0);
					BeverageOrder beverageOrder = BeverageOrder.builder().goodId(dbGoods.getGoodsId())
							.goodsBuyPrice(dbGoods.getGoodsPrice()).buyQuantity(dbGoods.getGoodsQuantity()).orderDate(sqlDate)
							.customerId("A124243295").build();
					beverageOrderList.add(beverageOrder);
					buyGoodsList.add(dbGoods);
			}			
		}
		fronEndDao.saveAll(buyGoodsList);
		beverageOrderDao.saveAll(beverageOrderList);
		customer.setCusName(sessionMemberInfo.getCustomerName());
		CheckoutCompleteInfo checkoutCompleteInfo=CheckoutCompleteInfo.builder()
				.customer(customer)
				.orderGoodList(buyGoodsList).build();
		
		return checkoutCompleteInfo;
	}
	
}
