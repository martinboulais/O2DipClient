package o2.dip.processors;

/**
 * Exception when a dip subscription processor is used to handle an invalid subscription
 */
public class InvalidSubscriptionException extends Exception {
    public InvalidSubscriptionException(String exception) {
        super(exception);
    }
}
