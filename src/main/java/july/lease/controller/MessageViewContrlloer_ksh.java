package july.lease.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import july.lease.dto.MyAllMessageListDto;
import july.lease.dto.ProductMessageInfoDto;
import july.lease.service.MessageService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageViewContrlloer_ksh{
	
	private final MessageService messageService;

	@GetMapping("/members/{memberId}/messages/{productId}/{roomNo}")
	public String productInfoMessageView(@PathVariable("memberId") Long memberId, 
			@PathVariable("productId") Long productId, @PathVariable("roomNo") Long roomNo, Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		Long sessionMemberId = (Long)session.getAttribute("memberId");
		
		
		// url접근 막기
		if (sessionMemberId != memberId) {
			throw new IllegalStateException();
		} 
		
		if(roomNo == 0L) {
			roomNo = messageService.findRoomNo(memberId, productId);
			String redirectUrl = "/members/" + memberId + "/messages/" + productId + "/" + roomNo;
            return "redirect:" + redirectUrl;
		}
		
		ProductMessageInfoDto pInfo = messageService.getOneProductInfo(productId);
		model.addAttribute("pInfo", pInfo);
		
		return "Project_chat";
	}
	
	@GetMapping("/members/{memberId}/messages")
	public String messageListView(@PathVariable("memberId") Long memberId, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Long sessionMemberId = (Long)session.getAttribute("memberId");

		// url접근 막기
		if (!(sessionMemberId + "").equals(memberId + "")) {
			throw new IllegalStateException();
		} 
		
		List<MyAllMessageListDto> allChatList = messageService.getMyAllMessageList(memberId);
		model.addAttribute("allChatList", allChatList);
		return "Project_chaLlist";
	}
	
}
