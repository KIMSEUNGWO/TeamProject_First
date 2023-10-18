package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RentAbleRequestDto {
	
	private String rentAbleStartDate;
	private String rentAbleEndDate;
	
	public RentAbleRequestDto() {
	}

	public RentAbleRequestDto(String rentAbleStartDate, String rentAbleEndDate) {
		this.rentAbleStartDate = rentAbleStartDate;
		this.rentAbleEndDate = rentAbleEndDate;
	}
	
	
	

}
