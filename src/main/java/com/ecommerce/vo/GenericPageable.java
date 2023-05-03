package com.ecommerce.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenericPageable {

	private int currentPageNo;// 當前頁面
	private int pageDataSize;// 每頁最大筆數
	private int pagesIconSize;// 頁次數量
	private List<Integer> pagination;

	public List<Integer> pagination(GenericPageable genericPageable, int goodscounts) {
		List<Integer> pagination = new ArrayList<>();
		int totalPages = (int) Math.ceil((double) goodscounts / genericPageable.getPageDataSize());
		int frontpages=genericPageable.getPagesIconSize()/2;
		int behindPages=genericPageable.getPagesIconSize()-frontpages;
		
			if(genericPageable.getCurrentPageNo()-frontpages>0) {
				for(int i=genericPageable.getCurrentPageNo()-frontpages;i<genericPageable.getCurrentPageNo();i++) {
					pagination.add(i);
				}
			}else {
				for(int i=1;i<genericPageable.getCurrentPageNo();i++) {
					pagination.add(i);
				}
			}
			if(genericPageable.getCurrentPageNo()+behindPages<totalPages) {
			for(int i=genericPageable.getCurrentPageNo();i<=genericPageable.getCurrentPageNo()+behindPages;i++) {
				pagination.add(i);
			}
				
			}else {
				for(int i=genericPageable.getCurrentPageNo();i<=totalPages;i++) {
					pagination.add(i);
				}
			}		
		return pagination;
	}
	public List<Integer> rownum(GenericPageable genericPageable){
		List<Integer> rownum=new ArrayList<>();
		int EndNo=genericPageable.getCurrentPageNo()*genericPageable.getPageDataSize();
		int StartNo=EndNo-(genericPageable.getPageDataSize());
		rownum.add(StartNo);
		rownum.add(EndNo);		
		return rownum;
	}
}
