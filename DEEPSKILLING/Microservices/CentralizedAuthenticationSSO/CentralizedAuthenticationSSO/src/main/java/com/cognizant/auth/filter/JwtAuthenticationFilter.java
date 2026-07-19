package com.cognizant.auth.filter;

import com.cognizant.auth.config.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Servlet filter that authenticates requests bearing a self-issued JWT (Exercise 3).
 *
 * <p>Reads the {@code Authorization: Bearer <token>} header, validates the token via
 * {@link JwtTokenProvider}, and - if valid - populates the
 * {@link org.springframework.security.core.context.SecurityContext} so downstream
 * authorization checks (declared in {@code SecurityConfig}) can succeed. This filter is
 * registered ahead of
 * {@link org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter}
 * for the {@code /api/**} filter chain.</p>
 *
 * <p>Replaces the legacy {@code JwtTokenFilter} shown in the original exercise material,
 * which is renamed here to {@code JwtAuthenticationFilter} to match the required project
 * structure.</p>
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Creates the filter.
     *
     * @param jwtTokenProvider the component used to validate tokens and build an
     *                         {@link Authentication} from them
     */
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Extracts and validates a Bearer token from the current request, authenticating the
     * request in the {@code SecurityContext} when the token is valid.
     *
     * @param request     the incoming HTTP request
     * @param response    the outgoing HTTP response
     * @param filterChain the remaining filter chain to invoke
     * @throws ServletException if a servlet-level error occurs
     * @throws IOException      if an I/O error occurs while processing the request
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Reads the raw Bearer token from the {@code Authorization} header, if present.
     *
     * @param request the incoming HTTP request
     * @return the token string, or {@code null} if no Bearer token was supplied
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}
