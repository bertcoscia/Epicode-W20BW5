package bertcoscia.Epicode_W20BW5.repositories;

import bertcoscia.Epicode_W20BW5.entities.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

    @Query(value = "SELECT f FROM Fattura f WHERE f.cliente.id = :clienteId", nativeQuery = true)
    Page<Fattura> findByClienteId(@Param("clienteId") UUID clienteId, Pageable pageable);

    @Query(value = "SELECT f FROM Fattura f WHERE f.statoFattura.id = :statoId", nativeQuery = true)
    Page<Fattura> findByStatoId(@Param("statoId") UUID statoId, Pageable pageable);

    @Query(value = "SELECT f FROM Fattura f WHERE f.data BETWEEN :startDate AND :endDate", nativeQuery = true)
    Page<Fattura> findByDataBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);

    @Query(value = "SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno", nativeQuery = true)
    Page<Fattura> findByAnno(@Param("anno") int anno, Pageable pageable);

    @Query(value = "SELECT f FROM Fattura f WHERE f.importo BETWEEN :minImporto AND :maxImporto", nativeQuery = true)
    Page<Fattura> findByImportoBetween(@Param("minImporto") Double minImporto, @Param("maxImporto") Double maxImporto, Pageable pageable);
}
