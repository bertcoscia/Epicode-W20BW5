package bertcoscia.Epicode_W20BW5.repository;

import bertcoscia.Epicode_W20BW5.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
}
