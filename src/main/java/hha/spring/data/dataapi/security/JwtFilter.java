package hha.spring.data.dataapi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import java.util.Optional;

/**
 * Chain of Filter to set Token
 * This filter is triggered from the WebSecurityConfiguration
 */
@Component
public class JwtFilter extends GenericFilterBean {

    /**
     * The constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);
    /**
     * The constant BEARER.
     */
    public static final String BEARER = "Bearer";

    private JwtService userDetailsService;

    /**
     * Instantiates a new Jwt filter.
     *
     * @param userDetailsService the user details service
     */
    public JwtFilter(JwtService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("Process request to check for a JSON Web Token ");
        String header = ((HttpServletRequest)servletRequest).getHeader("Authorization");

        getBearerToken(header).ifPresent(token-> {
            userDetailsService.loadUserByJwtToken(token).ifPresent(userDetails-> {
                SecurityContextHolder.getContext().setAuthentication(
                        new PreAuthenticatedAuthenticationToken(
                                userDetails, "", userDetails.getAuthorities()));
            });
        });

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Optional<String> getBearerToken(String header) {
        if(header != null && header.startsWith(BEARER)) {
            return Optional.of(header.replace(BEARER, "").trim());
        }

        return Optional.empty();
    }
}
