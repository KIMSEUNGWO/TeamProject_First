package july.lease.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import july.lease.dao.mypage.MyPageMapper;
import july.lease.dto.MyPageMainInfoDto;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Slf4j
public class myPageServiceTest_KSW {

	@Autowired
	private MyPageMapper mapper;
	
	@Test
	void findByname() {
		List<MyPageMainInfoDto> list = mapper.confirmCheck(1L);
		System.out.println(list);
		
	}
	

}
