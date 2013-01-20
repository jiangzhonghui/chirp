// InvalidOperationException.java, created on Jan 20, 2013
package eu.fabiostrozzi.chirp.service;

/**
 * Describes any attempt to do invalid operations such as following oneself.
 * 
 * @author fabio
 */
public class InvalidOperationException extends RuntimeException {
    private static final long serialVersionUID = 3562290360885372281L;

    public InvalidOperationException() {}

    /**
     * @param message
     */
    public InvalidOperationException(String message) {
        super(message);
    }
}
