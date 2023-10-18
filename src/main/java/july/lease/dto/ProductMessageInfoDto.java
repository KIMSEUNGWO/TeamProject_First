package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductMessageInfoDto {
	
	private String productName; // 상품명
	private String storeImageName;// 이미지
	private String sellerName; // 판매자
	private String location; // 대여위치
	private Long productPrice; // 가격
	private Long memberId; // 판매자아이디
	
	public ProductMessageInfoDto() {
		
	}

	public ProductMessageInfoDto(String productName, String storeImageName, String sellerName, String location,
			Long productPrice, Long memberId) {
		super();
		this.productName = productName;
		this.storeImageName = storeImageName;
		this.sellerName = sellerName;
		this.location = location;
		this.productPrice = productPrice;
		this.memberId = memberId;
	}
	
	
}
