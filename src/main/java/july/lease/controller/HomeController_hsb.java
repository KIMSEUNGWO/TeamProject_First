package july.lease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import july.lease.domain.Criteria;
import july.lease.dto.HomeItemDto;
import july.lease.dto.HomePopularItemDto;
import july.lease.service.HomeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController_hsb {

	@Autowired
	private HomeService homeService;
	
	@GetMapping("/")
	public String Home(Criteria cri, Model model) {
		
		
		List<HomePopularItemDto> popularList = homeService.papularItem();
		List<HomeItemDto> items = homeService.getAllItem(cri);
		System.out.println("items : " + items);
		model.addAttribute("popularList", popularList);
		model.addAttribute("items", items);
	
		return "Project_home";
	}	

}

	

