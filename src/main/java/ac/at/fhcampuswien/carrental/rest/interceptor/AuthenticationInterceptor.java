package ac.at.fhcampuswien.carrental.rest.interceptor;

import ac.at.fhcampuswien.carrental.entity.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    JwtService jwtService = new JwtService();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Adding .excludePathPatterns in AppConfig didn't work therefore this workaround.
        if (request.getRequestURL().toString().contains("users")) {
            return true;
        }

        String accessToken = request.getHeader("Auth");

        jwtService.isTokenExpired(accessToken);

        return true;
    }
}
