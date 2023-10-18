package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RentAbleDateDto {
	
	private String rentAbleStartDate;
	private String rentAbleEndDate;
	
	public RentAbleDateDto() {
	}
	
	public RentAbleDateDto(String rentAbleStartDate, String rentAbleEndDate) {
		this.rentAbleStartDate = rentAbleStartDate;
		this.rentAbleEndDate = rentAbleEndDate;
	}
	
	

}
