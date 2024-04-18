package com.alejo.svautenticacion.infraestructure.drivenadapters.security.jwt.filter;

import com.alejo.svautenticacion.infraestructure.drivenadapters.security.jwt.service.JwtService;
import com.alejo.svautenticacion.infraestructure.drivenadapters.security.util.Utilities;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        final String username;

        if (isTokenNull(token))
        {
            filterChain.doFilter(request, response);
            return;
        }

        username=jwtService.getUsernameFromToken(token);

        if (validateAuthenticatedUser(username))
        {
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, userDetails))
            {
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        filterChain.doFilter(request, response);
    }

    private boolean validateAuthenticatedUser(String username) {
        return username!=null && SecurityContextHolder.getContext().getAuthentication()==null;
    }

    private boolean isTokenNull(String token) {
        return token == null;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authHeader) && authHeader.startsWith(Utilities.TOKEN_STARTS_WITH))
        {
            return authHeader.substring(Utilities.INITIAL_POSITION_OF_THE_TOKEN);
        }
        return null;
    }




}