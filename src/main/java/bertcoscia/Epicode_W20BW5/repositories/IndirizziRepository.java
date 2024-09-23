package bertcoscia.Epicode_W20BW5.repositories;

import bertcoscia.Epicode_W20BW5.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IndirizziRepository extends JpaRepository<Indirizzo, UUID> {

    boolean existsByViaAndCivicoAndLocalita(String via, int civico, String localita);
}
