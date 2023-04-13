package com.ecommerce.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.BackEndDao;
import com.ecommerce.vo.GenericPageable;
import com.ecommerce.vo.GoodsDataCondition;
import com.ecommerce.vo.GoodsDataInfo;


@Service
public class BackendService {

	private static Logger logger = LoggerFactory.getLogger(BackendService.class);
	@Autowired
	private BackEndDao backEndDao;
	
	public GoodsDataInfo queryGoodsData(GoodsDataCondition condition,GenericPageable genericPageable) {
		
		
		GoodsDataInfo goodsDataInfo=backEndDao.queryGoodsDatabyKey(condition, genericPageable);
		
		
		return goodsDataInfo;
	}
	
}
