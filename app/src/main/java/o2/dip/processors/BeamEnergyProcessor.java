package o2.dip.processors;

import cern.dip.BadParameter;
import cern.dip.DipData;
import cern.dip.TypeMismatch;

public class BeamEnergyProcessor extends SingleTopicProcessor {
    public static final String BEAM_ENERGY_TOPIC = "dip/acc/LHC/Beam/Energy";

    @Override
    protected void process(DipData message) throws BadParameter, TypeMismatch {
        var payload = message.extractInt("payload");
        // As per doc, real energy has to be multiplied by 120 Mev to get the real energy
        var energy = payload * 120;

        logger.debug("Received beam energy: {}", energy);

        // Todo: handle properly beam energy
    }

    @Override
    protected String getHandledTopic() {
        return BEAM_ENERGY_TOPIC;
    }
}
