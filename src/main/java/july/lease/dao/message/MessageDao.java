package july.lease.dao.message;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import july.lease.domain.Message;
import july.lease.dto.MyAllMessageListDto;
import july.lease.dto.ProductMessageInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
public class MessageDao {

	private final MessageMapper messageMapper;
	
	public List<Message> getMessage(Long roomNo){
		return messageMapper.getMessage(roomNo);
	}
	
	public ProductMessageInfoDto getOneProductInfo(Long productId) {
		return messageMapper.getOneProductInfo(productId);
	}

	
	public List<MyAllMessageListDto> getMyAllMessageList(Long memberId) {
		return messageMapper.getMyAllMessageList(memberId);
	};
	
	public int insertMessageContent(Message msgVo) {
		return messageMapper.insertMessageContent(msgVo);
	}
	
	public int insertMessage(Message msgVo) {
		return messageMapper.insertMessage(msgVo);
	}
	
	public Long findRoomNo(Long myId, Long productId) {
		return messageMapper.findRoomNo(myId, productId);
	}
	
	public Long maxRoomNo() {
		return messageMapper.maxRoomNo();
	}
	
	public int readCheck(Long myId, Long roomNo) {
		return messageMapper.readCheck(myId, roomNo);
	}
	
	public int countUnreadMessage(Long yourId, Long roomNo) {
		return messageMapper.countUnreadMessage(yourId, roomNo);
	}
	
	public int deleteMessage(Long roomNo) {
		return messageMapper.deleteMessage(roomNo);
	}
}
