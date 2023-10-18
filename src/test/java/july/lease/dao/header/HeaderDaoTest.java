package july.lease.dao.header;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import july.lease.domain.Category;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class HeaderDaoTest {

	@Autowired	
	private HeaderDao headerDao;
	
	@Test
	public void getCategoryTest() {
		
		List<Category> list = headerDao.getCategory();
		
		list.forEach(cate->{
			log.info(cate.getCategoryName());
		});
	}
	
	@Test
	public void notReadMessageCnt() {
		
		log.info(String.valueOf(headerDao.notReadMessageCnt(2L)));
	}
}
