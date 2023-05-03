package com.ecommerce.vo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class MemberInfo {
	
	private Boolean isLogin;
	
	private String loginMessage;
	
	private String identificationNo;
		
	private String cusPassword;
		
	private String customerName;
	
	
}
