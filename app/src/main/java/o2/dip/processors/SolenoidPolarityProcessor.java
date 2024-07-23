package o2.dip.processors;

import cern.dip.BadParameter;
import cern.dip.DipData;
import cern.dip.TypeMismatch;

public class SolenoidPolarityProcessor extends SingleTopicProcessor {
  public static final String SOLENOID_POLARITY_TOPIC = "dip/ALICE/MCS/Solenoid/Polarity";

  @Override
  protected void process(DipData message) throws BadParameter, TypeMismatch {
    var isNegative = message.extractBoolean();

    logger.debug("Received solenoid polarity: {}", isNegative ? "negative" : "positive");

    // Todo: handle properly solenoid polarity
  }

  @Override
  protected String getHandledTopic() {
    return SOLENOID_POLARITY_TOPIC;
  }
}
