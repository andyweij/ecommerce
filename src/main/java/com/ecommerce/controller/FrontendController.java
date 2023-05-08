package com.ecommerce.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dao.BeverageOrderDao;
import com.ecommerce.entity.BeverageOrder;
import com.ecommerce.service.FrontendService;
import com.ecommerce.vo.CheckoutCompleteInfo;
import com.ecommerce.vo.GenericPageable;
import com.ecommerce.vo.GoodsVo;
import com.ecommerce.vo.MemberInfo;
import com.ecommerce.vo.OrderCustomer;
import com.ecommerce.vo.ProductGoodsInfo;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ecommerce/FrontendController")
public class FrontendController {
	
	private static Logger logger = LoggerFactory.getLogger(FrontendController.class);
	
	@Autowired
	private HttpSession httpSession;
	
	@Resource
	private MemberInfo sessionMemberInfo;
	
	@Resource(name = "sessionCartGoods")
	private List<GoodsVo> cartGoods;
	
	@Autowired
	private FrontendService frontendService;
	
	@Autowired	
	private BeverageOrderDao beverageOrderDao;
	
	@ApiOperation(value = "購物網-前臺-查詢商品列表")
	@GetMapping(value = "/queryGoodsData")
	public ResponseEntity<ProductGoodsInfo> queryGoodsData(@RequestParam(required = false) String searchKeyword,
			 @RequestParam int currentPageNo, @RequestParam int pageDataSize, @RequestParam int pagesIconSize) {
	
		GenericPageable genericPageable = GenericPageable.builder().currentPageNo(currentPageNo)
				.pageDataSize(pageDataSize).pagesIconSize(pagesIconSize).build();
		
		ProductGoodsInfo goodsDataInfo = frontendService.queryGoodsData(searchKeyword, genericPageable);		
		
		return ResponseEntity.ok(goodsDataInfo);
	}
	
	@ApiOperation(value = "購物網-前臺-結帳購物車商品")
	@PostMapping(value = "/checkoutGoods")
	public ResponseEntity<CheckoutCompleteInfo> checkoutGoods(@RequestBody OrderCustomer customer) {
		/*
		 	{
			  "customer": {
			    "cusName": "Andy",
			    "homeNumber": "0123456789",
			    "mobileNumber": "0234567890",
			    "orderAddr": "Taipei,Taiwan"
			  }
			  */
		logger.info("HttpSession checkoutGoods:" + httpSession.getId());
		logger.info("CheckoutGoods:" + sessionMemberInfo.toString());
		
		CheckoutCompleteInfo checkoutCompleteInfo = frontendService.checkoutGoods(sessionMemberInfo, customer, cartGoods);
		
		return ResponseEntity.ok(checkoutCompleteInfo);
	}
	@ApiOperation(value = "購物網-前臺-訂單新增")
	@PostMapping(value = "/insertSalesReports")
	public ResponseEntity<CheckoutCompleteInfo> insertSalesReport() {
		
		logger.info("HttpSession checkoutGoods:" + httpSession.getId());
		logger.info("CheckoutGoods:" + sessionMemberInfo.toString());
		LocalDateTime dateValue = LocalDateTime.now();// your LocalDateTime
		java.util.Date utilDate=null;
		String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern(dateFormat);
		SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat);
		try {
			utilDate = sdf1.parse(dateValue.format(dtf1));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());	
	
		BeverageOrder beverageOrder=BeverageOrder.builder()
				.goodId(5l)
				.goodsBuyPrice(20)
				.buyQuantity(1)
				.orderDate(sqlDate).customerId("A124243295").build();
		
		beverageOrderDao.save(beverageOrder);
		
		return ResponseEntity.ok(null);
	}

}
