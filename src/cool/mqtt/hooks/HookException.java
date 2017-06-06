package cool.mqtt.hooks;

/**
 * Exception class used by the Hook to signal an error condition.
 */
public class HookException extends Exception {

    private static final long serialVersionUID = 79558317492415306L;

    /** The error code */
    private int code;

    /**
     * Constructs an {@code HookException} with the specified error code and
     * detail message.
     * 
     * @param code
     *            the error code that can be used to distinguish the kind of
     *            problem. It must be a non negative integer, otherwise it will
     *            be replaced by {@code 0}
     * @param message
     *            the detail message to be forwarded to the Client. It can be
     *            null. The message should be in simple ASCII, otherwise it
     *            might be altered in order to be sent to the client; multiline
     *            text is also not allowed
     */
    public HookException(int code, String message) {
        super(message);
        if (code < 0) {
            code = 0;
        }
        this.code = code;
    }

    /**
     * @return the error code
     */
    public int getCode() {
        return code;
    }
}
