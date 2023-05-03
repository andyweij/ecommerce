package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

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
//		List<Integer> rownum = genericPageable.rownum(genericPageable);
		
		if(null==searchKeyword) {
			searchKeyword="";
			beverageGoods=fronEndDao.queryGoodsData(searchKeyword);
		}else {
		beverageGoods=fronEndDao.queryGoodsData(searchKeyword);
		}		
		ProductGoodsInfo productGoodsInfo=ProductGoodsInfo.builder().orderGoodList(beverageGoods).build();
//		genericPageable.setPagination(genericPageable.pagination(genericPageable, productGoodsInfo.getOrderGoodList().size()));
//		productGoodsInfo.setGenericPageable(genericPageable);
		
		return productGoodsInfo;
	}

	public CheckoutCompleteInfo checkoutGoods(MemberInfo sessionMemberInfo,OrderCustomer customer,List<GoodsVo> cartGoods) {
		
		return null;
	}
}
