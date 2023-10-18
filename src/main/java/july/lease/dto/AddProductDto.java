package july.lease.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import july.lease.validation.ValidFiles;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddProductDto {
	
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
	private List<String> rentAbleStartDate;
	@NotNull
	private List<String> rentAbleEndDate;
	@ValidFiles(min=1, max=8)
	private List<MultipartFile> images;
	
	public AddProductDto() {
	}

	public AddProductDto(String productName, Integer productPrice, String productContent, Long categoryId,
			List<String> rentAbleStartDate, List<String> rentAbleEndDate, List<MultipartFile> images) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.productContent = productContent;
		this.categoryId = categoryId;
		this.rentAbleStartDate = rentAbleStartDate;
		this.rentAbleEndDate = rentAbleEndDate;
		this.images = images;
	}
	
	
	
	
	
}
