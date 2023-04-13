package com.kiubit.webapi.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiubit.webapi.auth.models.AuthenticationResponse;
import com.kiubit.webapi.models.CommonResponse;
import com.kiubit.webapi.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            if (!request.getRequestURI().startsWith("/kiubit/api/swagger") && !request.getRequestURI().startsWith("/kiubit/api/auth/authenticate")&&!request.getRequestURI().startsWith("/kiubit/api/v3/api-docs")&&!request.getRequestURI().startsWith("/kiubit/api/public")){
                CommonResponse<AuthenticationResponse> respuesta = new CommonResponse<AuthenticationResponse>(new AuthenticationResponse("Must provide a valid token to continue"), false, "Authentication Failed");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                mapper.writeValue(response.getWriter(), respuesta);
            }
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        System.out.println(jwt);
        try{
            userEmail = jwtService.extractUsername(jwt);//todo extract the userEmail from JWT token
        }catch (Exception e){
            CommonResponse<AuthenticationResponse> respuesta = new CommonResponse<AuthenticationResponse>(new AuthenticationResponse("Invalid Token"), false, "Authentication Failed");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), respuesta);
            filterChain.doFilter(request,response);
            return;
        }

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
