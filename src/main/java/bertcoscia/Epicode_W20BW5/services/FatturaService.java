package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Fattura;
import bertcoscia.Epicode_W20BW5.repository.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository atturaRepository;

    public List<Fattura> findAll() {
        return fatturaRepository.findAll();
    }

    public Fattura findById(UUID id) {
        return fatturaRepository.findById(id).orElse(null);
    }

    public Fattura save(Fattura fattura) {
        return fatturaRepository.save(fattura);
    }

    public void deleteById(UUID id) {
        fatturaRepository.deleteById(id);
    }
}
