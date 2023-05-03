package com.ecommerce.controller;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.dao.BeverageMemberDao;
import com.ecommerce.entity.BeverageMember;
import com.ecommerce.vo.GoodsVo;
import com.ecommerce.vo.MemberInfo;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ecommerce/MemberController")
public class MemberController {
	
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Resource
	private MemberInfo sessionMemberInfo;
	
	@Resource(name = "sessionCartGoods")
	private List<GoodsVo> cartGoods;
	
	@Autowired
	private HttpSession httpSession; 
	
	@Autowired
	private BeverageMemberDao memberDao;

	@ApiOperation(value = "購物網-會員-檢查登入")
	@GetMapping(value = "/checkLogin")
	public ResponseEntity<MemberInfo> checkLogin() {
		
		logger.info("HttpSession checkLogin:" + httpSession.getId());
		logger.info("CheckLogin:" + sessionMemberInfo.toString());
		
		MemberInfo member = MemberInfo.builder().isLogin(sessionMemberInfo.getIsLogin()).customerName(sessionMemberInfo.getCustomerName()).build();
		if(!member.getIsLogin()) {
			member.setLoginMessage("尚未登入!!");
		}
		
		return ResponseEntity.ok(member);
	}
	
	@ApiOperation(value = "購物網-會員-登入")
	@PostMapping(value = "/login")
	public ResponseEntity<MemberInfo> login(@RequestBody MemberInfo member) {
		/*
			{
			  "identificationNo": "A124243295",
			  "cusPassword": "123"
			}
			{
			  "identificationNo": "G436565447",
			  "cusPassword": "123"
			}
		 */
		logger.info("HttpSession Login:" + httpSession.getId());
		logger.info("Before:" + sessionMemberInfo.toString());
		
		BeverageMember beverageMember= memberDao.findBymemberIdAndPwd(member.getIdentificationNo(),member.getCusPassword());
		if(null==beverageMember.getCustomerName()||null==beverageMember.getPwd()) {
			sessionMemberInfo=MemberInfo.builder().isLogin(false).identificationNo(member.getIdentificationNo()).loginMessage("輸入密碼錯誤").build();
		}else {
			sessionMemberInfo=MemberInfo.builder().isLogin(true).customerName(beverageMember.getCustomerName()).loginMessage("登入成功").build();
		}
		logger.info("After:" + sessionMemberInfo.toString());
		
		return ResponseEntity.ok(sessionMemberInfo);
	}
	
	@ApiOperation(value = "購物網-會員-登出")
	@GetMapping(value = "/logout")
	public ResponseEntity<MemberInfo> logout() {
		
		logger.info("HttpSession logout:" + httpSession.getId());
		
		sessionMemberInfo=MemberInfo.builder().isLogin(false).customerName(null).loginMessage("登出成功").build();
		
		return ResponseEntity.ok(sessionMemberInfo);
	}
	
	@ApiOperation(value = "商品加入購物車")
	@PostMapping(value = "/addCartGoods")
	public ResponseEntity<List<GoodsVo>> addCartGoods(@RequestBody GoodsVo goodsVo) {
		/*
			{
			  "goodsID": 28,
			  "goodsName": "Java Chip",
			  "description": "暢銷口味之一，以摩卡醬、乳品及可可碎片調製，加上細緻鮮奶油及摩卡醬，濃厚的巧克力風味。",
			  "goodsImageName": "20130813154445805.jpg",
			  "goodsPrice": 145,
			  "goodsQuantity": 17
			}

			{
			  "goodsID": 3,
			  "goodsName": "柳橙檸檬蜂蜜水",
			  "description": "廣受喜愛的蜂蜜水，搭配柳橙與檸檬汁，酸甜的好滋味，尾韻更帶有柑橘清香。",
			  "goodsImageName": "2021110210202761.jpg",
			  "goodsPrice": 20,
			  "goodsQuantity": 16
			}
		 */
		if(cartGoods.contains(goodsVo)) {
			cartGoods.get(cartGoods.indexOf(goodsVo)).setGoodsQuantity(cartGoods.get(cartGoods.indexOf(goodsVo)).getGoodsQuantity()+goodsVo.getGoodsQuantity());
		}else {
			cartGoods.add(goodsVo);
		}

		return ResponseEntity.ok(cartGoods);
	}
	
	@ApiOperation(value = "查尋購物車商品")
	@GetMapping(value = "/queryCartGoods")
	public ResponseEntity<List<GoodsVo>> queryCartGoods() {

		return ResponseEntity.ok(cartGoods);
	}
	
	@ApiOperation(value = "清空購物車商品")
	@DeleteMapping(value = "/clearCartGoods")
	public ResponseEntity<List<GoodsVo>> clearCartGoods() {
		cartGoods.clear();

		return ResponseEntity.ok(cartGoods);
	}
	
}
