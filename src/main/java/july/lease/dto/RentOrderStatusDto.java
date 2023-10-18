package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RentOrderStatusDto {
	
	private String rentAbleStartDate;
	private String rentAbleEndDate;
	private String orderRentStartDate;
	private String orderRentEndDate;
	private int orderConfirmStatus;
	
	public RentOrderStatusDto() {
	}

	public RentOrderStatusDto(String rentAbleStartDate, String rentAbleEndDate, String orderRentStartDate,
			String orderRentEndDate, int orderConfirmStatus) {
		this.rentAbleStartDate = rentAbleStartDate;
		this.rentAbleEndDate = rentAbleEndDate;
		this.orderRentStartDate = orderRentStartDate;
		this.orderRentEndDate = orderRentEndDate;
		this.orderConfirmStatus = orderConfirmStatus;
	}
	
	
	
	
	
}
