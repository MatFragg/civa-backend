package pe.civa.matias_aliaga.domain.services;

import pe.civa.matias_aliaga.domain.model.commands.CreateBusCommand;

public interface BusCommandService {
    Long handle(CreateBusCommand command);
}
