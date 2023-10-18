package july.lease.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import july.lease.dto.HeaderDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class HeaderServicetTest {

	@Autowired
	private HeaderService headerService;
	
	@Test
	public void getCategoryTest() {
		List<HeaderDto> list = headerService.getCategory();
		
		list.forEach(cate->{
			log.info(cate.getCategoryName());
			log.info(cate.getCategoryId2().toString());
		});
	}
	
	@Test
	public void notReadMessageCnt() {
		log.info(String.valueOf(headerService.notReadMessageCnt(2L)));
	}
}
