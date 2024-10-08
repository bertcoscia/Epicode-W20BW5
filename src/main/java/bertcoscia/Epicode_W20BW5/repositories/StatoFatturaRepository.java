package bertcoscia.Epicode_W20BW5.repositories;

import bertcoscia.Epicode_W20BW5.entities.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, UUID> {

    Optional<StatoFattura> findByNomeStatoIgnoreCase(String nome);

    public boolean existsByNomeStatoIgnoreCase(String nomeStato);
}
