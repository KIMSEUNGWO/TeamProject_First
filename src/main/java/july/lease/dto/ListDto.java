package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ListDto {

	private String productId;
	private String storeImageName;
	private String productName;
	private String productPrice;
	private String category;
}
