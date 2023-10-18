package july.lease.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import july.lease.intercept.MyPageInterceptor;


@Configuration
public class InteceptorWebConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// /members/ 이후 주소에 대한 인터셉터 (세션ID와 요청memberID 비교)
		registry.addInterceptor(new MyPageInterceptor())
				.order(1)
				.addPathPatterns("/members/**") // /members/ 하위 전 myPageInterceptor 적용
				.excludePathPatterns("/resources/js/**", "/resources/css/**", "/*.ico", "/error", "/members/add"); // 예외 URI
				// members/add = 회원가입
	}

}
