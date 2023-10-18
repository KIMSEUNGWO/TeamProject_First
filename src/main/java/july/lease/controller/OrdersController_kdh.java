package july.lease.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import july.lease.domain.Orders;
import july.lease.dto.CheckOrderRequestDto;
import july.lease.dto.OrderRequestDto;
import july.lease.service.MessageService;
import july.lease.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrdersController_kdh {
	
	private final OrdersService ordersService;
	private final MessageService messageService;
	
	
	@GetMapping("/products/{productId}/orders/confirm")
	public String checkOrder(@ModelAttribute("order") CheckOrderRequestDto orderRequestDto) {
		return "Project_product_comp";
	}
	
	@PostMapping("/products/{productId}/orders")
	public String order(@Validated @ModelAttribute OrderRequestDto orderRequestDto,
			BindingResult bindingResult,@PathVariable Long productId,
			@SessionAttribute(name = "memberId", required = false)Long memberId, Model model) {
		
		//log.info("OrdersController orderRequestDto={}", orderRequestDto);
		Orders saveOrder = null;
		try {		
			 saveOrder = ordersService.save(memberId, productId, orderRequestDto);
		} catch(IllegalArgumentException e) {
			//log.info("잘못된 날짜 선택");
			bindingResult.reject("InvalidDates");
			return "redirect:/products/" + productId;
		}

		
		model.addAttribute("order", saveOrder);

		// 대여신청 메세지
		int res = messageService.orderInsertMessage(memberId, productId, orderRequestDto);

		return "redirect:/products/" + productId + "/orders/confirm";
	}
	
}
