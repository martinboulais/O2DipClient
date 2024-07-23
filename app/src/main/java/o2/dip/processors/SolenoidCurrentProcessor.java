package o2.dip.processors;

import cern.dip.BadParameter;
import cern.dip.DipData;
import cern.dip.TypeMismatch;

public class SolenoidCurrentProcessor extends SingleTopicProcessor {
    public static final String SOLENOID_CURRENT_TOPIC = "dip/ALICE/MCS/Solenoid/Current";

    @Override
    protected void process(DipData message) throws BadParameter, TypeMismatch {
        var current = message.extractFloat();

        logger.debug("Received solenoid current: {}", current);

        // Todo: handle properly solenoid current
    }

    @Override
    protected String getHandledTopic() {
        return SOLENOID_CURRENT_TOPIC;
    }
}
