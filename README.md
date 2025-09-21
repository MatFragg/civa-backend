# 🚌 Sistema de Gestión de Flota de Buses CIVA - Backend API

Una API REST robusta desarrollada con Spring Boot 3 y Java 23 para la gestión de flotas de buses de CIVA Transportation. Este proyecto implementa arquitectura limpia (Clean Architecture) con separación en capas N-Tier y mejores prácticas de desarrollo empresarial.

## 📋 Tabla de Contenidos

- [Características Principales](#características-principales)
- [Arquitectura del Proyecto](#arquitectura-del-proyecto)
- [Patrones de Diseño](#patrones-de-diseño)
- [Tecnologías y Bibliotecas](#tecnologías-y-bibliotecas)
- [Estructura de Carpetas](#estructura-de-carpetas)
- [Instalación y Configuración](#instalación-y-configuración)
- [Endpoints de la API](#endpoints-de-la-api)
- [Base de Datos](#base-de-datos)
- [Documentación JavaDoc](#documentación-javadoc)

## ✨ Características Principales

- **API REST Completa**: Operaciones CRUD para gestión de buses y marcas
- **Paginación Avanzada**: Soporte nativo de paginación con Spring Data
- **Validación Robusta**: Validación de datos con Bean Validation (JSR-303)
- **Documentación Automática**: API documentada con OpenAPI 3.0 (Swagger)
- **Manejo Global de Errores**: Sistema centralizado de manejo de excepciones
- **Clean Architecture**: Implementación de arquitectura limpia con separación N-Tier
- **Value Objects**: Objetos de valor inmutables con validación integrada
- **Auditoría Automática**: Timestamps automáticos de creación y modificación

> Se recomienda utilizar Postman o la UI de Swagger para añadir buses mediante el metodo POST

## 🏗️ Arquitectura del Proyecto

### Clean Architecture (Arquitectura Limpia) - N-Tier

El proyecto implementa una arquitectura limpia organizada en capas N-Tier con separación clara de responsabilidades:

```
src/main/java/pe/civa/matias_aliaga/
├── domain/                    # Capa de Dominio
│   ├── model/
│   │   ├── entities/         # Entidades de negocio
│   │   ├── valueobjects/     # Objetos de valor
│   │   ├── commands/         # Comandos para operaciones
│   │   ├── queries/          # Consultas para datos
│   │   └── exceptions/       # Excepciones del dominio
│   └── services/             # Interfaces de servicios del dominio
├── application/              # Capa de Aplicación
│   └── internal/
│       ├── commandservices/  # Servicios de comando (casos de uso)
│       └── queryservices/    # Servicios de consulta (casos de uso)
├── infrastructure/           # Capa de Infraestructura
│   └── persistence/
│       └── jpa/
│           └── repositories/ # Implementaciones de persistencia
├── interfaces/               # Capa de Interfaces
│   └── rest/                # Controladores REST y DTOs
│       ├── resources/       # DTOs de entrada/salida
│       └── transform/       # Transformadores/Assemblers
└── shared/                  # Elementos compartidos entre capas
    ├── domain/
    ├── infrastructure/
    └── interfaces/
```

### Separación de Responsabilidades por Capas

- **Domain**: Entidades de negocio, Value Objects, interfaces de servicios y reglas de dominio
- **Application**: Casos de uso, orquestación de servicios y lógica de aplicación
- **Infrastructure**: Implementaciones de persistencia, configuraciones y adaptadores externos
- **Interfaces**: Controllers REST, DTOs, validaciones de entrada y transformadores
- **Shared**: Utilidades transversales, configuraciones comunes y elementos base

## 🎨 Patrones de Diseño

### 1. **Command Query Responsibility Segregation (CQRS)**
Separación clara entre operaciones de lectura y escritura:
```java
// Commands (Escritura)
public record CreateBusCommand(int busNumber, String licensePlate, String characteristics, String brand, boolean isActive)

// Queries (Lectura)
public record GetAllBusesQuery()
public record GetBusByIdQuery(Long id)
```

### 2. **Repository Pattern**
Abstracción del acceso a datos:
```java
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsBusByLicensePlateAndBusNumber(LicensePlate licensePlate, int busNumber);
}
```

### 3. **Service Layer Pattern**
Servicios especializados para comandos y consultas:
```java
@Service
public class BusCommandServiceImpl implements BusCommandService {
    public Long handle(CreateBusCommand command) { /* ... */ }
}

@Service
public class BusQueryServiceImpl implements BusQueryService {
    public List<Bus> handle(GetAllBusesQuery query) { /* ... */ }
}
```

### 4. **Value Object Pattern**
Objetos inmutables con validación integrada:
```java
@Embeddable
public class LicensePlate {
    public static final String VALIDATION_PATTERN = "^[A-Z]\\d[A-Z]-\\d{3}$";
    
    public LicensePlate(String value) {
        if (value == null || !value.matches(VALIDATION_PATTERN)) {
            throw new IllegalArgumentException("Invalid license plate format");
        }
        this.value = value;
    }
}
```

### 5. **Assembler Pattern**
Transformación entre capas:
```java
public class BusResourceFromEntityAssembler {
    public static BusResource toResource(Bus entity) {
        return new BusResource(
            entity.getId(),
            entity.getLicensePlate().getValue(),
            entity.getBrand().getStringName(),
            // ... más campos
        );
    }
}
```

### 6. **Factory Pattern**
Creación de objetos complejos:
```java
public static BusBrand toBusBrandFromName(String name) {
    return new BusBrand(BusBrands.fromString(name));
}
```

### 7. **Strategy Pattern**
Estrategia personalizada de nomenclatura de base de datos:
```java
public class SnakeCaseWithPluralizedTablePhysicalNamingStrategy implements PhysicalNamingStrategy {
    // Convierte "BusEntity" a "bus_entities"
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(this.toPlural(identifier));
    }
}
```

## 🛠️ Tecnologías y Bibliotecas

### Framework Principal
- **Spring Boot 3.5.6**: Framework principal para desarrollo empresarial
- **Java 23**: Última versión LTS con características modernas

### Persistencia y Base de Datos
- **Spring Data JPA**: Abstracción de acceso a datos
- **Hibernate**: ORM principal
- **PostgreSQL**: Base de datos relacional principal
- **Bean Validation (Hibernate Validator)**: Validación declarativa

### Documentación y API
- **SpringDoc OpenAPI 3 (2.7.0)**: Documentación automática con Swagger UI
- **Jakarta Validation**: Anotaciones de validación estándar

### Herramientas de Desarrollo
- **Lombok**: Reducción de código boilerplate
- **Spring Boot DevTools**: Herramientas de desarrollo y hot reload
- **Apache Commons Lang3 (3.18.0)**: Utilidades adicionales para Java

### Utilidades Especializadas
- **Pluralize (1.0.0)**: Pluralización automática de nombres de tablas
- **Jackson**: Serialización/deserialización JSON automática

### Testing
- **Spring Boot Test Starter**: Framework de testing integrado
- **JUnit 5**: Framework de testing moderno
- **Mockito**: Mocking para pruebas unitarias

## 📁 Estructura de Carpetas

```
src/main/java/pe/civa/matias_aliaga/
├── MatiasAliagaApplication.java           # Clase principal Spring Boot
├── application/
│   └── internal/
│       ├── commandservices/
│       │   ├── BusCommandServiceImpl.java
│       │   └── BusBrandCommandServiceImpl.java
│       └── queryservices/
│           ├── BusQueryServiceImpl.java
│           └── BusBrandQueryServiceImpl.java
├── domain/
│   ├── model/
│   │   ├── commands/
│   │   │   ├── CreateBusCommand.java
│   │   │   └── SeedBusBrandsCommand.java
│   │   ├── entities/
│   │   │   ├── Bus.java                   # Entidad principal Bus
│   │   │   └── BusBrand.java              # Entidad BusBrand
│   │   ├── exceptions/
│   │   │   └── BusNotFoundException.java
│   │   ├── queries/
│   │   │   ├── GetAllBusesQuery.java
│   │   │   ├── GetBusByIdQuery.java
│   │   │   └── GetBusBrandByNameQuery.java
│   │   └── valueobjects/
│   │       ├── BusBrands.java             # Enum de marcas
│   │       └── LicensePlate.java          # Value Object
│   └── services/
│       ├── BusCommandService.java
│       ├── BusQueryService.java
│       └── BusBrandQueryService.java
├── infrastructure/
│   └── persistence/
│       └── jpa/
│           └── repositories/
│               ├── BusRepository.java
│               └── BusBrandRepository.java
├── interfaces/
│   └── rest/
│       ├── BusesController.java           # Controller principal
│       ├── BusBrandsController.java
│       ├── resources/                     # DTOs
│       │   ├── BusResource.java
│       │   ├── CreateBusResource.java
│       │   └── BusBrandResource.java
│       └── transform/                     # Assemblers
│           ├── BusResourceFromEntityAssembler.java
│           ├── CreateBusCommandFromResourceAssembler.java
│           └── BusBrandResourceFromEntityAssembler.java
└── shared/
    ├── domain/
    │   └── model/
    │       └── entities/
    │           └── AuditableEntity.java   # Entidad base con auditoría
    ├── infrastructure/
    │   ├── documentation/
    │   │   └── openapi/
    │   │       └── configuration/
    │   │           └── OpenApiConfiguration.java
    │   └── persistence/
    │       └── jpa/
    │           └── configuration/
    │               └── strategy/
    │                   └── SnakeCaseWithPluralizedTablePhysicalNamingStrategy.java
    └── interfaces/
        └── rest/
            └── responses/
                └── ErrorResponse.java     # Respuesta de error estándar
```

## 🚀 Instalación y Configuración

### Prerrequisitos
- Java 23
- Maven 3.9+
- PostgreSQL 16+

### Configuración de Base de Datos
```sql
-- Crear base de datos
CREATE DATABASE civa;
```

### Variables de Entorno
```properties
# application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/civa
spring.datasource.username=postgres
spring.datasource.password=password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server Configuration
server.port=8091

# OpenAPI Configuration
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### Instalación
```bash
# Clonar el repositorio
git clone <repository-url>

# Navegar al directorio del backend
cd backend-api/matias-aliaga

# Compilar el proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Ejecutar la aplicación
mvn spring-boot:run
```

## 🌐 Endpoints de la API

### Buses Management
```http
GET    /api/v1/buses                    # Obtener todos los buses
GET    /api/v1/buses?paginated=true     # Obtener buses paginados
GET    /api/v1/buses/{id}               # Obtener bus por ID
POST   /api/v1/buses                    # Crear nuevo bus
```

### Bus Brands Management
```http
GET    /api/v1/bus-brands               # Obtener todas las marcas
```

### Documentación
```http
GET    /swagger-ui.html                 # Swagger UI
GET    /v3/api-docs                     # OpenAPI JSON
```

### Ejemplos de Uso

#### Crear un nuevo bus:
```json
POST /api/v1/buses
Content-Type: application/json

{
    "busNumber": 1234,
    "licensePlate": "A1B-123",
    "characteristics": "Aire acondicionado, WiFi, TV",
    "brand": "Scania",
    "isActive": true
}
```

#### Respuesta paginada:
```json
GET /api/v1/buses?paginated=true&page=0&size=10

{
    "content": [...],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10
    },
    "totalElements": 50,
    "totalPages": 5,
    "first": true,
    "last": false
}
```

## 🗄️ Base de Datos

### Esquema Principal
```sql
-- Tabla de marcas de buses
CREATE TABLE bus_brands (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Tabla principal de buses
CREATE TABLE buses (
    id BIGSERIAL PRIMARY KEY,
    bus_number INTEGER NOT NULL UNIQUE CHECK (bus_number >= 1000 AND bus_number <= 9999),
    license_plate VARCHAR(8) NOT NULL UNIQUE,
    characteristics VARCHAR(100),
    brand_id BIGINT NOT NULL REFERENCES bus_brands(id),
    is_active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

### Características de la Base de Datos
- **Convención de Nomenclatura**: snake_case automático
- **Pluralización**: Nombres de tabla pluralizados automáticamente
- **Auditoría**: Timestamps automáticos de creación y modificación
- **Validaciones**: Constraints de base de datos para integridad
- **Índices**: Índices automáticos en claves únicas y foráneas

## 📚 Documentación JavaDoc

Todo el código está completamente documentado con JavaDoc incluyendo:

- **Entidades y Value Objects**: Documentación de todas las propiedades y métodos
- **Servicios**: Casos de uso, parámetros y excepciones
- **Controllers**: Endpoints, parámetros de entrada y respuestas
- **Repositorios**: Métodos de consulta y criterios de búsqueda
- **DTOs**: Propósito y estructura de datos

### Ejemplo de Documentación:
```java
/**
 * Bus entity representing a transportation vehicle.
 * Contains bus information including number, license plate, characteristics, brand and active status.
 */
@Entity
@Table(name="buses")
public class Bus extends AuditableEntity {
    
    /**
     * Constructor to create a bus with all required parameters.
     * @param busNumber The bus number (must be positive)
     * @param licensePlate The bus license plate (cannot be null)
     * @param characteristics The bus characteristics description
     * @param isActive The bus active status
     * @param brand The bus brand (cannot be null)
     * @throws IllegalArgumentException if any validation fails
     */
    public Bus(int busNumber, LicensePlate licensePlate, String characteristics, boolean isActive, BusBrand brand) {
        // Implementación...
    }
}
```

## 🏆 Características Técnicas Destacadas

- **Type Safety**: Uso extensivo de Records y Value Objects
- **Immutability**: Objetos inmutables donde sea apropiado
- **Validation**: Validación en múltiples capas (DTO, Domain, Database)
- **Error Handling**: Manejo global y consistente de errores
- **Performance**: Lazy loading, paginación eficiente
- **Security**: Validación de entrada, prevención de inyección SQL
- **Maintainability**: Código autodocumentado y arquitectura limpia
- **Testability**: Dependencias inyectadas, interfaces bien definidas

## 🔧 Patrones de Validación

### Validación en Capas
```java
// 1. Validación de DTO (Entrada)
public record CreateBusResource(
    @Min(value = 1, message = "Bus number must be greater than 0")
    int busNumber,
    
    @Pattern(regexp = "^[A-Z]\\d[A-Z]-\\d{3}$", message = "Invalid license plate format")
    String licensePlate
) {}

// 2. Validación de Value Object (Dominio)
public class LicensePlate {
    public LicensePlate(String value) {
        if (value == null || !value.matches(VALIDATION_PATTERN)) {
            throw new IllegalArgumentException("Invalid license plate format");
        }
    }
}

// 3. Validación de Entidad (Persistencia)
@Entity
public class Bus {
    @Min(1000) @Max(9999)
    @Column(unique = true, nullable = false)
    private int busNumber;
}
```

---

## 👨‍💻 Desarrollado por

**Ethan Matias Aliaga Aguirre**  
📧 ethan.aliaga@gmail.com  
📱 +51 980 805 285  
📍 Lima, Perú

---

*Proyecto desarrollado como parte de la evaluación técnica para CIVA Transportation - 2025*
