package july.lease.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import july.lease.dao.message.MessageDao;
import july.lease.domain.Message;
import july.lease.dto.MyAllMessageListDto;
import july.lease.dto.OrderRequestDto;
import july.lease.dto.ProductMessageInfoDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

	private final MessageDao messageDao;
	
	@Override
	public List<Message> getMessage(Long myId, Long roomNo) {
		
		// 메세지 읽음 처리
		messageDao.readCheck(myId, roomNo);
		
		// 메세지 조회
		return messageDao.getMessage(roomNo);
	}

	@Override
	public ProductMessageInfoDto getOneProductInfo(Long productId) {
		// 제품 정보
		return messageDao.getOneProductInfo(productId);
	}
	
	
	@Override
	public List<MyAllMessageListDto> getMyAllMessageList(Long memberId) {
		// 메세지 리스트 조회
		List<MyAllMessageListDto> list = messageDao.getMyAllMessageList(memberId);
		
		// 안읽은 메세지 개수 조회
		list.forEach(msg -> {
			msg.setNoReadMcnt(messageDao.countUnreadMessage(memberId, msg.getRoomNo()));
		});
		
		return list;
	}

	@Transactional
	@Override
	public int insertMessage(Message msgVo) {
		int res=0;
		// 메세지 삽입
		res = messageDao.insertMessageContent(msgVo);
		res = messageDao.insertMessage(msgVo);
		
		return res;
	}

	@Override
	public Long findRoomNo(Long myId, Long productId) {
		// roomNo 찾기
		Long roomNo = messageDao.findRoomNo(myId, productId);
		Long maxRoomNo = messageDao.maxRoomNo();
		
		if(roomNo == null) {
			// 룸넘버가 null이면 roomNo 최대값+1 해주기
			roomNo = (maxRoomNo == null ? 0 : maxRoomNo)+1;
		}
		return roomNo;
	}

	@Override
	public int countUnreadMessage(Long yourId, Long roomNo) {
		return messageDao.countUnreadMessage(yourId, roomNo);
	}

	@Override
	public int deleteMessage(Long roomNo) {
		// TODO Auto-generated method stub
		
		int res = messageDao.deleteMessage(roomNo);
		
		return res;
	}

	@Override
	public int orderInsertMessage(Long memberId, Long productId, OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		
		// 대여신청 메세지 보내기
		String mesage = orderRequestDto.getOrderRentStartDate() +"~"+ orderRequestDto.getOrderRentEndDate()+"기간 대여  신청되었습니다.";
		
		// 프로덕트 아이디로 판매자 아이디 검색해오기
		ProductMessageInfoDto pInfo = messageDao.getOneProductInfo(productId);
		
		// 룸넘버 검색하기 
		Long roomNo = findRoomNo(memberId, productId);
		
		// myId yourId productId roomNo
		Message msgVo = new Message(memberId, pInfo.getMemberId(), productId, roomNo, mesage);

		return insertMessage(msgVo);
	}

}
