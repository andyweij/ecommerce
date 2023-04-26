package com.ecommerce.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.BackEndDao;
import com.ecommerce.dao.BeverageGoodsJpaDao;
import com.ecommerce.dao.BeverageOrderJpaDao;
import com.ecommerce.entity.BeverageGoods;
import com.ecommerce.vo.GenericPageable;
import com.ecommerce.vo.GoodsDataCondition;
import com.ecommerce.vo.GoodsDataInfo;
import com.ecommerce.vo.GoodsReportSales;
import com.ecommerce.vo.GoodsReportSalesInfo;
import com.ecommerce.vo.GoodsSalesReportCondition;

@Service
public class BackendService {

	private static Logger logger = LoggerFactory.getLogger(BackendService.class);

	@Autowired
	private BackEndDao backEndDao;
	@Autowired
	private BeverageOrderJpaDao beverageOrderJpaDao;
	@Autowired
	private BeverageGoodsJpaDao backEndJpaDao;

	public GoodsDataInfo queryGoodsData(GoodsDataCondition condition, GenericPageable genericPageable) {

		GoodsDataInfo goodsDataInfo = backEndDao.queryGoodsDatabyKey(condition, genericPageable);

		return goodsDataInfo;
	}

	public List<BeverageGoods> queryAllGoods() {

		List<BeverageGoods> beverageGoods = backEndJpaDao.findAll();

		return beverageGoods;
	}
	
	
	public GoodsReportSalesInfo queryGoodsSales(GoodsSalesReportCondition condition, GenericPageable genericPageable) {
		
		String startDate= condition.getStartDate();
		String endDate= condition.getEndDate();
		List<GoodsReportSales> queryGoodsSalesList=beverageOrderJpaDao.queryGoodsSales(startDate,endDate);
		GoodsReportSalesInfo queryGoodsSales=GoodsReportSalesInfo.builder().goodsReportSalesList(queryGoodsSalesList).build();
		
		return queryGoodsSales;
	}
	
}
