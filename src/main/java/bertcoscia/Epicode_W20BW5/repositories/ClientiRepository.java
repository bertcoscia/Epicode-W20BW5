package bertcoscia.Epicode_W20BW5.repositories;

import bertcoscia.Epicode_W20BW5.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface ClientiRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPartitaIva(String partitaIva);

    boolean existsByPec(String pec);

    @Query(value = "SELECT * FROM clienti ORDER BY name ASC", nativeQuery = true)
    Page<Cliente> orderByNameAsc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY name DESC", nativeQuery = true)
    Page<Cliente> orderByNameDesc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY fatturatoAnnuale ASC", nativeQuery = true)
    Page<Cliente> orderByFatturatoAnnualeAsc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY fatturatoAnnuale DESC", nativeQuery = true)
    Page<Cliente> orderByFatturatoAnnualeDesc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY dataInserimento ASC", nativeQuery = true)
    Page<Cliente> orderByDataInserimentoAsc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY dataInserimento DESC", nativeQuery = true)
    Page<Cliente> orderByDataInserimentoDesc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY dataUltimoContatto ASC", nativeQuery = true)
    Page<Cliente> orderByDataUltimoContattoAsc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY dataUltimoContatto DESC", nativeQuery = true)
    Page<Cliente> orderByDataUltimoContattoDesc(Pageable pageable);

    @Query("SELECT c FROM Cliente c ORDER BY c.sedeLegale.comune.provincia.nome ASC")
    Page<Cliente> orderBySedeLegaleComuneProvinciaNomeAsc(Pageable pageable);

    @Query("SELECT c FROM Cliente c ORDER BY c.sedeLegale.comune.provincia.nome DESC")
    Page<Cliente> orderBySedeLegaleComuneProvinciaNomeDesc(Pageable pageable);

    Page<Cliente> filterByFatturatoAnnuale(double fatturatoAnnuale, Pageable pageable);

    Page<Cliente> filterByDataInserimento(LocalDate dataInserimento, Pageable pageable);

    Page<Cliente> filterByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Cliente> filterByNomeLike(@Param("nome") String nome, Pageable pageable);

}
