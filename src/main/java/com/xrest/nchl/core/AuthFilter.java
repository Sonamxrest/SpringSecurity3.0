package com.xrest.nchl.core;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xrest.nchl.enums.ExceptionTypes;
import com.xrest.nchl.model.Customer;
import com.xrest.nchl.service.CustomerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

public class AuthFilter extends GenericFilterBean {


    private final CustomerService customerService;


    public AuthFilter(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String jwt = this.resolveToken(httpServletRequest);
            if (StringUtils.hasText(jwt)) {
                if (JWTUtils.validateToken(jwt)) {
                    String username = JWTUtils.decode(jwt);
                    UserDetails customer =  customerService.loadUserByUsername(username);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(customer, customer.getPassword(), customer.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new TokenExpiredException(ExceptionTypes.TOKEN_EXPIRED.label);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
            resetAuthenticationAfterRequest();
        } catch (Exception eje) {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            if (eje.getMessage().equals(ExceptionTypes.TOKEN_EXPIRED.label)) {
                servletResponse.getWriter().write(ExceptionTypes.TOKEN_EXPIRED.label);
                servletResponse.getWriter().flush();
            }
            LOGGER.debug("Exception " + eje.getMessage(), eje);
        }
    }

    private void resetAuthenticationAfterRequest() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(JWTUtils.headerType);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWTUtils.tokenType)) {
            String jwt = bearerToken.substring(7);
            return jwt;
        }
        return null;
    }
}

//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        try {
//            if (!ObjectUtils.isEmpty(request.getHeader(JWTUtils.headerType))) {
//
//                String header = request.getHeader(JWTUtils.headerType);
//                if (header.contains(JWTUtils.tokenType)) {
//                    String token = header.substring(7);
//                    String username = JWTUtils.decode(token);
//                    Customer customer = (Customer) customerService.loadUserByUsername(username);
//                    UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(customer.getUsername(), null, customer.getAuthorities());
//                    SecurityContextHolder.getContext().setAuthentication(token1);
//                }
//
//            }
//            filterChain.doFilter(request, response);
//
//        } catch (Exception ex) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Unauthorized Access");
//        }
//    }
