package common;

import board.model.vo.PageInfo;

public class Pagination {
	
	public static PageInfo getPageInfo(int currentPage, int listCount){
		int pageLimit = 10;  	// 한 페이지에서 보여질 페이징 수 작성
		int maxPage;    		// 전체 페이징 수 중 가장 마지막 페이지
		int startPage;  		// 현재 페이징을 구성하는 수 중 가장 처음에 있는 수, pageLimit에 따라 달라짐
		int endPage;    		// 현재 페이징을 구성하는 수 중 가장 마지막에 있는 수 
		int boardLimit = 5; 	// 한 페이지에 보여질 게시글 수
		
		//pageLimit, boardLimit는 직접 넣어줘야됨 즉 고정해서 변수를 넣어줘도 됨
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		/*
		   if( listCount % boardLimit != 0 ) { maxPage += 1; } 
		   int와 int계산이기 떄문에 if가 가능하다.
		   
		 */
		
		startPage = (currentPage - 1)/pageLimit*pageLimit + 1; //컴퓨터상에서 상쇄 개념이 없음
		// 1, 11, 21, 31, ..... => 10n + 1 (n >= 0)
		// currentPage : 1 ~ 10 => startPage 1 => n=0으로 고정
		// currentPage : 11 ~ 20 => startPage 11 => n=1으로 고정
		// currentPage : 21 ~ 30 => startPage 21 => n=2으로 고정
		// n을 계산하기위해 currentPage가 필요함.
		// n = (currentPage - 1)/pageLimit
		
		endPage = (startPage+pageLimit)-1;
		if(maxPage < endPage) {
			endPage =maxPage;
		}
		 
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, maxPage, startPage, endPage, boardLimit);
		return pi;
	}
}
