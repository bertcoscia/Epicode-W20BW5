package bertcoscia.Epicode_W20BW5.repositories;

import bertcoscia.Epicode_W20BW5.entities.Cliente;
import bertcoscia.Epicode_W20BW5.entities.Fattura;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FattureRepository extends JpaRepository<Fattura, UUID>, JpaSpecificationExecutor<Fattura> {

    Optional<Cliente> findByClienteId(UUID clienteId);

    @Query(value = "SELECT MAX(numero_fattura) FROM fatture", nativeQuery = true)
    String findMaxNumeroFattura();
}
