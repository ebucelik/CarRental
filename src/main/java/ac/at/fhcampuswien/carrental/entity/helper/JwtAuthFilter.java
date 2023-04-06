package ac.at.fhcampuswien.carrental.entity.helper;

import ac.at.fhcampuswien.carrental.entity.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String eMail;
        String url = request.getRequestURI();

        if(!url.contains("/api/v1/cars") || !url.contains("/api/v1/users") || !url.contains("/swagger-ui") ) {
            if (authHeader != null && authHeader.startsWith("Bearer " )) {
                token = authHeader.substring(7);
                eMail = jwtService.extractUserEmail(token);
                System.out.println(eMail);
            }
        }
        filterChain.doFilter(request, response);
    }
}

