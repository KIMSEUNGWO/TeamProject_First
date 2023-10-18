package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MyPageOrderItemsDto {

	private String productId;
	private String productImage;
	private String productName;
	private String startDate;
	private String endDate;
	private String rentDate;
	private String sellerName;
	private String productStatus;
	
	public MyPageOrderItemsDto(String productId, String productImage, String productName, String startDate,
			String endDate, String rentDate, String sellerName, String productStatus) {
		this.productId = productId;
		this.productImage = productImage;
		this.productName = productName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rentDate = rentDate;
		this.sellerName = sellerName;
		this.productStatus = productStatus;
	}
	
	
	
}
