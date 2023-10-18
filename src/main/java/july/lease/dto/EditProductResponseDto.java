package july.lease.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import july.lease.domain.ProductImage;
import july.lease.domain.RentDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditProductResponseDto {
	
	@NotBlank
	private Long memberId;
	@NotBlank
	private String productName;
	@Range(min = 1, max = 1000000) @NotNull
	private Integer productPrice;
	@NotBlank @Size(max=1000) 
	private String productContent;
	@NotNull
	private Long categoryId;
	@NotNull
	private Long categoryId3;
	@NotBlank
	private String region1;
	@NotBlank
	private String region2;
	@NotNull
	List<RentDate> rentDates;
	@Size(min=1, max=8)
	private List<ProductImage> images;
	@NotBlank
	private char productEndStatus;
	
	public EditProductResponseDto() {
	}

	public EditProductResponseDto(Long memberId, String productName,
			Integer productPrice, String productContent,
			Long categoryId, Long categoryId3, List<RentDate> rentDates,
			List<ProductImage> images, char productEndStatus, String region1, String region2) {
		
		this.memberId = memberId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productContent = productContent;
		this.categoryId = categoryId;
		this.categoryId3 = categoryId3;
		this.rentDates = rentDates;
		this.images = images;
		this.productEndStatus = productEndStatus;
		this.region1 = region1;
		this.region2 = region2;
	}
	
	
	
	
	

}
