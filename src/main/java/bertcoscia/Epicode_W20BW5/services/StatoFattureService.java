package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.StatoFattura;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StatoFattureService {
    @Autowired
    StatoFatturaRepository repository;
    private StatoFatturaRepository statoFatturaRepository;

    public StatoFattura saveStatoFattura(StatoFatturaDTO body) {
        if (body == null) {
            throw new BadRequestException("La richiesta deve avere un body!");
        } else if (this.statoFatturaRepository.existsByNomeStatoIgnoreCase(body.nomeStato())) {
            throw new BadRequestException("Lo stato: " + body.nomeStato() + " è già presente come stato fattura!");
        } else {
            StatoFattura statoFattura = new StatoFattura(body.nomeStato());
            return this.statoFatturaRepository.save(statoFattura);
        }
    }

    public StatoFattura findByNomeStatoIgnoreCase(String nome) {
        return this.repository.findByNomeStatoIgnoreCase(nome).orElseThrow(() -> new NotFoundException("Non è stato possibile trovare uno stato fattura chiamato " + nome));
    public StatoFattura findById(UUID statoFatturaId) {
        return this.statoFatturaRepository.findById(statoFatturaId).orElseThrow(() -> new NotFoundException(statoFatturaId));
    }

    public StatoFattura findByNomeStato(String nomeStato) {
        return statoFatturaRepository.findByNomeStato(nomeStato).orElseThrow(() -> new NotFoundException("Il nome dello Stato " + nomeStato + " non è stato trovato!"));
    }

    public Page<StatoFattura> findAll(int page, int size, String sortBy) {
        if (page > 20) page = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.statoFatturaRepository.findAll(pageable);
    }

    public void findByIdAndDelete(UUID statoFatturaId) {
        StatoFattura found = findById(statoFatturaId);
        this.statoFatturaRepository.delete(found);
    }

    public StatoFattura findByIdAndUpdate(UUID statoFatturaId, StatoFatturaDTO updateBody) {
        StatoFattura found = findById(statoFatturaId);
        if (this.statoFatturaRepository.existsByNomeStatoIgnoreCase(updateBody.nomeStato())) {
            throw new BadRequestException("L'email " + updateBody.nomeStato() + " è già in uso!");
        } else {
            found.setNomeStato(updateBody.nomeStato());
            return statoFatturaRepository.save(found);
        }
    }
}
