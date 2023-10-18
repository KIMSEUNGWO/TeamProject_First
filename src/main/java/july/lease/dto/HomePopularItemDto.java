package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HomePopularItemDto {
		String storeImageName;
		String productName;
		String productPrice;
		String productId;
		
		public HomePopularItemDto() {
			
		}
		
		public HomePopularItemDto(String storeImageName, String productName, String productPrice, String productId) {
			super();
			this.storeImageName = storeImageName;
			this.productName = productName;
			this.productPrice = productPrice;
			this.productId = productId;
		}
}
