package july.lease.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	
	private Long memberId;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private String memberNickname;
	private String memberPhone;
	private String memberCreateDate;
	private char memberDeleteStatus;
	private String socialLogin;
	private String redirectURL;
	
	public Member() {
	}
	
	public Member(String memberEmail, String memberPassword, String memberName, String memberNickname,
			String memberPhone, String socialLogin) {
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberNickname = memberNickname;
		this.memberPhone = memberPhone;
		this.socialLogin = socialLogin;
	}
	
	
	
	
}
