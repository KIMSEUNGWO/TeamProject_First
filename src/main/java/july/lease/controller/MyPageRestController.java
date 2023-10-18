package july.lease.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import july.lease.dto.MyPageMainInfoDto;
import july.lease.dto.MyPageOrderItemsDto;
import july.lease.dto.MyPageSellItemsDto;
import july.lease.dto.MyPageSellitemsModalDto;
import july.lease.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MyPageRestController {
	
	private final MyPageService myPageService;
	
	
	@GetMapping(value = "/{memberId}/main", produces = "application/json")
	public ResponseEntity<List<MyPageMainInfoDto>> confirm(@PathVariable Long memberId,	Long startDate, Long endDate){
		List<MyPageMainInfoDto> list = myPageService.confirmCheckAJAX(memberId, startDate, endDate);
		if (list == null) return new ResponseEntity<List<MyPageMainInfoDto>>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<MyPageMainInfoDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{memberId}/items/sell", produces = "application/json")
	public ResponseEntity<List<MyPageSellItemsDto>> sell(@PathVariable Long memberId, Long startDate, Long endDate){
		List<MyPageSellItemsDto> list = myPageService.sellItemsListAJAX(memberId, startDate, endDate);
		if (list == null) return new ResponseEntity<List<MyPageSellItemsDto>>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<MyPageSellItemsDto>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/{memberId}/orderlist/order", produces = "application/json")
	public ResponseEntity<List<MyPageOrderItemsDto>> order(@PathVariable Long memberId, Long startDate, Long endDate){
		List<MyPageOrderItemsDto> list = myPageService.orderItemsAJAX(memberId, startDate, endDate);
		if (list == null) return new ResponseEntity<List<MyPageOrderItemsDto>>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<MyPageOrderItemsDto>>(list, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/{memberId}/confirm", produces="application/json")
	public ResponseEntity<String> confirm(@PathVariable Long memberId, String type, Long orderId, Long productId) {
		System.out.println("confirm RestController");
		if (!myPageService.validConfirm(memberId, productId, orderId)) return new ResponseEntity<String>("검증실패",HttpStatus.NOT_FOUND);
		
		Long btn = 5L; // 밑에 해당없으면 반납처리
		if (type.equals("confirmBtn")) btn = 3L; // 확정
		else if (type.equals("cancelBtn")) btn = 1L; // 보류
		else if (type.equals("sendBtn")) btn = 4L; // 승인
		System.out.println(btn);
		if (myPageService.confirmUpdate(orderId, productId, btn)) {
			if (btn == 4L) {
				if (myPageService.productRent(productId)) {
					return new ResponseEntity<String>("업데이트 성공", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("업데이트 실패", HttpStatus.NOT_FOUND);
				}
			} else if (btn == 5L) {
				if (myPageService.productReturn(productId)) {
					return new ResponseEntity<String>("업데이트 성공", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("업데이트 실패", HttpStatus.NOT_FOUND);
				}
			}
			return new ResponseEntity<String>("업데이트 성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("업데이트 실패", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/{memberId}/items/modal", produces = "application/json")
	public ResponseEntity<MyPageSellitemsModalDto> modal(@PathVariable Long memberId, Long productId){
		MyPageSellitemsModalDto modal = myPageService.sellItemsModal(memberId, productId);
		if (modal == null) return new ResponseEntity<MyPageSellitemsModalDto>(modal, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<MyPageSellitemsModalDto>(modal, HttpStatus.OK);
	}
}
