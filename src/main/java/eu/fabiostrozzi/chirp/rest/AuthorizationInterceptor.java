// AuthorizationInterceptor.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.rest;

import static org.springframework.util.StringUtils.hasText;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import eu.fabiostrozzi.chirp.service.ChirpsService;

/**
 * Intercepts calls and checks whether the authorization token is valid or not.
 * <p>
 * In case of unauthorizated access, returns the 401 HTTP error code.
 * 
 * @author fabio
 */
public class AuthorizationInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired
    private ChirpsService service;

    /*
     * (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader(RestController.TOKEN);
            if (!hasText(token)) {
                log.error("Unauthorized attempt to call the Chirp API without token!");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            if (!service.isValidToken(token)) {
                log.error("Unauthorized attempt to call the Chirp API with token '{}'!", token);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            return true;
        } catch (Exception e) {
            log.error("Unexpected exception while authorizing call to '{}'", request.getRequestURI(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
     * org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {}

    /*
     * (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
     * java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {}

}
