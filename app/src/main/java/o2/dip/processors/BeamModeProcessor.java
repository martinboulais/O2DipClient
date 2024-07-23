package o2.dip.processors;

import cern.dip.BadParameter;
import cern.dip.DipData;
import cern.dip.TypeMismatch;

public class BeamModeProcessor extends SingleTopicProcessor {
    public static final String BEAM_MODE_TOPIC = "dip/acc/LHC/RunControl/BeamMode";

    @Override
    protected void process(DipData message) throws BadParameter, TypeMismatch {
        var beamMode = message.extractString("value");

        logger.debug("New beam mode: {}", beamMode);

        // Todo: handle the new beam mode
    }

    @Override
    protected String getHandledTopic() {
        return BEAM_MODE_TOPIC;
    }
}
