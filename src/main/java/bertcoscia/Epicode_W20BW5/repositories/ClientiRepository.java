package bertcoscia.Epicode_W20BW5.repositories;

import bertcoscia.Epicode_W20BW5.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientiRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPartitaIva(String partitaIva);

    boolean existsByPec(String pec);
}
