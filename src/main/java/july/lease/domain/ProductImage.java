package july.lease.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductImage {
	
	private Long productImageId;
	private Long productId;
	private String uploadImageName;
	private String storeImageName;
	
	public ProductImage() {
	}

	public ProductImage(Long productId, String uploadImageName, String storeImageName) {
		this.productId = productId;
		this.uploadImageName = uploadImageName;
		this.storeImageName = storeImageName;
	}
	
	
	
	
	
	
}
