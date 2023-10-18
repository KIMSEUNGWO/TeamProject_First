package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderRequestDto {
	
	private String orderRentStartDate;
	private String orderRentEndDate;
	private Integer orderPrice;
	private String orderRequest;
	private String rentDate;
	
	public OrderRequestDto() {
	}

	public OrderRequestDto(String orderRentStartDate,
			String orderRentEndDate, Integer orderPrice, String orderRequest, String rentDate) {

		this.orderRentStartDate = orderRentStartDate;
		this.orderRentEndDate = orderRentEndDate;
		this.orderPrice = orderPrice;
		this.orderRequest = orderRequest;
		this.rentDate = rentDate;
	}
	
	
	
	

}
