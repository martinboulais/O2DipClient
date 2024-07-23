package o2.dip;

import cern.dip.*;

public class DipClient {
    DipFactory dipFactory = Dip.create();

    DipClient(String[] subscriptionItems) {
        var handler = new ProcessingQueueListener();

        for (String subscriptionItem : subscriptionItems) {
            try {
                dipFactory.createDipSubscription(subscriptionItem, handler);
            } catch (DipException e) {
            }
        }
    }

    class ProcessingQueueListener implements DipSubscriptionListener {
        @Override
        public void handleMessage(DipSubscription subscription, DipData message) {
        }

        @Override
        public void disconnected(DipSubscription subscription, String reason) {
        }

        @Override
        public void connected(DipSubscription subscription) {
        }

        @Override
        public void handleException(DipSubscription subscription, Exception ex) {
        }
    }
}
