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
	private int endPage;

	public List<Integer> pagination(GenericPageable genericPageable, int totalPages) {
		List<Integer> pagination = new ArrayList<>();
		
		if(genericPageable.getPagesIconSize()>=totalPages) {
			for(int i=1;i<=totalPages;i++) {
				pagination.add(i);
			}
		}else {
			int frontPages;
			int behindPages;
			if(genericPageable.getPagesIconSize()%2==0) {
				frontPages=genericPageable.getPagesIconSize()/2-1;
			behindPages=genericPageable.getPagesIconSize()/2;
			}else {
				frontPages=genericPageable.getPagesIconSize()/2;
				behindPages=frontPages;
			}
			if(genericPageable.getCurrentPageNo()-frontPages>0&&genericPageable.getCurrentPageNo()+behindPages<=totalPages) {
				for(int i=genericPageable.getCurrentPageNo()-frontPages;i<=genericPageable.getCurrentPageNo()+behindPages;i++) {
					pagination.add(i);
				}
			}else if(genericPageable.getCurrentPageNo()+frontPages>=totalPages&&genericPageable.getCurrentPageNo()!=totalPages) {
				for(int i=genericPageable.getCurrentPageNo()-behindPages-(genericPageable.getCurrentPageNo()+frontPages-totalPages);i<=totalPages;i++) {
					pagination.add(i);
				}
			}else if(genericPageable.getCurrentPageNo()==1||genericPageable.getCurrentPageNo()-frontPages==0) {
				for(int i=1;i<=genericPageable.getPagesIconSize();i++) {
					pagination.add(i);
				}	
			}else {
				for(int i=totalPages-genericPageable.getPagesIconSize()+1;i<=totalPages;i++) {
					pagination.add(i);
				}
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
	public int totalPages(GenericPageable genericPageable,int goodscounts) {
		int totalPages=(int) Math.ceil((double) goodscounts / genericPageable.getPageDataSize());
		return totalPages;
	}
}
