package july.lease.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import july.lease.intercept.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	LoginInterceptor loginInterceptor;
	
	private static final String[] EXCLUDE_PATHS = {
			"/", "/login", "/logout", "/resources/**", "/members/add", "/findbyEmail", "/findbyEmailRes",
			"/searchPw" ,"/idCheck", "/phoneCheck", "/findbyEmailAction", "/searchPwAction",
			"/nickNameCheck", "/check/**", "/searchPwCheck", "/findbyEmailCheck", "/error",
			"/naver", "/kakao", "/products/*/getImage"
	};
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
			.excludePathPatterns(EXCLUDE_PATHS)
			.addPathPatterns("/products/add", "/products/*/*", "/products/*/*/*");
	}
}
