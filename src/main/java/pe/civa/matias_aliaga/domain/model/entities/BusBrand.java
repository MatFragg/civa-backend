package pe.civa.matias_aliaga.domain.model.entities;


import jakarta.persistence.*;
import lombok.*;
import pe.civa.matias_aliaga.domain.model.valueobjects.BusBrands;

/**
 * Bus brand entity representing different bus manufacturers.
 * Contains the brand information as an enumerated type.
 */
@Entity
@Table(name="bus_brands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@With
public class BusBrand {
    /**
     * Unique identifier for the bus brand.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Bus brand name as an enumerated value.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="name", length = 50)
    private BusBrands name;

    /**
     * Constructor to create a bus brand with a specific brand name.
     * @param name The bus brand enumerated value
     */
    public BusBrand(BusBrands name) {
        this.name = name;
    }

    /**
     * Gets the string representation of the bus brand name.
     * @return The brand name as a string
     */
    public String getStringName() {
        return name.name();
    }

    /**
     * Factory method to create a BusBrand from a string name.
     * @param name The brand name as string
     * @return A new BusBrand instance
     */
    public static BusBrand toBusBrandFromName(String name) {
        return new BusBrand(BusBrands.fromString(name));
    }
}
