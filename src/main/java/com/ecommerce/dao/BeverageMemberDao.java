package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.BeverageMember;
import com.ecommerce.vo.MemberInfo;

@Repository
public interface BeverageMemberDao extends JpaRepository<BeverageMember, Long>{
	public BeverageMember findBymemberIdAndPwd(String identificationNo, String pwd);
	
	
}
