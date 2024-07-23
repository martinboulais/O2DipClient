package o2.dip.processors;

import cern.dip.BadParameter;
import cern.dip.DipData;
import cern.dip.DipSubscription;
import cern.dip.TypeMismatch;
import o2.dip.DipMessageProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract class SingleTopicProcessor implements DipMessageProcessor {
    protected static final Logger logger = LogManager.getLogger("dip-processor");

    @Override
    public final void process(DipSubscription subscription, DipData message) throws InvalidSubscriptionException {
        if (!subscription.getTopicName().equals(getHandledTopic())) {
            throw new InvalidSubscriptionException(String.format(
                "Invalid processor %s to handle %s",
                this.getClass().getCanonicalName(),
                subscription.getTopicName()
            ));
        }

        try {
            this.process(message);
        } catch (Exception e) {
            logger.error("An error occurred when processing message from {}: {}", getHandledTopic(), e.getMessage());
        }
    }

    /**
     * Process a received message
     *
     * @param message the message to handle
     */
    protected abstract void process(DipData message) throws BadParameter, TypeMismatch;

    /**
     * Returns the topic handled by the processor
     *
     * @return the topic name
     */
    protected abstract String getHandledTopic();
}
