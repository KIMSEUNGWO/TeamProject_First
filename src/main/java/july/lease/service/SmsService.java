package july.lease.service;

import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class SmsService {
	
	public static String PhoneNumberCheck(String to) throws CoolsmsException {
			
			String api_key = "NCSOOIIA3GBBPHYM";
			String api_secret = "IVTBRAJKJAGL4TNR8VTUFFFGAECHP4UI";
			Message coolsms = new Message(api_key, api_secret);
			
			Random rand  = new Random();
	    String numStr = "";
	    for(int i=0; i<4; i++) {
	       String ran = Integer.toString(rand.nextInt(10));
	       numStr+=ran;
	    }          
	    to = to.replaceAll("-", "");
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", to);    // 수신전화번호(ajax로 view 화면에서 받아온 값으로 넘김)
	    params.put("from", "01035674431");    // 발신전화번호, 테스트시에는 발신, 수신 둘다 본인 번호로 하면 됨
	    params.put("type", "sms"); 
	    params.put("text", "인증번호는 [" + numStr + "] 입니다.");
	
	    coolsms.send(params); // 메시지 전송
	        
	    return numStr;
	
	}
	
	public static String TemporaryPassword(String to) throws CoolsmsException {
		
		String api_key = "NCSOOIIA3GBBPHYM";
		String api_secret = "IVTBRAJKJAGL4TNR8VTUFFFGAECHP4UI";
		Message coolsms = new Message(api_key, api_secret);
			
		Random rand  = new Random();
	    
	    Character[] q = IntStream.of(rand.ints(97,121).distinct().limit(3).toArray()).mapToObj(a -> (char)a).toArray(Character[]::new);
	    Character[] w = IntStream.of(rand.ints(65,90).distinct().limit(2).toArray()).mapToObj(a -> (char)a).toArray(Character[]::new);
	    Character[] e = IntStream.of(rand.ints(48,57).distinct().limit(4).toArray()).mapToObj(a -> (char)a).toArray(Character[]::new);
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append((char)q[0])
	    .append((char)e[0])
	    .append((char)w[0])
	    .append((char)q[1])
	    .append((char)q[2])
	    .append((char)e[1])
	    .append((char)w[1])
	    .append((char)e[2])
	    .append((char)e[3]);
	    String randompassword = sb.toString();
	    
	    to = to.replaceAll("-", "");
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", to);    // 수신전화번호 (ajax로 view 화면에서 받아온 값으로 넘김)
	    params.put("from", "01035674431");    // 발신전화번호, 테스트시에는 발신, 수신 둘다 본인 번호로 하면 됨
	    params.put("type", "sms"); 
	    params.put("text", "임시 비밀번호는 [" + randompassword + "] 입니다. 반드시 비밀번호를 변경하시기 바랍니다.");
	
	    coolsms.send(params); // 메시지 전송
	        
	    return randompassword;

}
}

