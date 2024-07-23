package o2.dip.processors;

import cern.dip.BadParameter;
import cern.dip.DipData;
import cern.dip.TypeMismatch;

public class DipoleCurrentProcessor extends SingleTopicProcessor {
  public static final String DIPOLE_CURRENT_TOPIC = "dip/ALICE/MCS/Dipole/Current";

  @Override
  protected void process(DipData message) throws BadParameter, TypeMismatch {
    var current = message.extractFloat();

    logger.debug("Received dipole current: {}", current);

    // Todo: handle properly dipole current
  }

  @Override
  protected String getHandledTopic() {
    return DIPOLE_CURRENT_TOPIC;
  }
}
