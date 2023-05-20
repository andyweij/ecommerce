package com.ecommerce.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.dao.BackEndDao;
import com.ecommerce.dao.BeverageGoodsJpaDao;
import com.ecommerce.dao.BeverageOrderJpaDao;
import com.ecommerce.entity.BeverageGoods;
import com.ecommerce.vo.GenericPageable;
import com.ecommerce.vo.GoodsDataCondition;
import com.ecommerce.vo.GoodsDataInfo;
import com.ecommerce.vo.GoodsOrderListVo;
import com.ecommerce.vo.GoodsOrderVo;
import com.ecommerce.vo.GoodsSalesReportCondition;
import com.ecommerce.vo.GoodsVo;

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
	
	
	public GoodsOrderListVo queryGoodsSales(GoodsSalesReportCondition condition, GenericPageable genericPageable) {
		
		String startDate= condition.getStartDate();
		String endDate= condition.getEndDate();		
		int totalgoods=beverageOrderJpaDao.queryGoodsSales(startDate,endDate).size();
		int totalPages=genericPageable.totalPages(genericPageable, totalgoods);
		List<Integer> rownumList=genericPageable.rownum(genericPageable);
		List<Integer> pagination=genericPageable.pagination(genericPageable,totalPages);
		genericPageable.setEndPage(totalPages);
		genericPageable.setPagination(pagination);
		List<GoodsOrderVo> queryGoodsSalesList=beverageOrderJpaDao.queryGoodsSalespages(startDate,endDate,rownumList.get(0),rownumList.get(1));
		GoodsOrderListVo queryGoodsSales=GoodsOrderListVo.builder().goodsReportSalesList(queryGoodsSalesList).genericPageable(genericPageable).build();
		
		
		return queryGoodsSales;
	}
	public BeverageGoods createGoods( GoodsVo goodsVo) throws IOException{
		BeverageGoods geverageGoods=BeverageGoods.builder().goodsName(goodsVo.getGoodsName()).
				goodsPrice(goodsVo.getGoodsPrice()).goodsQuantity(goodsVo.getGoodsQuantity()).
				DESCRIPTION(goodsVo.getDescription()).goodsImageName(goodsVo.getGoodsImageName()).status(goodsVo.getStatus()).build();
		backEndJpaDao.save(geverageGoods);
		if(null!=goodsVo.getFile()) {
		MultipartFile file = goodsVo.getFile();
		
		Files.copy(file.getInputStream(), Paths.get("/Users/andyweij/eclipse-workspace/Framework/ecommerce/src/main/webapp/DrinksImage").resolve(goodsVo.getGoodsImageName()));
		}
		return geverageGoods;
	}
	public BeverageGoods queryGoodsByID(Long goodsID) {
		
		BeverageGoods Goods=new BeverageGoods();
		Optional<BeverageGoods> beverageGoods=backEndJpaDao.findById(goodsID);
		if(beverageGoods.isPresent()) {
			Goods = beverageGoods.get();
		}
		return Goods;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public BeverageGoods updateGoods(GoodsVo goodsVo) throws IOException{
		Optional<BeverageGoods> goodsInfo = backEndJpaDao.findById(goodsVo.getGoodsID());	
		BeverageGoods beverageGoods=null;
		if(goodsInfo.isPresent()) {
			beverageGoods=goodsInfo.get();
			if(null!=goodsVo.getGoodsName()) {
			beverageGoods.setGoodsName(goodsVo.getGoodsName());
			}
			if(null!=(String.valueOf(goodsVo.getGoodsPrice()))) {
			beverageGoods.setGoodsPrice(goodsVo.getGoodsPrice());
			}
			if(goodsVo.getGoodsQuantity()>=0) {
			beverageGoods.setGoodsQuantity(beverageGoods.getGoodsQuantity()+goodsVo.getGoodsQuantity());
			}
			if(null!=goodsVo.getStatus()) {
			beverageGoods.setStatus(goodsVo.getStatus());
			}
			if(null!=goodsVo.getDescription()) {
			beverageGoods.setDESCRIPTION(goodsVo.getDescription());
			}
			if(null!=goodsVo.getFile()) {
				MultipartFile file = goodsVo.getFile();	
				Files.delete(Paths.get("/Users/andyweij/eclipse-workspace/Framework/ecommerce/src/main/webapp/DrinksImage").resolve(goodsInfo.get().getGoodsImageName()));
				if(null==goodsVo.getGoodsImageName()) {
				Files.copy(file.getInputStream(), Paths.get("/Users/andyweij/eclipse-workspace/Framework/ecommerce/src/main/webapp/DrinksImage").resolve(goodsVo.getFile().getOriginalFilename()));
				beverageGoods.setGoodsImageName(goodsVo.getFile().getOriginalFilename());
				}else{
				Files.copy(file.getInputStream(), Paths.get("/Users/andyweij/eclipse-workspace/Framework/ecommerce/src/main/webapp/DrinksImage").resolve(goodsVo.getGoodsImageName()));
				beverageGoods.setGoodsImageName(goodsVo.getGoodsImageName());
				}					
			}			
		}		
		return beverageGoods;
	}
}
