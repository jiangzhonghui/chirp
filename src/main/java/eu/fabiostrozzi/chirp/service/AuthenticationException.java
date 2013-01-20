// AuthenticationException.java, created on Jan 20, 2013
package eu.fabiostrozzi.chirp.service;

/**
 * Any exception related to authentication issues.
 * 
 * @author fabio
 */
public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = 1870337983326711292L;

    public AuthenticationException(String message) {
        super(message);
    }

}
