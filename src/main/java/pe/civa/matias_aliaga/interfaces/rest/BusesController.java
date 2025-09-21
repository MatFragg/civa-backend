package pe.civa.matias_aliaga.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.civa.matias_aliaga.domain.model.entities.Bus;
import pe.civa.matias_aliaga.domain.model.queries.GetAllBusesQuery;
import pe.civa.matias_aliaga.domain.model.queries.GetBusByIdQuery;
import pe.civa.matias_aliaga.domain.services.BusCommandService;
import pe.civa.matias_aliaga.domain.services.BusQueryService;
import pe.civa.matias_aliaga.interfaces.rest.resources.BusResource;
import pe.civa.matias_aliaga.interfaces.rest.resources.CreateBusResource;
import pe.civa.matias_aliaga.interfaces.rest.transform.BusResourceFromEntityAssembler;
import pe.civa.matias_aliaga.interfaces.rest.transform.CreateBusCommandFromResourceAssembler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for managing buses.
 * Provides endpoints for creating, retrieving, and managing bus information.
 * Supports both paginated and non-paginated data retrieval.
 * Supports CORS for frontend integration on localhost:5173.
 */
@RestController
@RequestMapping(value = "/api/v1/buses",produces = APPLICATION_JSON_VALUE)
@Tag(name = "Buses", description = "Endpoints for managing buses")
@CrossOrigin(origins = "*")
public class BusesController {

    /** Service for handling bus query operations */
    private final BusQueryService busQueryService;
    /** Service for handling bus command operations */
    private final BusCommandService busCommandService;

    /**
     * Constructor for BusesController.
     * @param busQueryService The service for bus query operations
     * @param busCommandService The service for bus command operations
     */
    @Autowired
    public BusesController(BusQueryService busQueryService, BusCommandService busCommandService) {
        this.busQueryService = busQueryService;
        this.busCommandService = busCommandService;
    }

    /**
     * Creates a new bus in the system.
     * Validates the input data, creates the bus, and returns the created bus information.
     *
     * @param resource The CreateBusResource containing the bus data to be created
     * @return ResponseEntity containing the created BusResource or error status
     */
    @Operation(
            summary = "Add a new bus",
            description = "Create a new bus with the provided details",
            operationId = "createBus",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Bus created successfully",content = @Content(mediaType = "application/json",schema = @Schema(implementation = BusResource.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",content = @Content(mediaType = "application/json",schema = @Schema(implementation = RuntimeException.class)))
    }
    )
    @PostMapping
    public ResponseEntity<BusResource> createBus(@Valid @RequestBody CreateBusResource resource){
        var createBusCommand = CreateBusCommandFromResourceAssembler.toCommand(resource);

        var busId = this.busCommandService.handle(createBusCommand);


        if (busId.equals(0L))
            return ResponseEntity.badRequest().build();

        var getBusByIdQuery = new GetBusByIdQuery(busId);

        var optionalBus = this.busQueryService.handle(getBusByIdQuery);

        if(optionalBus.isEmpty())
            return ResponseEntity.badRequest().build();

        var busResource = BusResourceFromEntityAssembler.toResource(optionalBus.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(busResource);
    }

    /**
     * Retrieves all buses with optional pagination support.
     * Can return either all buses or a paginated subset based on the request parameters.
     *
     * @param paginated Whether to use pagination (default: false)
     * @param page The page number for pagination (default: 0)
     * @param size The page size for pagination (default: 10)
     * @return ResponseEntity containing either a list of all buses or a paginated result
     */
    @Operation(
            summary = "Get all buses",
            description = "Retrieve a list of all buses",
            operationId = "getAllBuses",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of buses retrieved successfully",content = @Content(mediaType = "application/json",schema = @Schema(implementation = BusResource.class)))
            }
    )
    @GetMapping
    public ResponseEntity<?> getAllBuses(
            @RequestParam(defaultValue = "false") boolean paginated,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (paginated) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Bus> busPage = busQueryService.handle(pageable);
            return ResponseEntity.ok(busPage.map(BusResourceFromEntityAssembler::toResource));

        } else {
            var getAllBusesQuery = new GetAllBusesQuery();
            var buses = this.busQueryService.handle(getAllBusesQuery);
            var busResources = buses.stream().map(BusResourceFromEntityAssembler::toResource).toList();
            return ResponseEntity.ok(busResources);
        }
    }

    /**
     * Retrieves a specific bus by its unique identifier.
     *
     * @param id The unique identifier of the bus to retrieve
     * @return ResponseEntity containing the BusResource if found, or 404 status if not found
     */
    @Operation(
            summary = "Get a bus by ID",
            description = "Retrieve a bus by its unique ID",
            operationId = "getBusById",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Bus retrieved successfully",content = @Content(mediaType = "application/json",schema = @Schema(implementation = BusResource.class))),
                    @ApiResponse(responseCode = "404", description = "Bus not found",content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BusResource> getBusById(@PathVariable Long id){
        var getBusByIdQuery = new GetBusByIdQuery(id);
        var optionalBus = this.busQueryService.handle(getBusByIdQuery);

        if(optionalBus.isEmpty())
            return ResponseEntity.notFound().build();

        var busResource = BusResourceFromEntityAssembler.toResource(optionalBus.get());

        return ResponseEntity.ok(busResource);
    }
}
