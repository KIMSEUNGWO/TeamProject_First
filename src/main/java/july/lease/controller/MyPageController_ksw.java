package july.lease.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import july.lease.dao.member.MemberDao;
import july.lease.domain.Member;
import july.lease.domain.Product;
import july.lease.dto.MyPageMainInfoDto;
import july.lease.dto.MyPageOrderItemsDto;
import july.lease.dto.MyPageRentItemsDto;
import july.lease.dto.MyPageSellItemsDto;
import july.lease.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MyPageController_ksw {
	
	private final MyPageService myPageService;
	private final MemberDao memberDao;
	
	@GetMapping("/{memberId}")
	public String MyPageMain(@PathVariable Long memberId, Model model, HttpServletRequest request) {
		
		String name = myPageService.findByName(memberId); // 마이페이지 닉네임
		if (name == null) return "redirect:../login";
		
		
		List<Integer> compressInfo = myPageService.compressInfo(memberId);
		List<MyPageMainInfoDto> confirmInfo = myPageService.confirmCheck(memberId);
		System.out.println(confirmInfo);;
		model.addAttribute("myName", name);
		model.addAttribute("compressInfo", compressInfo);
		model.addAttribute("confirmInfo", confirmInfo);
		return "Project_mypage_main";
	}
	
	@GetMapping("/{memberId}/orderlist")
	public String OrderList(@PathVariable Long memberId, Model model) {
		String name = myPageService.findByName(memberId);
		if (name == null) return "redirect:../login";
		model.addAttribute("myName", name);
		
		List<MyPageOrderItemsDto> list = myPageService.orderItems(memberId);
		model.addAttribute("orderItems", list);
		
		return "Project_mypage_orderlist";
	}
	
	@GetMapping("/{memberId}/items")
	public String sellList(@PathVariable Long memberId, Model model) {
		String name = myPageService.findByName(memberId);
		if (name == null) return "redirect:../login";
		model.addAttribute("myName", name);
		
		List<Object> compressInfo = myPageService.sellItemsInfo(memberId);
		List<MyPageSellItemsDto> itemSellList = myPageService.sellItemsList(memberId);
		System.out.println(itemSellList);
		List<Product> endItems = myPageService.endItems(memberId);
		model.addAttribute("sellItemInfo", compressInfo);
		model.addAttribute("itemSellList", itemSellList);
		model.addAttribute("endItems", endItems);
		
		return "Project_mypage_selllist";
	}

	@GetMapping("/{memberId}/check")
	public String editInfoCheck(@PathVariable Long memberId, Model model) {
		String name = myPageService.findByName(memberId);
		if (name == null) return "redirect:../login";
		model.addAttribute("myName", name);
		
		return "Project_mypage_editinfo_login";
	}
	
	@PostMapping("/{memberId}/check")
	public String editInfoCheckPost(@PathVariable Long memberId, String userPw, RedirectAttributes redirectAttributes) {
	
		Member member = myPageService.findByPw(memberId, userPw);
		if (member == null) {
			redirectAttributes.addFlashAttribute("errors", "비밀번호가 일치하지 않습니다.");
			return "redirect:./check";
		}
		redirectAttributes.addFlashAttribute("member", member);
		return "redirect:./editinfo";
	}
	
	
	@GetMapping("/{memberId}/editinfo")
	public String editInfo(@PathVariable Long memberId, Model model) {
		String name = myPageService.findByName(memberId);
		if (name == null) return "redirect:../login";
		model.addAttribute("myName", name);
		return "Project_mypage_editinfo";
	}
	
	@PostMapping("/{memberId}/editinfo")
	public String edit(@PathVariable Long memberId, Model model, String userPw, String userPwCheck, RedirectAttributes redirectAttributes) {
		String name = myPageService.findByName(memberId);
		if (name == null) return "redirect:../login";
		model.addAttribute("myName", name);
		
		if (!myPageService.comparePw(memberId, userPw, userPwCheck)) {
			Member failMember = memberDao.selectOne(memberId);
			redirectAttributes.addFlashAttribute("message", "현재 비밀번호와 같거나 비밀번호가 올바르지 않습니다");
			redirectAttributes.addFlashAttribute("member", failMember);
			return "redirect:./editinfo";
		}
		Member member = myPageService.updatePw(memberId, userPw);
		redirectAttributes.addFlashAttribute("message", "비밀번호를 변경하였습니다.");
		redirectAttributes.addFlashAttribute("member", member);
		return "redirect:./editinfo";
	}
	
	@GetMapping("/{memberId}/items/now")
	public String now(@PathVariable Long memberId, Model model) {
		String name = myPageService.findByName(memberId);
		if (name == null) return "redirect:../login";
		model.addAttribute("myName", name);
		
		List<MyPageRentItemsDto> rentList = myPageService.rentList(memberId);
		model.addAttribute("rentList", rentList);
		
		
		return "Project_mypage_rent_item";
	}
}
