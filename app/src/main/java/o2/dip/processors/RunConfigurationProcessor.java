package o2.dip.processors;

import cern.dip.BadParameter;
import cern.dip.DipData;
import cern.dip.TypeMismatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunConfigurationProcessor extends SingleTopicProcessor {
    public static final String RUN_CONFIGURATION_TOPIC = "dip/acc/LHC/RunControl/RunConfiguration";

    @Override()
    public void process(DipData message) throws BadParameter, TypeMismatch {
        var fillNumberStr = message.extractString("FILL_NO");
        var fillNumber = message.extractInt("FILL_NO");
        var time = message.extractDipTime().getAsMillis();

        var particleTypeB1 = message.extractString("PARTICLE_TYPE_B1");
        var particleTypeB2 = message.extractString("PARTICLE_TYPE_B2");
        var activeInjectionScheme = message.extractString("ACTIVE_INJECTION_SCHEME");
        var ip2NoCollisions = message.extractString("IP2-NO-COLLISIONS");
        var noBunches = message.extractString("NO_BUNCHES");

        logger.debug(
            "Received a new fill ({} - {}) at {}: B1 ({}), B2 ({}), Injection scheme ({}), Ip2 no collisions ({}), N° bunches ({})",
            fillNumber,
            fillNumberStr,
            time,
            particleTypeB1,
            particleTypeB2,
            activeInjectionScheme,
            ip2NoCollisions,
            noBunches
        );

        // Todo: publish the new fill number in kafka
    }

    @Override()
    public String getHandledTopic() {
        return RUN_CONFIGURATION_TOPIC;
    }
}
