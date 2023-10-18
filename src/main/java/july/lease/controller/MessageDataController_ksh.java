package july.lease.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import july.lease.domain.Message;
import july.lease.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageDataController_ksh{
	
	private final MessageService messageService;
	
	@GetMapping("/messages/{memberId}/{productId}/{roomNo}")
	public Map<String, Object> getMessage(@PathVariable("memberId") Long memberId, 
			@PathVariable("productId") Long productId, @PathVariable("roomNo") Long roomNo, Model model) {

		Map<String, Object> map = new HashMap<>();
		
		List<Message> list = messageService.getMessage(memberId, roomNo);
		
		map.put("msgList", list);
		
		return map;
	}
	
	@PostMapping("/messages/insert")
	public Map<String, Object> getMessage(@RequestBody Message msgVo) {
		
		//log.info("writetest");
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			int res = messageService.insertMessage(msgVo);
			map.put("result", "success");
					
		}catch (Exception e) {
			map.put("result", "fail");
			map.put("message", e.getMessage());
		}
		
		return map;
	}
	
	@PostMapping("/messages/delete")
	public Map<String, Object> deleteMessage(@RequestBody Long[] no){
		
		Map<String, Object> map = new HashMap<>();
		List<Long> failNo = new ArrayList<>();
				
		for(int i=0; i<no.length; i++) {
			int res = messageService.deleteMessage(no[i]);
			if(res==0) {
				failNo.add(no[i]);				
			} 
		}
		
		if(failNo.size()==0) {
			map.put("message", "success");
		} else {
			map.put("message", failNo);
		}
		
		return map;
	}
}
