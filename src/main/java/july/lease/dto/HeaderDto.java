package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HeaderDto {
	
	private Long categoryId;
	private Long categoryId2;
	private String categoryName;
	
	public HeaderDto() {
		
	}
	
	public HeaderDto(Long categoryId, Long categoryId2, String categoryName) {
		this.categoryId = categoryId;
		this.categoryId2 = categoryId2;
		this.categoryName = categoryName;
	}

}
