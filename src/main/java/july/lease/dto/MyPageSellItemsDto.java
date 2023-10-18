package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyPageSellItemsDto {

	private String itemId;
	private String itemImage;
	private String itemTitle;
	private String itemPrice;
	private Long itemStatus; // 판매중, 대여중, 판매완료 구분
	private String itemTotalCount; // 대여된 건수
	private String itemTotalPrice; // 대여된금액의 총합

}
