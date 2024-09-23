package bertcoscia.Epicode_W20BW5.repositories;

import bertcoscia.Epicode_W20BW5.entities.Clienti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientiRepository extends JpaRepository<Clienti, UUID> {
    
}
