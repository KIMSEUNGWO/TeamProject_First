package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemListDto {
	String storeImageName;
	String productName;
	String productPrice;
	String productId;
	String startDate;
	String endDate;
	String categoryId;
	String search;
	
	public ItemListDto() {
		
	}

	public ItemListDto(String storeImageName, String productName, String productPrice, String productId,
			String startDate, String endDate, String categoryId, String search) {
		super();
		this.storeImageName = storeImageName;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productId = productId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.search = search;
		this.categoryId = categoryId;
	}
	
}
