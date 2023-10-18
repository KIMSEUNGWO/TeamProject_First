package july.lease.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import july.lease.domain.Message;
import july.lease.dto.MyAllMessageListDto;
import july.lease.dto.ProductMessageInfoDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class MessageServiceTest_ksh {

	@Autowired
	private MessageService messageService;
	
	@Test
	public void getMessageList() {
		
		List<Message> list = messageService.getMessage(2L, 1L);
		
		for(int i=0; i<list.size(); i++) {
			log.info("message={}", list.get(i));
		}
	}
	
	@Test
	public void getOneProductInfoTest() {
		
		// productId
		ProductMessageInfoDto pInfo = messageService.getOneProductInfo(6L);
		
		log.info("========productInfo========");
		log.info("========productInfo========");
		log.info(pInfo.getProductName());
		log.info(pInfo.getSellerName());
		log.info(String.valueOf(pInfo.getProductPrice()));
		log.info(pInfo.getLocation());
		log.info(pInfo.getStoreImageName());
		
	}
	
	@Test
	public void getMyAllMessageList() {
		
		List<MyAllMessageListDto> list = messageService.getMyAllMessageList(2L);
		
		for(int i=0; i<list.size(); i++) {
			log.info("MyAllMessageList"+list.get(i));
		}
		
	}
	
	@Test
	public void insertMessageTest() {
		Message msg = new Message();
		msg.setMyId(1L);
		msg.setProductId(6L);
		msg.setRoomNo(1L);
		msg.setMessageText("테스트으1101010100110");
		
		log.info("yourId={}", msg.getYourId());
		
		try {
			int res = messageService.insertMessage(msg);
		}catch(Exception e) {
			log.info(e.getMessage());
		}
	}
	
	@Test
	public void findRoomNo() {
		log.info("findRoomNo");
		
		Long roomNo = messageService.findRoomNo(1L, 6L);
		
		log.info("roomNo={}", roomNo);
	}
	
	@Test
	public void deleteTest() {
		int res = messageService.deleteMessage(1L);
		
		log.info("res={}", res);
	}
}
