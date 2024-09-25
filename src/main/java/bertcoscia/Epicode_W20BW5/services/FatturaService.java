package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Fattura;
import bertcoscia.Epicode_W20BW5.repository.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;

    public List<Fattura> getAllFatture() {
        return fatturaRepository.findAll();
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
