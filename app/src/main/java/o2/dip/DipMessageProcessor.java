package o2.dip;

import cern.dip.DipData;
import cern.dip.DipSubscription;
import o2.dip.processors.InvalidSubscriptionException;

public interface DipMessageProcessor {
    /**
     * Process the next message
     *
     * @param subscription dip subscription on which message has been received
     * @param message the message to process
     */
    void process(DipSubscription subscription, DipData message) throws InvalidSubscriptionException;
}
