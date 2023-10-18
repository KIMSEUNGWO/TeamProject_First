package july.lease.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyAllMessageListDto {
	
	private Long roomNo;
	private Long productId;
	private String messageCreateDate;
	private char messageReadStatus;
	private String messageText;
	private String sender;
	private Long myId;
	private String reciver;
	private Long yourId;
	private String productName;
	private String storeImageName;
	private int noReadMcnt;
	

	public MyAllMessageListDto() {
		
	}


	public MyAllMessageListDto(Long roomNo, Long productId, String messageCreateDate, char messageReadStatus,
			String messageText, String sender, Long myId, String reciver, Long yourId, String productName,
			String storeImageName, int noReadMcnt) {
		super();
		this.roomNo = roomNo;
		this.productId = productId;
		this.messageCreateDate = messageCreateDate;
		this.messageReadStatus = messageReadStatus;
		this.messageText = messageText;
		this.sender = sender;
		this.myId = myId;
		this.reciver = reciver;
		this.yourId = yourId;
		this.productName = productName;
		this.storeImageName = storeImageName;
		this.noReadMcnt = noReadMcnt;
	}
	
}
