package july.lease.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import july.lease.domain.Member;

@Service
public interface MemberService {
	
	public Member login(Member member);
	public Member selectOne(long memberId);
	public int insert(Member member);
	public int idCheck(Member member);
	public int phoneCheck(Member member);
	public Member findbyEmail(Member member);
	public int searchPw(Member member);
	public int nickNameCheck(Member member);
	public Member naverLogin(HttpServletRequest request);
	public int ninsert(Member member);
	public Member findbyEmailCheck(Member member);
	public String getAccessToken (String authorize_code);
	public Member getUserInfo (String kaccess_Token);
	public int kinsert(Member member);
	public Member socialLogin(Member member);
	public Member searchPwCheck(Member member);

}
