package pe.civa.matias_aliaga.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.civa.matias_aliaga.domain.model.queries.GetAllBusBrandsQuery;
import pe.civa.matias_aliaga.domain.services.BusBrandQueryService;
import pe.civa.matias_aliaga.interfaces.rest.resources.BusBrandResource;
import pe.civa.matias_aliaga.interfaces.rest.transform.BusBrandResourceFromEntityAssembler;

import java.util.List;

/**
 * REST controller for managing bus brands.
 * Provides endpoints for retrieving bus brand information.
 * Supports CORS for frontend integration on localhost:5173.
 */
@RestController
@RequestMapping(value = "/api/v1/bus-brands", produces = "application/json")
@Tag( name = "Bus Brands", description = "Endpoint for managing bus brands")
@CrossOrigin(origins = "*")
public class BusBrandsController {
    
    /** Service for handling bus brand query operations */
    private final BusBrandQueryService busBrandQueryService;
    
    /**
     * Constructor for BusBrandsController.
     * @param busBrandQueryService The service for bus brand query operations
     */
    @Autowired
    public BusBrandsController(BusBrandQueryService busBrandQueryService) {
        this.busBrandQueryService = busBrandQueryService;
    }

    /**
     * Retrieves all available bus brands.
     * Returns a list of all bus brands currently stored in the system.
     *
     * @return ResponseEntity containing a list of BusBrandResource objects
     */
    @GetMapping
    @Operation(
            summary = "Get all bus brands",
            description = "Retrieve a list of all bus brands",
            operationId = "getAllBusBrands",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of bus brands retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<BusBrandResource>> getAllBusBrands() {
        var getAllBusBrandsQuery = new GetAllBusBrandsQuery();
        var busBrands = this.busBrandQueryService.handle(getAllBusBrandsQuery);
        var busBrandResources = busBrands.stream().map(BusBrandResourceFromEntityAssembler::toResource).toList();
        
        return ResponseEntity.ok(busBrandResources);
    }
    
}
