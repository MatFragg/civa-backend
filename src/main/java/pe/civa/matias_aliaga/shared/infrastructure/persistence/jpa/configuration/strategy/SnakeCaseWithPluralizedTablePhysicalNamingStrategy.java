package pe.civa.matias_aliaga.shared.infrastructure.persistence.jpa.configuration.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

/**
 * Custom Hibernate physical naming strategy that converts entity names to snake_case
 * and pluralizes table names automatically.
 * This strategy standardizes database naming conventions across the application.
 */
public class SnakeCaseWithPluralizedTablePhysicalNamingStrategy implements PhysicalNamingStrategy {

    /**
     * Converts catalog names to snake_case format.
     * @param identifier The catalog identifier to convert
     * @param jdbcEnvironment The JDBC environment context
     * @return The converted identifier in snake_case format
     */
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Converts schema names to snake_case format.
     * @param identifier The schema identifier to convert
     * @param jdbcEnvironment The JDBC environment context
     * @return The converted identifier in snake_case format
     */
    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Converts table names to pluralized snake_case format.
     * Example: "BusEntity" becomes "bus_entities"
     * @param identifier The table identifier to convert
     * @param jdbcEnvironment The JDBC environment context
     * @return The converted identifier in pluralized snake_case format
     */
    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {

        return this.toSnakeCase(this.toPlural(identifier));
    }

    /**
     * Converts sequence names to snake_case format.
     * @param identifier The sequence identifier to convert
     * @param jdbcEnvironment The JDBC environment context
     * @return The converted identifier in snake_case format
     */
    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Converts column names to snake_case format.
     * @param identifier The column identifier to convert
     * @param jdbcEnvironment The JDBC environment context
     * @return The converted identifier in snake_case format
     */
    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Converts camelCase identifiers to snake_case format.
     * Uses regex to replace camelCase patterns with underscore-separated lowercase.
     * @param identifier The identifier to convert
     * @return The converted identifier in snake_case format, or null if input is null
     */
    private Identifier toSnakeCase(final Identifier identifier) {
        if (identifier == null) {
            return null;
        }
        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        final String newName = identifier.getText()
                .replaceAll(regex, replacement)
                .toLowerCase();
        return Identifier.toIdentifier(newName);
    }

    /**
     * Converts singular identifiers to their plural form.
     * Uses the Pluralize library to handle English pluralization rules.
     * @param identifier The identifier to pluralize
     * @return The pluralized identifier
     */
    private Identifier toPlural(final Identifier identifier) {
        final String newName = pluralize(identifier.getText());
        return Identifier.toIdentifier(newName);
    }
}