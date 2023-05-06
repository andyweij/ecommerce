package com.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.BeverageGoods;

@Repository
public interface FrontEndDao extends JpaRepository<BeverageGoods, Long>{
//	SELECT * FROM(Select bs.*,ROWNUM ROW_NUM FROM BEVERAGE_GOODS BS WHERE bs.goods_name like  '%a%' ) WHERE ROW_NUM > 1 AND ROW_NUM <= 3;
	@Query(value = "SELECT * FROM ( SELECT bs.* ,ROWNUM ROW_NUM FROM BEVERAGE_GOODS bs WHERE bs.goods_name like %?1% ) WHERE ROW_NUM > ?2 AND ROW_NUM <= ?3" ,nativeQuery = true)
	List<BeverageGoods> queryGoodsDataByKeyAndrowNum(String searchKeyword,int startNo,int endNo);
	
	@Query(value = "SELECT bs FROM BeverageGoods bs WHERE bs.goodsName like %?1%" )
	List<BeverageGoods> queryGoodsData(String searchKeyword);
	
	
}