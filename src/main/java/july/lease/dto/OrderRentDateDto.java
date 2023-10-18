package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderRentDateDto {
	
	private String orderRentStartDate;
	private String orderRentEndDate;
	
	public OrderRentDateDto() {
	}

	public OrderRentDateDto(String orderRentStartDate, String orderRentEndDate) {
		this.orderRentStartDate = orderRentStartDate;
		this.orderRentEndDate = orderRentEndDate;
	}
	
}
