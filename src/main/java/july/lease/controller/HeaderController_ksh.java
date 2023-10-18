package july.lease.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import july.lease.dto.HeaderDto;
import july.lease.service.HeaderService;
import july.lease.service.MessageService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HeaderController_ksh {

	@Autowired
	private HeaderService headerService;
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/category")
	public Map<String, Object> getCategory(HttpServletRequest request) {

		//log.info("category호출");
		
		Map<String, Object> map = new HashMap<>();
		
		List<HeaderDto> list = headerService.getCategory();
		
		HttpSession session = request.getSession(); 
	    Long sessionMemberId = (Long)session.getAttribute("memberId");
		
		// 로그인 유무와 상관없이 카테고리 리스트 맵에 put
		map.put("list", list);
		
		// 로그인하면 사용자한테 안읽은 쪽지 개수 보여주기
		if(sessionMemberId != null) {
			int cntMsg = messageService.countUnreadMessage(sessionMemberId, null);
			map.put("notReadM", cntMsg);			
		} else {
			map.put("notReadM", "notlogin");
		}
		return map;
	}
	
}
