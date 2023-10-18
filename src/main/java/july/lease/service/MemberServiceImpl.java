package july.lease.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import july.lease.dao.member.MemberDao;
import july.lease.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberDao memberDao;

	private final BCryptPasswordEncoder encoder;
	
	private final ApiExamMemberProfile apiExam;
	
	@Override
	public Member login(Member paramMember) {
		
		Member member = memberDao.login(paramMember);
		
		if(member != null) {
			
			boolean res = encoder.matches(paramMember.getMemberPassword(), member.getMemberPassword());
			
			if(res) {
				
				return member;
			}
			
		}
		
		return null;
	}

	@Override
	public Member selectOne(long memberId) {
		
		return memberDao.selectOne(memberId);
	}

	@Override
	public int insert(Member member) {
		
		member.setMemberPassword(encoder.encode(member.getMemberPassword()));
		
		String getMemberPhone = member.getMemberPhone().replace("-", "");
		
		member.setMemberPhone(getMemberPhone);
		
		return memberDao.insert(member);
	}

	@Override
	public int idCheck(Member member) {
		
		return memberDao.idCheck(member);
	}

	@Override
	public int phoneCheck(Member member) {
		
		String getMemberPhone = member.getMemberPhone().replace("-", "");
		
		member.setMemberPhone(getMemberPhone);
		
		return memberDao.phoneCheck(member);
	}

	@Override
	public Member findbyEmail(Member member) {
	
		String getMemberPhone = member.getMemberPhone().replace("-", "");
		
		member.setMemberPhone(getMemberPhone);
		
		return memberDao.findbyEmail(member);
	}

	@Override
	public int searchPw(Member member) {
		
		member.setMemberPassword(encoder.encode(member.getMemberPassword()));
		
		String getMemberPhone = member.getMemberPhone().replace("-", "");
		
		member.setMemberPhone(getMemberPhone);
		
		return memberDao.searchPw(member);
	}
	
	public Member searchPwCheck(Member member) {
		String getMemberPhone = member.getMemberPhone().replace("-", "");
		
		member.setMemberPhone(getMemberPhone);
		
		return memberDao.searchPwCheck(member);
	}

	@Override
	public int nickNameCheck(Member member) {
		
		return memberDao.nickNameCheck(member);
	}
	
	@Override
	public Member naverLogin(HttpServletRequest request) {
		try {
			// callback 처리 -> access_token
			Map<String, String> callbackRes = callback(request);
			
			String access_token = callbackRes.get("access_token");
			
			// access_token -> 사용자 정보 조회
			Map<String, Object> responseBody = apiExam.getMemberProfile(access_token);
			
			Map<String, String> response = (Map<String, String>) responseBody.get("response");
			String phone = response.get("mobile").replace("-", "");
			String name = response.get("name");
			Member memberPhone = new Member();
			
			memberPhone.setMemberName(name);
			memberPhone.setMemberPhone(phone);
			
			Member naverMember = new Member();
			
			if(memberDao.phoneCheck(memberPhone)==0) {
				naverMember.setMemberEmail(response.get("email"));
				naverMember.setMemberNickname(response.get("nickname"));
				naverMember.setMemberName(response.get("name"));
				naverMember.setMemberPhone(phone);		
				
				memberDao.ninsert(naverMember);
				
				
				return memberDao.socialLogin(naverMember);
			} else {
				naverMember = memberDao.findbyEmail(memberPhone);

				if(naverMember.getSocialLogin()!=null && naverMember.getSocialLogin().equalsIgnoreCase("Naver")) {
					return memberDao.socialLogin(naverMember);
				} else if(naverMember.getSocialLogin()==null || !naverMember.getSocialLogin().equalsIgnoreCase("Naver")){
					return null;
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<String, String> callback(HttpServletRequest request) throws Exception{
		String clientId = "WPbsOJi72I4GB5iqARDX";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "pPL2LGrfmL";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    try {
		    String redirectURI = URLEncoder.encode("http://localhost:8080/naver", "UTF-8");
		    String apiURL;
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		    String access_token = "";
		    String refresh_token = "";
	    
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      if(responseCode==200) {
		        Map<String, String> map = new HashMap<String, String>();
		        ObjectMapper objectMapper = new ObjectMapper();
		        map = objectMapper.readValue(res.toString(), Map.class);
		        return map;
		      } else {
		    	  throw new Exception("callback 반환코드 : "+ responseCode);
		      }
		    } catch (Exception e) {
		      System.out.println(e);
		      throw new Exception("callback 처리중 예외 발생");
		    }
	}

	@Override
	public int ninsert(Member member) {
		
		return memberDao.ninsert(member);
	}

	@Override
	public Member findbyEmailCheck(Member member) {
		
		String getMemberPhone = member.getMemberPhone().replace("-", "");
		
		member.setMemberPhone(getMemberPhone);
		
		return memberDao.findbyEmailCheck(member);
	}
	
	public String getAccessToken (String authorize_code) {
        String kaccess_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=124198d768a446e334a4d562c4c29d2e");
            sb.append("&redirect_uri=http://localhost:8080/kakao");
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();
            
            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
 
            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            
            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            kaccess_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return kaccess_Token;
    }
	
	public Member getUserInfo (String kaccess_Token) {
	    
	    //요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
	    HashMap<String, Object> userInfo = new HashMap<>();
	    String reqURL = "https://kapi.kakao.com/v2/user/me";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        
	        //    요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + kaccess_Token);
	        
	        int responseCode = conn.getResponseCode();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        
	        JsonParser parser = new JsonParser();
	        JsonElement element = parser.parse(result);
	        
	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	        
	        String phone = kakao_account.getAsJsonObject().get("phone_number").getAsString();
	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	        String name = kakao_account.getAsJsonObject().get("name").getAsString();
	        String email = kakao_account.getAsJsonObject().get("email").getAsString();

	        userInfo.put("email",email);
	        userInfo.put("name",name);
	        userInfo.put("phone",phone);
	        userInfo.put("nickname", nickname);
	        
	        String phoneNumber = phone.replace("+82 ", "0").replace("-", "");
	        
	        Member memberPhone = new Member();
	        
	        memberPhone.setMemberName(name);
	        memberPhone.setMemberPhone(phoneNumber);
			
	        Member kakaoMember = new Member();
	        
			if(memberDao.phoneCheck(memberPhone)==0) {
				kakaoMember.setMemberEmail(email);
				kakaoMember.setMemberNickname(nickname);
				kakaoMember.setMemberName(name);
				kakaoMember.setMemberPhone(phoneNumber);		
				
				memberDao.kinsert(kakaoMember);
				
				
				
				return memberDao.socialLogin(kakaoMember);
			} else {
				kakaoMember = memberDao.findbyEmail(memberPhone);

				if(kakaoMember.getSocialLogin()!=null && kakaoMember.getSocialLogin().equalsIgnoreCase("Kakao")) {
					
					return memberDao.socialLogin(kakaoMember);
				} else if(kakaoMember.getSocialLogin()==null || !kakaoMember.getSocialLogin().equalsIgnoreCase("Kakao")) {
					return null;
				}
			}
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	@Override
	public int kinsert(Member member) {

		return memberDao.ninsert(member);
	}

	@Override
	public Member socialLogin(Member paramMember) {
		Member member = memberDao.socialLogin(paramMember);
		return member;
	}
	
}