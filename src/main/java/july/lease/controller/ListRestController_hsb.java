package july.lease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import july.lease.domain.Criteria;
import july.lease.dto.HomeItemDto;
import july.lease.dto.ItemListDto;
import july.lease.service.HomeService;
import july.lease.service.ItemListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/list")
@Slf4j
public class ListRestController_hsb {

    private ItemListService itemListService;

    @GetMapping(value = "/items", produces = "application/json")
    public ResponseEntity<List<ItemListDto>> getItemAJAX(@RequestBody Criteria cri) {

		List<ItemListDto> lists = itemListService.getItemListAJAX(cri.getSearch(), cri.getStartDate(), cri.getEndDate(), cri.getCategoryId(), cri.getStartRow(), cri.getEndRow());
		System.out.println("lists : " + lists);
		
		if (lists == null) {
			return new ResponseEntity<>(lists, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(lists, HttpStatus.OK);
    
	
 }
}
