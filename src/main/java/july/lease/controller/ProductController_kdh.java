package july.lease.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import july.lease.dto.AddProductDto;
import july.lease.dto.EditProductRequestDto;
import july.lease.dto.EditProductResponseDto;
import july.lease.dto.ProductDetailResponseDto;
import july.lease.dto.ProductListDto;
import july.lease.dto.RentAbleRequestDto;
import july.lease.service.OrdersService;
import july.lease.service.ProductService_kdh;
import july.lease.service.RentDateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController_kdh {
	
	private final ProductService_kdh productService_kdh;
	private final OrdersService ordersService;
	private final RentDateService rentDateService;
	
	@GetMapping("/products/add")
	public String addForm(@ModelAttribute("product") AddProductDto productDto) {
		return "Project_product_upload";
	}
	
	@PostMapping("/products/add")
	public String addProduct(@Validated @ModelAttribute("product") AddProductDto productDto, BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			@SessionAttribute(name = "memberId", required = false)Long memberId) throws IOException {
		
		//log.info("check AddProductDto={}", productDto);
		if(bindingResult.hasErrors()) {
			//log.info("errors={}", bindingResult);
			return "Project_product_upload";
		}
		
		Long productId = productService_kdh.addProduct(memberId, productDto);
		
		redirectAttributes.addAttribute("productId", productId);
		
		return "redirect:/products/{productId}";
		
	}
	
	@GetMapping("/products/{productId}/edit")
	public String editForm(@PathVariable Long productId,
			@ModelAttribute("productRequest") EditProductRequestDto productRequest,
			@SessionAttribute(name = "memberId", required = false)Long memberId,
			Model model) {
		
		EditProductResponseDto productResponse = productService_kdh.findByProductIdForEdit(productId);
		
		//판매중단된 경우 상품수정페이지로 들어가려하면 홈 띄우기
		if(productResponse.getProductEndStatus() == 'Y') {
			return "redirect:/";
		}
		
		
		//jsp폼에서 제품상세 <form:textarea>에서 path기능을 사용하기 위한 코드
		productRequest.setProductContent(productResponse.getProductContent());
		productRequest.setCategoryId(productResponse.getCategoryId());
		productRequest.setCategoryId3(productResponse.getCategoryId3());
		productRequest.setRegion1(productResponse.getRegion1());
		productRequest.setRegion2(productResponse.getRegion2());
		//로그인한 회원이 올린 상품인지 확인
		if(productResponse.getMemberId() != memberId){
			throw new IllegalStateException();
		}
		
		model.addAttribute("productResponse", productResponse);
		model.addAttribute("productId", productId);
		
		return "Project_product_edit";
	}
	
	@PostMapping("/products/{productId}/edit")
	public String editProduct(@PathVariable Long productId, Model model,
			@Validated @ModelAttribute("productRequest") EditProductRequestDto editProductRequestDto,
			BindingResult bindingResult) throws IOException {
		//log.info("ProductContorller_kdh editProduct={}", editProductRequestDto);
		
		if(bindingResult.hasErrors()) {
			//log.info("errors={}", bindingResult);
			return "Project_product_edit";
		}
		
		productService_kdh.editProduct(productId, editProductRequestDto);
	
		return "redirect:/products/" + productId;
		
	}
	
	@ResponseBody
	@GetMapping(value = "/products/{productId}/edit/rentdate/orders", produces = "application/json")
	public int rentOrderStatus(String rentAbleStartDate, String rentAbleEndDate,
			@PathVariable Long productId) {
		//log.info("ProductController_kdh rentOrderStatus={}", rentAbleStartDate);
		RentAbleRequestDto rentAble = new RentAbleRequestDto(rentAbleStartDate, rentAbleEndDate);
		//log.info("================= rentAble ={}",rentAble);
		return productService_kdh.rentOrderStatusSize(productId, rentAble);
	}
	
	@ResponseBody
	@GetMapping(value = "/products/{productId}/orders/count")
	public int checkOrders(@PathVariable Long productId) {
		//log.info("checkOrders ==================");
		return ordersService.checkOrdersIfValid(productId);
	}
	
	@PostMapping("/products/{productId}/delete")
	public String delete(@PathVariable Long productId) {
		productService_kdh.delete(productId);
		return "redirect:/products/" + productId;
	}
	
	@PostMapping("/products/{productId}/rerent")
	public String reRent(@PathVariable Long productId) {
		productService_kdh.reRent(productId);
		return "redirect:/products/" + productId;
	}
	
	
	
	
	@GetMapping("/products/{productId}")
	public String product(@PathVariable Long productId, Model model,
			@SessionAttribute(name = "memberId", required = false)Long memberId,
			HttpServletRequest request, HttpServletResponse response) {
		
		ProductDetailResponseDto responseDto = productService_kdh.findByProductIdForProductDetail(productId);
		List<ProductListDto> list = productService_kdh.findByMemberIdExceptProductWithProductId(responseDto.getMemberId(), productId);
		String orderRentDateStr = ordersService.findOrderRentDateByProductId(productId);
		String rentDateStr = rentDateService.findRentAbleDateByProductId(productId);
		int confirmStatusCount = ordersService.findConfirmStatusCountByProductId(productId);
		String memberNickname = productService_kdh.findNicknameByProductId(productId);
		
		visitCountValidation(productId, responseDto, request, response);
		
		boolean status = true;
		if(memberId != responseDto.getMemberId()) {
			status = false;
		} 
		model.addAttribute("isMyItem", status);
		
		model.addAttribute("product", responseDto);
		model.addAttribute("productList", list);
		model.addAttribute("productsCount", list.size()+1);
		model.addAttribute("orderRentDate", orderRentDateStr);
		model.addAttribute("rentDate", rentDateStr);
		model.addAttribute("confirmStatusCount", confirmStatusCount);
		model.addAttribute("nickname", memberNickname);
		
	
		return "Project_product_details";
	}
	
	private void visitCountValidation(Long productId, ProductDetailResponseDto responseDto, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = null;
        if (request.getCookies() != null) {
            cookie = Arrays.stream(request.getCookies())
                    .filter(c -> c.getName().equals("productView")) // productView 쿠키가 있는지 필터링함
                    .findFirst()// filter조건에 일치하는 가장 앞에 있는 요소 1개를 Optional로 리턴함. 없으면 empty 리턴
                    .map(c -> { // Optional에 Cookie가 있으면 꺼내서 수행
                        if (!c.getValue().contains("[" + productId + "]")) {
                            //log.info("쿠키가 있어서 실행");
                            productService_kdh.addVisitCount(productId, responseDto);
                            c.setValue(c.getValue() + "[" + productId + "]");
                        }
                        return c;
                    })
                    .orElseGet(() -> { // Optional에 Cookie가 없으면 수행
                        //log.info("쿠키가 없어서 실행");
                        productService_kdh.addVisitCount(productId, responseDto);
                        return new Cookie("productView", "[" + productId + "]");
                    });
        } else {
        	productService_kdh.addVisitCount(productId, responseDto);
            cookie = new Cookie("postView", "[" + productId + "]");
        }

        long todayEndSecond = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        cookie.setPath("/"); // 모든 경로에서 접근 가능
        cookie.setMaxAge((int) (todayEndSecond - currentSecond)); // 오늘 하루 자정까지 남은 시간초 설정
        response.addCookie(cookie);
    }
	
	@GetMapping("/products/{productId}/getImage")
	private @ResponseBody List<String> getImage(@PathVariable Long productId){
		List<String> list = productService_kdh.getImage(productId);
		return list;
	}
	

}