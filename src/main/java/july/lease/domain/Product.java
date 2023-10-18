package july.lease.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
	
	private Long productId;
	private Long memberId;
	private Long categoryId;
	private Long categoryId2; //카테고리 아이디2 추가
	private Long categoryId3; //카테고리 아이디3 추가
	private String categoryName; //카테고리 이름 추가
	private String categoryName2;
	private String productName;
	private Integer productPrice;
	private String productContent;
	private String location;
	private String productCreateDate;
	private char productEndStatus;
	private List<ProductImage> images;
	private int productStatus;
	private int productVisitCount;
	
	public Product() {
	}

	public Product(Long memberId, Long categoryId, String productName, Integer productPrice, String productContent,
			String location) {
		this.memberId = memberId;
		this.categoryId = categoryId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productContent = productContent;
		this.location = location;
	}
	
	

}