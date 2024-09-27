package bertcoscia.Epicode_W20BW5.repositories;

import bertcoscia.Epicode_W20BW5.entities.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RuoloRepository extends JpaRepository<Ruolo, UUID> {
    public Ruolo findByNome(String nome);

    public boolean existsByNome(String nome);
}
