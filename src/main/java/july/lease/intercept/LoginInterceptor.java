package july.lease.intercept;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("memberId")== null) {			
			System.out.println(requestURI);
			response.sendRedirect("/login?redirectURL=" + requestURI);
			
			return false;
		}
		
		return true;
		
	}
	
}