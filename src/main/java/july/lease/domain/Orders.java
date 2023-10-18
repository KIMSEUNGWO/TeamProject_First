package july.lease.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Orders {

	private Long orderId;
	private Long memberId;
	private Long productId;
	private Long rentDateId;
	private String orderCreateDate;
	private int orderConfirmStatus;
	private String orderRentStartDate;
	private String orderRentEndDate;
	private Integer orderPrice;
	private String orderRequest;
	
	public Orders() {
	}

	public Orders(Long memberId, Long productId, Long rentDateId,
			String orderRentStartDate, String orderRentEndDate,
			Integer orderPrice, String orderRequest) {
		this.memberId = memberId;
		this.productId = productId;
		this.rentDateId = rentDateId;
		this.orderRentStartDate = orderRentStartDate;
		this.orderRentEndDate = orderRentEndDate;
		this.orderPrice = orderPrice;
		this.orderRequest = orderRequest;
	}
	
	public Orders(Long memberId, Long productId, Long rentDateId,
			String orderRentStartDate, String orderRentEndDate,
			Integer orderPrice) {
		this.memberId = memberId;
		this.productId = productId;
		this.rentDateId = rentDateId;
		this.orderRentStartDate = orderRentStartDate;
		this.orderRentEndDate = orderRentEndDate;
		this.orderPrice = orderPrice;
	}
	
	
	
	
}
