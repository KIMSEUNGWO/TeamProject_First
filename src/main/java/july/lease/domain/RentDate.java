package july.lease.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RentDate {
		
	private Long rentDateId;
	private Long productId;
	private String rentAbleStartDate;
	private String rentAbleEndDate;
	
	public RentDate() {
	}

	public RentDate(Long productId, String rentAbleStartDate, String rentAbleEndDate) {
		this.productId = productId;
		this.rentAbleStartDate = rentAbleStartDate;
		this.rentAbleEndDate = rentAbleEndDate;
	}
	
	
	
	
}
