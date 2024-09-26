package bertcoscia.Epicode_W20BW5.specs;

import bertcoscia.Epicode_W20BW5.entities.Fattura;
import bertcoscia.Epicode_W20BW5.entities.StatoFattura;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class FattureSpecs {

    public static Specification<Fattura> hasCliente(UUID idCliente) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("cliente").get("id"), idCliente);
        };
    }

    public static Specification<Fattura> hasStato(String stato) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get("statoFattura").get("nomeStato")), stato.toLowerCase());
        };
    }

    public static Specification<Fattura> hasData(LocalDate data) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("data"), data);
        };
    }

    public static Specification<Fattura> hasAnno(String anno) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class, root.get("data")), Integer.valueOf(anno));
        };
    }

    public static Specification<Fattura> hasImportoBetween(double min, double max) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.between(root.get("importo"), min, max);
        };
    }
}
