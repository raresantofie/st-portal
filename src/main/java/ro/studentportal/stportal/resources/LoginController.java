package ro.studentportal.stportal.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;
import ro.studentportal.stportal.exception.UnauthorizedException;
import ro.studentportal.stportal.model.*;
import ro.studentportal.stportal.resources.dto.SessionDto;
import ro.studentportal.stportal.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    @Qualifier("customUserDetails")
    UserDetailsService userDetailsService;

    @PostMapping
    public SessionDto authorize(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        UserDetails user = userDetailsService.loadUserByUsername(loginDto.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, loginDto.getPassword(), user.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            httpServletResponse.addHeader("Access-Control-Allow-Origin",
                    httpServletRequest.getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            return SessionDto.mapObject((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } else {
            throw new UnauthorizedException("Login failed");
        }
    }

    @GetMapping("/checkstatus")
    public void checkLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
