package web.config;

import web.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user", user);
        if (user.getAuthorities()
                .stream()
                .anyMatch(role -> "ADMIN".equals(role.getAuthority())
                )) {
            httpServletResponse.sendRedirect("/");
        } else if (user.getAuthorities()
                .stream()
                .anyMatch(role -> "USER".equals(role.getAuthority())
                )) {
            httpServletResponse.sendRedirect("/users/read");
        }
    }
}
