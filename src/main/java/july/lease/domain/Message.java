package july.lease.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
	private Long messageId;
	private Long myId;
	private Long yourId;
	private Long messageContentId;
	private char messageReadStatus;
	private char messageDeleteStatus;
	private Long orderId;
	private String messageCreateDate;
	private Long productId;
	private Long roomNo;

	private String sender;
	private String reciver;
	private String messageText;
	
	public Message() {
		
	}

	public Message(Long messageId, Long myId, Long yourId, Long messageContentId, char messageReadStatus,
			char messageDeleteStatus, Long orderId, String messageCreateDate, Long productId, Long roomNo,
			String sender, String reciver, String messageText) {
		super();
		this.messageId = messageId;
		this.myId = myId;
		this.yourId = yourId;
		this.messageContentId = messageContentId;
		this.messageReadStatus = messageReadStatus;
		this.messageDeleteStatus = messageDeleteStatus;
		this.orderId = orderId;
		this.messageCreateDate = messageCreateDate;
		this.productId = productId;
		this.roomNo = roomNo;
		
		this.sender = sender;
		this.reciver = reciver;
		this.messageText = messageText;
	}

	public Message(Long myId, Long productId, Long roomNo, String messageText) {
		super();
		this.myId = myId;
		this.productId = productId;
		this.roomNo = roomNo;
		this.messageText = messageText;
	}

	public Message(Long myId, Long yourId, Long productId, Long roomNo, String messageText) {
		super();
		this.myId = myId;
		this.yourId = yourId;
		this.productId = productId;
		this.roomNo = roomNo;
		this.messageText = messageText;
	}
}
