package july.lease.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageContent {
	
	private Long messageContentId;
	private String messageText;
	
	public MessageContent() {
	}

	public MessageContent(String messageText) {
		this.messageText = messageText;
	}
	
	

	
}
