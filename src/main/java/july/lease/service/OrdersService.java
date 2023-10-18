package july.lease.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import july.lease.dao.order.OrdersDao;
import july.lease.dao.rentDate.RentDateDao;
import july.lease.domain.Orders;
import july.lease.dto.OrderRequestDto;
import july.lease.dto.RentAbleDateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {
	
	private final OrdersDao ordersDao;
	private final RentDateDao rentDateDao;
	
	public int checkOrdersIfValid(Long productId) {
		int result = ordersDao.checkOrdersIfValid(productId);
		//log.info("첵오더스={}", result);
		return result;
	}
	
	public String findOrderRentDateByProductId(Long productId) {
		String result = ordersDao.findOrderRentDateByProductId(productId);
		//log.info("OrderService findOrderRentDateByProductId={}", result);
		return result;		
	}
	
	public Orders save(Long memberId, Long productId, OrderRequestDto orderRequestDto) {
		
		String rentDate = orderRequestDto.getRentDate();
		
		//log.info("=========check ={}", rentDate);
		
		List<RentAbleDateDto> rentAbleDates = rentDateDao.findRentAbleDatesWithinOrderDates(
				productId, orderRequestDto.getOrderRentStartDate(), orderRequestDto.getOrderRentEndDate());
		
		getStartDateIfInRangeWithOrderRentEndDate(rentAbleDates, 
				orderRequestDto.getOrderRentStartDate(), orderRequestDto.getOrderRentEndDate());

		String rentDateFirst = 
				getStartDateIfInRangeWithOrderRentStartDate(rentDate, orderRequestDto.getOrderRentStartDate());
		
		Long rentDateId = rentDateDao.findRentDateIdByRentAbleStartDateAndProductId(rentDateFirst, productId);
		
		if(orderRequestDto.getOrderRequest() == null) {
			Orders order = new Orders(memberId, productId, rentDateId, orderRequestDto.getOrderRentStartDate(),
					orderRequestDto.getOrderRentEndDate(), orderRequestDto.getOrderPrice());
			return ordersDao.save(order);
		} else {
			Orders order = new Orders(memberId, productId, rentDateId, orderRequestDto.getOrderRentStartDate(),
					orderRequestDto.getOrderRentEndDate(), orderRequestDto.getOrderPrice(),
					orderRequestDto.getOrderRequest());
			return ordersDao.save(order);
		}

	}
	
	private String getStartDateIfInRangeWithOrderRentStartDate(String rentDate, String orderRentStartDate) {
		
		 String[] dateRanges = rentDate.split(",");

	        for (String range : dateRanges) {
	            String[] dates = range.split("\\|");

	            // orderRentStartDate 값이 2개의 인덱스 값 사이에 있는 경우 0번 인덱스 값을 리턴
	            if (orderRentStartDate.compareTo(dates[0]) >= 0 && orderRentStartDate.compareTo(dates[1]) <= 0) {
	                return dates[0];
	            }
	        }
	        
	        return null;
	}
	
	
	// 8.17-19, 8.21-22 를 대여가능일로 했을때 고객이 8.19-21을 선택했을경우 예외터트리는 로직
	 private static void getStartDateIfInRangeWithOrderRentEndDate(List<RentAbleDateDto> rentAbleDates, String orderRentStartDate, String orderRentEndDate) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        
	        for(int i=0; i<rentAbleDates.size()-1; i++) {

	        	LocalDate endDate1 = LocalDate.parse(rentAbleDates.get(i).getRentAbleEndDate(), formatter);
	        	LocalDate startDate2 = LocalDate.parse(rentAbleDates.get(i+1).getRentAbleStartDate(), formatter);
	        	  	
	        	if(!endDate1.plusDays(1).equals(startDate2)) {
	        		 throw new IllegalArgumentException("날짜를 다시 선택 해 주세요.");
	        	} 
	        }

	    }
	
	public int findConfirmStatusCountByProductId(Long productId) {
		return ordersDao.findConfirmStatusCountByProductId(productId);
	}
	
	
	}


	
	
	

