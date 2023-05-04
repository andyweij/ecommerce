package com.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.FrontEndDao;
import com.ecommerce.entity.BeverageGoods;
import com.ecommerce.vo.CheckoutCompleteInfo;
import com.ecommerce.vo.GenericPageable;
import com.ecommerce.vo.GoodsVo;
import com.ecommerce.vo.MemberInfo;
import com.ecommerce.vo.OrderCustomer;
import com.ecommerce.vo.ProductGoodsInfo;



@Service
public class FrontendService {
	
@Autowired
private FrontEndDao fronEndDao;
	

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
		genericPageable.setPagination(genericPageable.pagination(genericPageable,fronEndDao.queryGoodsData(searchKeyword).size() ));
		productGoodsInfo.setGenericPageable(genericPageable);
		
		return productGoodsInfo;
	}
	@Transactional
	public CheckoutCompleteInfo checkoutGoods(MemberInfo sessionMemberInfo,OrderCustomer customer,List<GoodsVo> cartGoods) {
		
		Map<Long,Integer> goodIds=new HashMap<>();
		for(GoodsVo cartGood:cartGoods) {
		goodIds.put(cartGood.getGoodsID(),cartGood.getGoodsQuantity());		
		}
		List<BeverageGoods> dbGoodsList=fronEndDao.findAllById(goodIds.keySet());
		for(BeverageGoods buyGoods:dbGoodsList) {
			
			if(buyGoods.getGoodsQuantity()>=goodIds.get(buyGoods.getGoodsId())) {
				
				buyGoods.setGoodsQuantity(buyGoods.getGoodsQuantity()-goodIds.get(buyGoods.getGoodsId()));
			}else if(buyGoods.getGoodsQuantity()<goodIds.get(buyGoods.getGoodsId())&&buyGoods.getGoodsQuantity()!=0) {
				buyGoods.setGoodsQuantity(0);
			}			
		}
		
		
		return null;
	}
}
