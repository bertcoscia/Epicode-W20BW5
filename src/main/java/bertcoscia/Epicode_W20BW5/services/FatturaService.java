package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Fattura;
import bertcoscia.Epicode_W20BW5.entities.StatoFattura;
import bertcoscia.Epicode_W20BW5.repositories.FattureRepository;
import bertcoscia.Epicode_W20BW5.repositories.StatoFatturaRepository;
import bertcoscia.Epicode_W20BW5.specs.FattureSpecs;
import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class FatturaService {

    @Autowired
    private FattureRepository fatturaRepository;

    @Autowired
    private StatoFattureService statoFattureService;

    public Page<Fattura> getAllFatture(int page, int size, String sortBy, Sort.Direction direction, Map<String, String> params) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Specification<Fattura> spec = Specification.where(null);
        if (params.containsKey("cliente")) {
            UUID idCliente = UUID.fromString(params.get("cliente"));
            spec = spec.and(FattureSpecs.hasCliente(idCliente));
        }
        if (params.containsKey("stato")) {
            StatoFattura statoFatturaFound = this.statoFattureService.findByNomeStatoIgnoreCase(params.get("stato"));
            spec = spec.and(FattureSpecs.hasStato(statoFatturaFound.getNomeStato()));
        }
        if (params.containsKey("data")) {
            LocalDate data = LocalDate.parse(params.get("data"));
            spec = spec.and(FattureSpecs.hasData(data));
        }
        if (params.containsKey("anno")) {
            String anno = params.get("anno");
            spec = spec.and(FattureSpecs.hasAnno(anno));
        }
        if (params.containsKey("min") && params.containsKey("max")) {
            double min = Double.valueOf(params.get("min"));
            double max = Double.valueOf(params.get("max"));
            spec = spec.and(FattureSpecs.hasImportoBetween(min, max));
        }
        return this.fatturaRepository.findAll(spec, pageable);
    }

    public Optional<Fattura> getFatturaById(UUID id) {
        return fatturaRepository.findById(id);
    }

    public Fattura saveFattura(Fattura fattura) {
        return fatturaRepository.save(fattura);
    }

    public void deleteFattura(UUID id) {
        fatturaRepository.deleteById(id);
    }
}
