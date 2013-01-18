// Response.java, created on Jan 17, 2013
package eu.fabiostrozzi.chirp.model;

import static eu.fabiostrozzi.chirp.model.Response.ErrorCode.INTERNAL_ERROR;
import static eu.fabiostrozzi.chirp.model.Response.ErrorCode.OK;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Generic response with code information if any occurs.
 * 
 * @author fabio
 */
@XmlRootElement(name = "response")
public class Response<T> {
    /**
     * Possible error codes.
     * 
     * @author fabio
     */
    public static enum ErrorCode {
        OK(0), INTERNAL_ERROR(1), USER_NOT_FOUND(2);

        private int code;

        private ErrorCode(int code) {
            this.code = code;
        }

        public int code() {
            return code;
        }
    }

    private T result;
    private ErrorCode error;

    /**
     * @return the result
     */
    public T getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * @return the code
     */
    public ErrorCode getError() {
        return error;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setError(ErrorCode error) {
        this.error = error;
    }

    /**
     * Creates a new {@link Response} message carrying the given result.
     * 
     * @param result
     * @return
     */
    public static <S> Response<S> of(S result) {
        Response<S> resp = new Response<S>();
        resp.error = OK;
        resp.result = result;
        return resp;
    }

    /**
     * Creates a new {@link Response} message carrying the given code code.
     * 
     * @param code
     * @return
     */
    public static <S> Response<S> failure(ErrorCode error) {
        Response<S> resp = new Response<S>();
        resp.error = error;
        resp.result = null;
        return resp;
    }

    /**
     * Creates a new {@link Response} message carrying internal code code.
     * 
     * @param code
     * @return
     */
    public static <S> Response<S> unexpected() {
        Response<S> resp = new Response<S>();
        resp.error = INTERNAL_ERROR;
        resp.result = null;
        return resp;
    }
}
