package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Cliente;
import bertcoscia.Epicode_W20BW5.entities.Fattura;
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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private StatoFattureService statoFattureService;
    @Autowired
    private ClientiService clientiService;

    public Page<Fattura> findAll(int page, int size, String sortBy) {
        if (page > 20) page = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.fatturaRepository.findAll(pageable);
    }

    public Fattura findById(UUID fatturaId) {
        return fatturaRepository.findById(fatturaId).orElseThrow(() -> new NotFoundException(fatturaId));
    }

    public Fattura saveFattura(FatturaDTO body) {
        LocalDate data;
        try {
            data = LocalDate.parse(body.data());
        } catch (DateTimeParseException e) {
            throw new BadRequestException("Formato della data non valido: " + body.data() + ", il formato deve essere yyyy-mm-dd!");
        }
        UUID clienteId;
        try {
            clienteId = UUID.fromString(body.clienteId());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Id dello user non valido: " + body.clienteId() + ". Deve essere un UUID valido.");
        }
        StatoFattura statoFattura;
        try {
            statoFattura = statoFattureService.findByNomeStato(body.nomeStatoFattura());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Il nome dello stato fattura non è valido: " + body.nomeStatoFattura());
        }
        Cliente cliente = clientiService.findClienteById(clienteId);
        Fattura fattura = new Fattura(cliente, data, body.importo(), statoFattura);
        return fatturaRepository.save(fattura);
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
            statoFattura = statoFattureService.findByNomeStato(updateBody.nomeStatoFattura());
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

    public Page<Fattura> findFattureByCliente(UUID clienteId, int page, int size, String sortBy) {
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
    }
}
