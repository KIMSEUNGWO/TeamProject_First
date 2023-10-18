package july.lease.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import july.lease.domain.Member;
import july.lease.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController extends CommonRestController {

	private final MemberService memberService;
	
	@GetMapping("/login/kakao")
	public void kakaoLogin() {
		
	}
	
	@GetMapping("/kakao")
	 public String login(@RequestParam("code") String code, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        String kaccess_Token = memberService.getAccessToken(code);
        Member kakao = memberService.getUserInfo(kaccess_Token);
        String state = request.getParameter("state");
        
        if(kakao==null) {
        	try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out;
				
				response.setContentType("text/html; charset=utf-8");
				PrintWriter w = response.getWriter();
				w.write("<script>alert('이미 가입한 정보가 있습니다');");
				w.write("history.go(-1)");
				w.write("</script>");
				w.flush();
				w.close();
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
		} 
		
        session.setAttribute("memberId", kakao.getMemberId());	
     
        return "redirect:" + state;
    }
	
	@GetMapping("/login/naver")
	public void naverLogin(){
	}
	
	
	@GetMapping("/naver")
	public String naverLogin_callback(HttpServletRequest request, Member member, HttpSession session, HttpServletResponse response) {
		String state = request.getParameter("state");
		Member nmember = memberService.naverLogin(request);

		if(nmember==null) {
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out;
				
				response.setContentType("text/html; charset=utf-8");
				PrintWriter w = response.getWriter();
				w.write("<script>alert('이미 가입한 정보가 있습니다');");
				w.write("history.go(-1)");
				w.write("</script>");
				w.flush();
				w.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		session.setAttribute("memberId",nmember.getMemberId());
		if(!state.equalsIgnoreCase(null)) {
			return "redirect:" + state;			
		}else {
			return "redirect:" + state;
		}
	}
	
	@GetMapping("/login")
	public String login(HttpSession session, @RequestParam(defaultValue = "/") String redirectURL) {
		if(session.getAttribute("memberId")!=null) {
			return "redirect:/";
		}
		return "/Project_login";
	}
	
	@PostMapping("/login")
	public @ResponseBody Map<String, Object> loginAction(@RequestBody Member member, HttpSession session, HttpServletRequest request){
		String redirectURL = member.getRedirectURL();
		
		
		member = memberService.login(member);
		
		if(!redirectURL.equals("") && member!=null) {
			
			session.setAttribute("memberId", member.getMemberId());

			int index = redirectURL.indexOf("/members/");
			if (index != -1) {
				redirectURL = new StringBuilder(redirectURL).insert("/members/".length(), member.getMemberId()).toString();
			}
			
			Map<String, Object> map = responseMap(REST_SUCCESS, "");
			
			map.put("url", redirectURL);
			
			return map;
			
		} else if(redirectURL.equals("") && member!=null) {
			session.setAttribute("memberId", member.getMemberId());

			Map<String, Object> map = responseMap(REST_SUCCESS, "");
			
			map.put("url", "/");
			
			return map;
		} else {
			return responseMap(REST_FAIL, "");
		}
		
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    return "redirect:/";
	}
	
	
	@GetMapping("/members/add")
	public String register(HttpSession session) {
		if(session.getAttribute("memberId")!=null) {
			return "redirect:/";
		}
		return "/Project_register";
	}
	
	@PostMapping("/members/add")
	public @ResponseBody Map<String, Object> registerAction(@RequestBody Member member){
		
		try {
			int res = memberService.insert(member);
			
			return responseWriteMap(res);
		
		} catch(Exception e) {
			
			e.printStackTrace();
			return responseMap(REST_FAIL, "");
		}
		
		
	}
	
	@PostMapping("/idCheck")
	public @ResponseBody Map<String, Object> idCheck(@RequestBody Member member){
		
		int res = memberService.idCheck(member);
		
		if(res==0) {
			return responseMap(REST_SUCCESS, "");
		} else {
			return responseMap(REST_FAIL, "");
		}
	}
	
	@PostMapping("/phoneCheck")
	public @ResponseBody Map<String, Object> phoneCheck(@RequestBody Member member){
		
		int res = memberService.phoneCheck(member);
		
		if(res==0) {
			return responseMap(REST_SUCCESS, "");
		} else {
			return responseMap(REST_FAIL, "");
		}
	}
	
	@PostMapping("/findbyEmailCheck")
	public @ResponseBody Map<String, Object> findbyEmailCheck(@RequestBody Member member){
		
		member = memberService.findbyEmailCheck(member);
		
		if(member!=null) {
			return responseMap(REST_SUCCESS, "");
		} else {
			return responseMap(REST_FAIL, "");
		}
	}
	
	@PostMapping("/searchPwCheck")
	public @ResponseBody Map<String, Object> searchPwCheck(@RequestBody Member member){
		
		member = memberService.searchPwCheck(member);
		
		if(member!=null) {
			return responseMap(REST_SUCCESS, "");
		} else {
			return responseMap(REST_FAIL, "");
		}
	}
	
	
	@GetMapping("/findbyEmail")
	public String findbyEmail() {
		return "/Project_findbyEmail";
	}
	
	@GetMapping("/findbyEmailRes")
	public String findbyEmailResult() {
		return "/Project_findbyEmailRes";
	}
	
	@PostMapping("/findbyEmailAction")
	public @ResponseBody Map<String, Object> findbyEmailAction(@RequestBody Member member, HttpSession session){
		
		member = memberService.findbyEmail(member);
		if(member!=null) {
			session.setAttribute("memberEmail", member.getMemberEmail());
			session.setAttribute("socialLogin", member.getSocialLogin());
			session.setAttribute("memberName", member.getMemberName());
			
			Map<String, Object> map = responseMap(REST_SUCCESS, "");
			
			map.put("url", "/findbyEmailRes");
			
			return map;
			
		} else {
			return responseMap(REST_FAIL, "");
		}
		
	}
	
	@GetMapping("/searchPw")
	public String searchPw() {
		return "/Project_searchPw";
	}

	@PostMapping("/searchPwAction")
	public @ResponseBody Map<String, Object> searchPwAction(@RequestBody Member member){
		
		try {
			int res = memberService.searchPw(member);
			
			return responseWriteMap(res);
		
		} catch(Exception e) {
			
			e.printStackTrace();
			return responseMap(REST_FAIL, "");
		}
		
	}
	
	@PostMapping("/nickNameCheck")
	public @ResponseBody Map<String, Object> nickNameCheck(@RequestBody Member member){
		
		int res = memberService.nickNameCheck(member);
		
		if(res==0) {
			return responseMap(REST_SUCCESS, "");
		} else {
			return responseMap(REST_FAIL, "");
		}
	}

}