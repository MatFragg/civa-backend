package pe.civa.matias_aliaga.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pe.civa.matias_aliaga.application.internal.commandservices.BusBrandCommandServiceImpl;
import pe.civa.matias_aliaga.domain.model.commands.SeedBusBrandsCommand;

import java.sql.Timestamp;

@Service
public class CivaReadyEventHandler {
    private final BusBrandCommandServiceImpl busBrandCommandService;
    private static final Logger logger = LoggerFactory.getLogger(CivaReadyEventHandler.class);

    public CivaReadyEventHandler(BusBrandCommandServiceImpl busBrandCommandService) {
        this.busBrandCommandService = busBrandCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getApplicationName();
        logger.info("Starting to verify if bus brands seeding is needed for {} at {}",
                applicationName, currentTimestamp());

        busBrandCommandService.handle(new SeedBusBrandsCommand());
        logger.info("Bus brands seeding verification finished for {} at {}",applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
