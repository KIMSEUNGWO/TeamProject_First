package july.lease.dao.mypage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import july.lease.domain.Product;
import july.lease.dto.MyPageMainInfoDto;
import july.lease.dto.MyPageOrderItemsDto;
import july.lease.dto.MyPageRentItemsDto;
import july.lease.dto.MyPageSellItemsDto;
import july.lease.dto.MyPageSellitemsModalDto;

@Mapper
public interface MyPageMapper {
	
	//	마이페이지 메뉴의 이름가져오기
	String findByName(Long memberId);
	
	//	마이페이지 메인화면에서 요약된 박스 4개의 정보가져오기
	Integer findByOrderCount(Long memberId);
	
	Integer findBySellCount(Long memberId);
	
	Integer findByMyitemsCount(Long memberId);
	
	Integer findByWaitItems(Long memberId);
	////////////////////////////////////////////
	

	// 마이페이지 메인화면에서 현재 대여요청 리스트 가져오기
	List<MyPageMainInfoDto> confirmCheck(Long memberId);
	List<MyPageMainInfoDto> confirmCheckAJAX(@Param("memberId") Long memberId, @Param("startRow") Long startRow, @Param("endRow") Long endRow);
	
	// 마이페이지 판매내역 요약된 박스 3개 정보 가져오기
	Integer findBySellingItem(Long memberId);
	
	Integer todayReturnItem(Long memberId);
	
	String totalBenefit(Long memberId);
	
	// 마이페이지 판매내역 리스트
	List<MyPageSellItemsDto> itemSellList(Long memberId);
	List<MyPageSellItemsDto> itemSellListAJAX(@Param("memberId") Long memberId, @Param("startRow") Long startRow, @Param("endRow") Long endRow);
	
	List<Product> endItems(Long memberId);

	// 구매내역 리스트
	List<MyPageOrderItemsDto> orderItems(Long memberId);
	List<MyPageOrderItemsDto> orderItemsAJAX(@Param("memberId") Long memberId, @Param("startRow") Long startRow, @Param("endRow") Long endRow);
	
	// 확정 취소버튼 클릭시 유효성 검사
	Long validConfirm1(@Param("memberId") Long memberId, @Param("productId") Long productId); // 해당 물건이 내가 올린물건이 맞는지 확인
	Long validConfirm2(@Param("orderId") Long orderId, @Param("productId") Long productId); // 해당 주문번호가 해당 물품을 가르키고있는지 확인
	
	Integer confirmUpdate(@Param("orderId") Long orderId, @Param("productId") Long productId, @Param("confirmId") Long confirmId);
	
	MyPageSellitemsModalDto sellItemsModal(@Param("memberId") Long memberId, @Param("productId") Long productId);
	
	int updatePw(@Param("memberId") Long memberId, @Param("pw") String pw);
	int productRent(Long productId);
	int productReturn(Long productId);
	
	List<MyPageRentItemsDto> rentList(Long memberId);
}
