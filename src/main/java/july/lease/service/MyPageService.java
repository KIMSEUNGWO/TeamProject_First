package july.lease.service;

import java.util.List;

import july.lease.domain.Member;
import july.lease.domain.Product;
import july.lease.dto.MyPageMainInfoDto;
import july.lease.dto.MyPageOrderItemsDto;
import july.lease.dto.MyPageRentItemsDto;
import july.lease.dto.MyPageSellItemsDto;
import july.lease.dto.MyPageSellitemsModalDto;

public interface MyPageService {
	
	String findByName(Long memberId);
	
	List<Integer> compressInfo(Long memberId);
	
	List<MyPageMainInfoDto> confirmCheck(Long memberId);
	List<MyPageMainInfoDto> confirmCheckAJAX(Long memberId, Long startRow, Long endRow);
	
	List<Object> sellItemsInfo(Long memberId);
	
	List<MyPageSellItemsDto> sellItemsList(Long memberId);
	List<MyPageSellItemsDto> sellItemsListAJAX(Long memberId, Long startRow, Long endRow);

	List<Product> endItems(Long memberId);
	
	List<MyPageOrderItemsDto> orderItems(Long memberId);
	List<MyPageOrderItemsDto> orderItemsAJAX(Long memberId, Long startRow, Long endRow);
	
	
	Member findByPw(Long memberId, String password);
	
	// 확정, 취소 버튼 클릭시 유효성검사
	boolean validConfirm(Long memberId, Long productId, Long orderId);
	// 확정, 취소 버튼 클릭시 업데이트
	boolean confirmUpdate(Long orderId, Long productId, Long confirmId);
	
	MyPageSellitemsModalDto sellItemsModal(Long memberId, Long productId);
	
	boolean comparePw(Long memberId, String pw, String pwCheck);
	Member updatePw(Long memberId, String pw);
	
	boolean productRent(Long productId);
	boolean productReturn(Long productId);
	
	List<MyPageRentItemsDto> rentList(Long memberId);
	
	
	
}
