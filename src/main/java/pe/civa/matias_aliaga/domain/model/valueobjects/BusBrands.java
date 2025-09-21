package pe.civa.matias_aliaga.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Enumeration of available bus brands with their corresponding values.
 * Represents different bus manufacturers and their identification numbers.
 */
public enum BusBrands {
    /** Mercedes-Benz bus brand */
    MERCEDES_BENZ (1),
    /** Volvo bus brand */
    VOLVO (2),
    /** Scania bus brand */
    SCANIA (3),
    /** MAN bus brand */
    MAN (4),
    /** Irizar bus brand */
    IRIZAR (5),
    /** Neoplan bus brand */
    NEOPLAN (6),
    /** Setra bus brand */
    SETRA (7),
    /** Yutong bus brand */
    YUTONG (8),
    /** King Long bus brand */
    KING_LONG (9),
    /** BYD bus brand */
    BYD (10),
    /** Marcopolo bus brand */
    MARCOPOLO (11);

    /** The numeric value associated with the bus brand */
    private final int value;

    /**
     * Constructor for BusBrands enum.
     * @param value The numeric identifier for the bus brand
     */
    BusBrands(int value) {
        this.value = value;
    }

    /**
     * Creates a BusBrands enum value from a string representation.
     * Normalizes the input by trimming, converting to uppercase, and replacing spaces/hyphens with underscores.
     * @param name The string representation of the bus brand name
     * @return The corresponding BusBrands enum value
     * @throws IllegalArgumentException if the name is null or doesn't match any enum value
     */
    @JsonCreator
    public static BusBrands fromString(String name) {
        if (name == null) throw new IllegalArgumentException("Brand name is null");
        String normalized = name.trim()
                .toUpperCase()
                .replace(" ", "_")
                .replace("-", "_");
        return BusBrands.valueOf(normalized);
    }
}
