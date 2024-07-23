package o2.dip;

import cern.dip.*;
import o2.dip.processors.InvalidSubscriptionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DipClient {
    private static final Logger logger = LogManager.getLogger(DipClient.class);
    DipFactory dipFactory = Dip.create("LocalServer");

    BlockingQueue<DipData> processingQueue = new LinkedBlockingQueue<>();

    DipClient(Map<String,DipMessageProcessor> subscriptionItems) {
        dipFactory.setDNSNode("dipnsgpn1.cern.ch,dipnsgpn2.cern.ch");

        for (var subscriptionItem : subscriptionItems.entrySet()) {
            logger.info("Subscribe to {}", subscriptionItem.getKey());;
            try {
                var subscription = dipFactory.createDipSubscription(
                    subscriptionItem.getKey(),
                    new ProcessingQueueListener() {
                        @Override
                        public void handleMessage(DipSubscription dipSubscription, DipData dipData) {
                            try {
                                subscriptionItem.getValue().process(dipSubscription, dipData);
                            } catch (InvalidSubscriptionException e) {
                                logger.error("Error in processing subscription {}: {}", dipSubscription.getTopicName(), e);
                            }
                        }
                    }
                );
                subscription.requestUpdate();
            } catch (DipException e) {
                logger.error("Error when trying to subscribe to {}: {}", subscriptionItem.getKey(), e.getMessage());
            }
        }
    }

    abstract class ProcessingQueueListener implements DipSubscriptionListener {
        @Override
        public void disconnected(DipSubscription subscription, String reason) {
            System.out.println("Disconnected from " + subscription.getTopicName() + ": " + reason);
        }

        @Override
        public void connected(DipSubscription subscription) {
            System.out.println("Connected to " + subscription.getTopicName());
        }

        @Override
        public void handleException(DipSubscription subscription, Exception ex) {
            System.out.println("Error on " + subscription.getTopicName() + ": " + ex.getMessage());
        }
    }
}
