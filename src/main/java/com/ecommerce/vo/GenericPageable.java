package com.ecommerce.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GenericPageable {
	private int currentPageNo;//當前頁面
	private int pageDataSize;//每頁最大筆數
	private int pagesIconSize;//頁次數量
	private List<Integer> pagination;
	

	public List<Integer> pagination(GenericPageable genericPageable,int goodscounts) {
			List<Integer> pagination=new ArrayList<>();
			int totalPages=(int)Math.ceil((double)goodscounts/genericPageable.getPageDataSize());
			int difnum=genericPageable.getCurrentPageNo()-genericPageable.getPagesIconSize();
		if(difnum<=1 && genericPageable.getPagesIconSize()<totalPages) {
			for(int i=1;i<=genericPageable.getPagesIconSize();i++) {
				pagination.add(i);
			}
		}else if(difnum>0&&genericPageable.getCurrentPageNo()==totalPages) {
			for(int i=totalPages-genericPageable.getPagesIconSize();i<=totalPages;i++) {
				pagination.add(i);
			}
		}else if(genericPageable.getCurrentPageNo()==1) {
			pagination.add(genericPageable.getCurrentPageNo());
			pagination.add(genericPageable.getCurrentPageNo()+1);
		}
////		pagination.setPageSize(6);//每頁顯示筆數
////		pagination.setTotalPages((int)Math.ceil((double)backenddao.queryGoodsBykey(page).size()/pagination.getPageSize()));//總頁數
////		if(null==page.getPageNo()||page.getPageNo()==""){
////			pagination.setCurPage(1);
////		}else{
////			pagination.setCurPage(Integer.parseInt(page.getPageNo()));
////		}
////		pagination.setGoodsList(backenddao.paginationBykey(page));
////		List<Integer> pageNoList=new ArrayList<>();
////		if(pagination.getTotalPages()<3){
////			for(int i=1;i<=pagination.getTotalPages();i++){
////				pageNoList.add(i);
////			}
////		}else{
////			if(pagination.getCurPage()==1){
////				for(int i=1;i<=3;i++){
////					pageNoList.add(i);	
////				}
////			}else if(pagination.getCurPage()==pagination.getTotalPages()){
////				for(int i=pagination.getTotalPages()-2;i<=pagination.getTotalPages();i++){
////					pageNoList.add(i);	
////				}
////			}else{
////				for(int i=pagination.getCurPage()-1;i<=pagination.getCurPage()+1;i++){
////					pageNoList.add(i);	
////				}
////			}
////		}
////		pagination.setPageNo(pageNoList);
////		return pagination;
		return pagination;	
	}
	
}
