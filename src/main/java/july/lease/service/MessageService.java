package july.lease.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import july.lease.domain.Message;
import july.lease.dto.MyAllMessageListDto;
import july.lease.dto.OrderRequestDto;
import july.lease.dto.ProductMessageInfoDto;


@Service
public interface MessageService {
	
	// 상품별 메세지 리스트
	public List<Message> getMessage(Long myId, Long roomNo);
	
	// 주문안했을경우에 대한 제품 정보
	public ProductMessageInfoDto getOneProductInfo(Long productId);
	
	// 전체 메세지 리스트
	public List<MyAllMessageListDto> getMyAllMessageList(Long memberId);
	
	// 메세지 저장
	public int insertMessage(Message msgVo);
	
	// roomNo 조회
	public Long findRoomNo(Long myId, Long productId);
	
	// 안읽은 메세지 
	public int countUnreadMessage(Long yourId, Long roomNo);
	
	// 메세지 삭제
	public int deleteMessage(Long roomNo);
	
	// 대여신청 확인 메세지 보내기
	public int orderInsertMessage(Long memberId, Long productId, OrderRequestDto orderRequestDto);
}
