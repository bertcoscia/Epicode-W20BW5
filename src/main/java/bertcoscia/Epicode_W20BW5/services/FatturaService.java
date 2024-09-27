package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Cliente;
import bertcoscia.Epicode_W20BW5.entities.Fattura;
import bertcoscia.Epicode_W20BW5.entities.StatoFattura;
import bertcoscia.Epicode_W20BW5.repositories.FattureRepository;
import bertcoscia.Epicode_W20BW5.repositories.StatoFatturaRepository;
import bertcoscia.Epicode_W20BW5.specs.FattureSpecs;
import org.hibernate.query.SortDirection;
import bertcoscia.Epicode_W20BW5.entities.StatoFattura;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.payloads.FatturaDTO;
import bertcoscia.Epicode_W20BW5.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Service
public class FatturaService {

    @Autowired
    private FattureRepository fatturaRepository;

    @Autowired
    private StatoFattureService statoFattureService;

    @Autowired
    private ClientiService clientiService;

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
            int anno = Integer.parseInt(params.get("anno"));
            spec = spec.and(FattureSpecs.hasAnno(anno));
        }
        if (params.containsKey("min") && params.containsKey("max")) {
            double min = Double.parseDouble(params.get("min"));
            double max = Double.parseDouble(params.get("max"));
            spec = spec.and(FattureSpecs.hasImportoBetween(min, max));
        }
        return this.fatturaRepository.findAll(spec, pageable);
    }

    public Fattura findById(UUID fatturaId) {
        return fatturaRepository.findById(fatturaId).orElseThrow(() -> new NotFoundException(fatturaId));
    }

    public Fattura saveFattura(FatturaDTO body) {
        Cliente clienteFound = clientiService.findClienteById(UUID.fromString(body.clienteId()));
        StatoFattura statoFatturaFound = this.statoFattureService.findByNomeStatoIgnoreCase(body.nomeStatoFattura());
        int maxNumeroFattura;
        if (this.fatturaRepository.findMaxNumeroFattura() == null) {
            maxNumeroFattura = 0;
        } else {
            maxNumeroFattura = Integer.parseInt(this.fatturaRepository.findMaxNumeroFattura());
        }
        int newNumeroFattura = maxNumeroFattura + 1;
        return fatturaRepository.save(new Fattura(clienteFound, LocalDate.parse(body.data()), body.importo(), statoFatturaFound, String.valueOf(newNumeroFattura)));
    }

    public void findByIdAndDelete(UUID fatturaId) {
        Fattura found = findById(fatturaId);
        this.fatturaRepository.delete(found);
    }

    public Fattura findByIdAndUpdate(UUID fatturaId, FatturaDTO updateBody) {
        Fattura found = findById(fatturaId);
        LocalDate data;
        try {
            data = LocalDate.parse(updateBody.data());
        } catch (DateTimeParseException e) {
            throw new BadRequestException("Formato della data non valido: " + updateBody.data() + ", il formato deve essere yyyy-mm-dd!");
        }
        UUID clienteId;
        try {
            clienteId = UUID.fromString(updateBody.clienteId());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Id dello user non valido: " + updateBody.clienteId() + ". Deve essere un UUID valido.");
        }
        StatoFattura statoFattura;
        try {
            statoFattura = statoFattureService.findByNomeStatoIgnoreCase(updateBody.nomeStatoFattura());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Il nome dello stato fattura non è valido: " + updateBody.nomeStatoFattura());
        }
        Cliente cliente = clientiService.findClienteById(clienteId);
        found.setCliente(cliente);
        found.setData(data);
        found.setStatoFattura(statoFattura);
        found.setImporto(updateBody.importo());
        return fatturaRepository.save(found);
    }

    /*public Page<Fattura> findFattureByCliente(UUID clienteId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findByClienteId(clienteId, pageable);
    }

    public Page<Fattura> findFattureByStato(UUID statoId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findByStatoId(statoId, pageable);
    }

    public Page<Fattura> findFattureByData(LocalDate startDate, LocalDate endDate, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findByDataBetween(startDate, endDate, pageable);
    }

    public Page<Fattura> findFattureByAnno(int anno, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findByAnno(anno, pageable);
    }

    public Page<Fattura> findFattureByImportoRange(Double minImporto, Double maxImporto, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findByImportoBetween(minImporto, maxImporto, pageable);
    }*/
}
