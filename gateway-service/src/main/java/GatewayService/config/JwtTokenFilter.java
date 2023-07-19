package GatewayService.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import GatewayService.model.Roles;
import GatewayService.model.User;
import GatewayService.model.enums.ERoles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final String SECRET_KEY = "enterprise";

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (!hasAuthorizationBearer(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = getAccessToken(servletRequest);

        setAuthenticationContext(token, servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }


    //header if token if it return false, token is not valid
    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
        return true;
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken
        authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            return false;
        }
    }

    private UserDetails getUserDetails(String token) {

        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();

        LinkedHashMap < ? , ? > userMap = (LinkedHashMap < ? , ? > ) claims.get("user");

        Long id = ((Integer) userMap.get("id")).longValue();
        String username = (String) userMap.get("username");
        String email = (String) userMap.get("email");

        List < LinkedHashMap < String, String >> rolesMap = (List < LinkedHashMap < String, String >> ) userMap.get("roles");
        List < String > roles = rolesMap.stream()
            .map(roleMap -> roleMap.get("role"))
            .collect(Collectors.toList());

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        
        List<Roles> userRoles = new ArrayList<>();

        for (String roleName : roles) {
            Roles role = new Roles();
            ERoles roleEnum = Arrays.stream(ERoles.values())
            .filter(enumValue -> enumValue.name().equals(roleName))
            .findFirst()
            .orElse(null);
            role.setRole(roleEnum);
            userRoles.add(role);
        }

        user.setRoles(userRoles);

        return user;
    }
}
