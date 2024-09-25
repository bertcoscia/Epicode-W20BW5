package bertcoscia.Epicode_W20BW5.repositories;

import bertcoscia.Epicode_W20BW5.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComuniRepository extends JpaRepository<Comune, UUID> {

    Optional<Comune> findByNomeIgnoreCase(String nome);

    List<Comune> findByProgressivoComune(String progressivoComune);
}
