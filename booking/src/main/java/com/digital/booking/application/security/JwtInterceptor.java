package com.digital.booking.application.security;

import com.digital.booking.application.security.enums.EntityAuthorizationRole;
import com.digital.booking.application.security.models.UserSecurityDetails;
import com.digital.booking.core.port.output.SecurityJwtRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtInterceptor extends OncePerRequestFilter {

    private static final String HEADER_TOKEN_NAME = "Authorization";
    private final SecurityJwtRepository jwtUtil;

    private final SecurityCommonService securityService;

    public JwtInterceptor(SecurityJwtRepository jwtUtil, SecurityCommonService securityService) {
        this.jwtUtil = jwtUtil;
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HEADER_TOKEN_NAME);
        if(token != null && token.startsWith("Bearer")){
            token = token.substring(7);
            String userName = jwtUtil.getUserEmail(token);
            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = securityService.loadUserByUsername(userName);
                UserSecurityDetails securityDetails = UserSecurityDetails.builder()
                                .userName(userDetails.getUsername())
                                .password(userDetails.getPassword())
                                .authorities(userDetails.getAuthorities()
                                        .stream().map(grantedAuthority -> EntityAuthorizationRole.getFromValue(grantedAuthority.getAuthority()))
                                        .collect(Collectors.toSet()))
                                .build();
                if(jwtUtil.isValidToken(token, securityDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
