package o2.dip.processors;

import cern.dip.BadParameter;
import cern.dip.DipData;
import cern.dip.TypeMismatch;

public class BetaStarProcessor extends SingleTopicProcessor {
    public static final String BETA_STAR_TOPIC = "dip/acc/LHC/Beam/BetaStar/Bstar2";

    @Override
    protected void process(DipData message) throws BadParameter, TypeMismatch {
        var betaStar2 = message.extractInt("payload");

        logger.debug("Received Beta* 2: {}", betaStar2);

        // Todo: handle the new beta *
    }

    @Override
    protected String getHandledTopic() {
        return BETA_STAR_TOPIC;
    }
}
