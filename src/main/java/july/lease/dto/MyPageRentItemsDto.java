package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MyPageRentItemsDto {
	
	private Long orderId;
	private Long productId;
	private String productImage;
	private String productName;
	private String productStartDate;
	private String productEndDate;
	private String productDateCount;
	private String productStatus;
	
	
	public MyPageRentItemsDto(Long orderId, Long productId, String productImage, String productName,
			String productStartDate, String productEndDate, String productDateCount, String productStatus) {
		this.orderId = orderId;
		this.productId = productId;
		this.productImage = productImage;
		this.productName = productName;
		this.productStartDate = productStartDate;
		this.productEndDate = productEndDate;
		this.productDateCount = productDateCount;
		this.productStatus = productStatus;
	}
	
	
	
	
	

	
}
