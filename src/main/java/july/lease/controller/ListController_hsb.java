package july.lease.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import july.lease.domain.Criteria;
import july.lease.dto.ItemListDto;
import july.lease.service.ItemListService;

@Controller
public class ListController_hsb {

    private final ItemListService itemListService;

    public ListController_hsb(ItemListService itemListService) {
        this.itemListService = itemListService;
    }

//    @ModelAttribute("cri")
    public Criteria createCriteria() {
        Criteria cri = new Criteria();
        cri.setPageStart();
        return cri;
    }
    
//    @GetMapping("/list")
    public String list( Criteria cri,
			            @RequestParam(name = "page", defaultValue = "1") int page, 
			            Model model) {
    	
    	cri.setPageStart(); // 페이지 시작 설정 추가
    	System.out.println("listController");
    	int perPageNum = 12; // 페이지당 아이템 수

        long startRow = (page - 1) * perPageNum + 1;
        long endRow = startRow + perPageNum - 1;
        System.out.println(cri+"==========================");
        List<ItemListDto> items = itemListService.getItemListAJAX(cri.getSearch(), cri.getStartDate(), cri.getEndDate(), cri.getCategoryId(), startRow, endRow);      
        System.out.println("items : " + items);       
        model.addAttribute("items", items);
        model.addAttribute("cri", cri);
        System.out.println(cri);
     
        return "Project_list"; // 뷰 이름을 반환합니다.
    }
}
