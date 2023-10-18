package july.lease.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import july.lease.dto.HomeItemDto;
import july.lease.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class HomeRestController_hsb {
	
	private final HomeService homeService;
	
	@GetMapping(value = "main", produces = "application/json")
	public ResponseEntity<List<HomeItemDto>> allList(Long startRow, Long endRow){
		List<HomeItemDto> allList = homeService.getAllItemAJAX(startRow, endRow);
		if (allList == null) return new ResponseEntity<List<HomeItemDto>>(allList, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<HomeItemDto>>(allList, HttpStatus.OK);
	}
		
}
