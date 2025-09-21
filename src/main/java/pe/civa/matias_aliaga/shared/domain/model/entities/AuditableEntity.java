package pe.civa.matias_aliaga.shared.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Abstract base entity that provides auditing capabilities for domain entities.
 * Automatically tracks creation and modification timestamps for entities that extend this class.
 * Uses Spring Data JPA auditing to populate timestamp fields automatically.
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class AuditableEntity{

    /** Unique identifier for the entity, auto-generated */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Timestamp when the entity was created (immutable after creation) */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    /** Timestamp when the entity was last modified (updated automatically) */
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
}