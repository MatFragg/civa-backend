package pe.civa.matias_aliaga.domain.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.civa.matias_aliaga.domain.model.entities.Bus;
import pe.civa.matias_aliaga.domain.model.queries.GetAllBusesQuery;
import pe.civa.matias_aliaga.domain.model.queries.GetBusByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BusQueryService {
    Optional<Bus> handle(GetBusByIdQuery query);
    List<Bus> handle(GetAllBusesQuery query);
    Page<Bus> handle(Pageable query);
}
