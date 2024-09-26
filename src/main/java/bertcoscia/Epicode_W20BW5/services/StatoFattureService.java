package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.StatoFattura;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatoFattureService {
    @Autowired
    StatoFatturaRepository repository;

    public StatoFattura findByNomeStatoIgnoreCase(String nome) {
        return this.repository.findByNomeStatoIgnoreCase(nome).orElseThrow(() -> new NotFoundException("Non è stato possibile trovare uno stato fattura chiamato " + nome));
    }
}
