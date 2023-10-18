package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductListDto {
	
	private String memberNickname;
	private Long productId;
	private String productName;
	private Integer productPrice;
	private String uploadImageName;
	private String storeImageName;
	
	public ProductListDto() {
	}

	public ProductListDto(String memberNickname, Long productId, String productName, Integer productPrice,
			String uploadImageName, String storeImageName) {
		this.memberNickname = memberNickname;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.uploadImageName = uploadImageName;
		this.storeImageName = storeImageName;
	}
	
	
	
	
	
}
