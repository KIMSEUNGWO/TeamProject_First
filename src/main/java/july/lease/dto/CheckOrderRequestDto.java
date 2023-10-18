package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckOrderRequestDto {
	
	private String sellerName;
	private String productName;
	private String uploadImageName;
	private String storeImageName;
	private String orderRentStartDate;
	private String orderRentEndDate;
	private Integer productPrice;
	private Integer orderPrice; //최종가격
	
	public CheckOrderRequestDto() {
	}

	public CheckOrderRequestDto(String sellerName, String productName, String uploadImageName, String storeImageName,
			String orderRentStartDate, String orderRentEndDate, Integer productPrice, Integer orderPrice) {
		this.sellerName = sellerName;
		this.productName = productName;
		this.uploadImageName = uploadImageName;
		this.storeImageName = storeImageName;
		this.orderRentStartDate = orderRentStartDate;
		this.orderRentEndDate = orderRentEndDate;
		this.productPrice = productPrice;
		this.orderPrice = orderPrice;
	}
	
	
	
	
}
