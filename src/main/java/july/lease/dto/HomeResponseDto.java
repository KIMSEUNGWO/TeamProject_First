package july.lease.dto;

import java.util.List;

import july.lease.domain.ProductImage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HomeResponseDto {
	
	private String productName;
	private Integer productPrice;
	private List<ProductImage> images;
	
	public HomeResponseDto() {
	}

	public HomeResponseDto(String productName, Integer productPrice, List<ProductImage> images) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.images = images;
	}
	
	
	
	

}
