package bertcoscia.Epicode_W20BW5.specs;

import bertcoscia.Epicode_W20BW5.entities.Cliente;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ClientiSpecs {

    public static Specification<Cliente> hasFatturato(Long fatturato) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("fatturatoAnnuale"), fatturato);
        };
    }

    public static Specification<Cliente> hasDataInserimento(LocalDate dataInserimento) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("dataInserimento"), dataInserimento);
        };
    }

    public static Specification<Cliente> hasDataUltimoContatto(LocalDate dataUltimoContatto) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("dataUltimoContatto"), dataUltimoContatto);
        };
    }

    public static Specification<Cliente> hasNomeLike(String nome) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("nomeSocieta"), "%" + nome + "%");
        };
    }
}
