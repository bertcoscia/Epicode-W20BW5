package bertcoscia.Epicode_W20BW5.repositories;

import bertcoscia.Epicode_W20BW5.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvincieRepository extends JpaRepository<Provincia, UUID> {

    Optional<Provincia> findByNomeIgnoreCase(String name);

    Optional<Provincia> findById(UUID id);

    boolean existsByNome(String nome);

    boolean existsBySigla(String sigla);
}
