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

    @Query(value = "SELECT * FROM clienti ORDER BY nome_societa ASC", nativeQuery = true)
    Page<Cliente> orderByNameAsc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY nome_societa DESC", nativeQuery = true)
    Page<Cliente> orderByNameDesc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY fatturato_annuale ASC", nativeQuery = true)
    Page<Cliente> orderByFatturatoAnnualeAsc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY fatturato_annuale DESC", nativeQuery = true)
    Page<Cliente> orderByFatturatoAnnualeDesc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY data_inserimento ASC", nativeQuery = true)
    Page<Cliente> orderByDataInserimentoAsc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY data_inserimento DESC", nativeQuery = true)
    Page<Cliente> orderByDataInserimentoDesc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY data_ultimo_contatto ASC", nativeQuery = true)
    Page<Cliente> orderByDataUltimoContattoAsc(Pageable pageable);

    @Query(value = "SELECT * FROM clienti ORDER BY data_ultimo_contatto DESC", nativeQuery = true)
    Page<Cliente> orderByDataUltimoContattoDesc(Pageable pageable);

    @Query("SELECT c FROM Cliente c ORDER BY c.sedeLegale.comune.provincia.nome ASC")
    Page<Cliente> orderBySedeLegaleComuneProvinciaNomeAsc(Pageable pageable);

    @Query("SELECT c FROM Cliente c ORDER BY c.sedeLegale.comune.provincia.nome DESC")
    Page<Cliente> orderBySedeLegaleComuneProvinciaNomeDesc(Pageable pageable);

    Page<Cliente> findByFatturatoAnnuale(double fatturatoAnnuale, Pageable pageable);

    Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);

    Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nomeSocieta) LIKE LOWER(CONCAT('%', :nomeSocieta, '%'))")
    Page<Cliente> findByNomeSocietaLike(@Param("nomeSocieta") String nomeSocieta, Pageable pageable);

}
