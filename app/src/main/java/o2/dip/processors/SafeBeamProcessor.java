package o2.dip.processors;

import cern.dip.BadParameter;
import cern.dip.DipData;
import cern.dip.TypeMismatch;

import java.math.BigInteger;

public class SafeBeamProcessor extends SingleTopicProcessor {
    public static final String SAFE_BEAM_TOPIC = "dip/acc/LHC/RunControl/SafeBeam";

    @Override
    protected void process(DipData message) throws BadParameter, TypeMismatch {
        int payload = message.extractInt("payload");
        var payloadBigInt = BigInteger.valueOf(payload);
        var beam1 = payloadBigInt.testBit(0);
        var beam2 = payloadBigInt.testBit(4);

        logger.debug("Received safe beam info: beam 1 {} and beam 2 {}", beam1, beam2);

        // TODO handle safe beam topic
    }

    @Override
    protected String getHandledTopic() {
        return SAFE_BEAM_TOPIC;
    }
}
