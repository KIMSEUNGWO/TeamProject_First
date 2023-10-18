package july.lease.dto;

import java.util.List;

import july.lease.domain.ProductImage;
import july.lease.domain.RentDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDetailResponseDto {

	private Long memberId;
	private String productName;
	private Integer productPrice;
	private Long categoryId;
	private Long categoryId2;
	private String categoryName;
	private String categoryName2;
	private String productContent;
	private char productEndStatus;
	private String location;
	private int productVisitCount;
	private String productCreateDate;
	private List<ProductImage> images;
	private List<RentDate> rentDates;
	
	public ProductDetailResponseDto() {
	}
	
	public ProductDetailResponseDto(Long memberId, String productName, Integer productPrice, Long categoryId,
			Long categoryId2, String categoryName, String categoryName2, String productContent, char productEndStatus, String location,
			int productVisitCount, String productCreateDate,
			List<ProductImage> images, List<RentDate> rentDates) {
		this.memberId = memberId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.categoryId = categoryId;
		this.categoryId2 = categoryId2;
		this.categoryName = categoryName;
		this.categoryName2 = categoryName2;
		this.productContent = productContent;
		this.productEndStatus = productEndStatus;
		this.location = location;
		this.productVisitCount = productVisitCount;
		this.productCreateDate = productCreateDate;
		this.images = images;
		this.rentDates = rentDates;
	}
	
	
}
