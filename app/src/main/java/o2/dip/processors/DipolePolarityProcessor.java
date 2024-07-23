package o2.dip.processors;

import cern.dip.BadParameter;
import cern.dip.DipData;
import cern.dip.TypeMismatch;

public class DipolePolarityProcessor extends SingleTopicProcessor {
  public static final String DIPOLE_POLARITY_TOPIC = "dip/ALICE/MCS/Dipole/Polarity";

  @Override
  protected void process(DipData message) throws BadParameter, TypeMismatch {
    var isNegative = message.extractBoolean();

    logger.debug("Received dipole polarity: {}", isNegative ? "negative" : "positive");

    // Todo: handle properly dipole polarity
  }

  @Override
  protected String getHandledTopic() {
    return DIPOLE_POLARITY_TOPIC;
  }
}
