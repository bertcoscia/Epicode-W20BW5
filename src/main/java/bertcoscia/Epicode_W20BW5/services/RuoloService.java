package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Ruolo;
import bertcoscia.Epicode_W20BW5.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuoloService {
    @Autowired
    private RuoloRepository ruoloRepository;

    public List<Ruolo> findAll() {
        return ruoloRepository.findAll();
    }

    public Ruolo save(Ruolo ruolo) {
        return ruoloRepository.save(ruolo);
    }

    public Optional<Ruolo> findById(Long id) {
        return ruoloRepository.findById(id);
    }

    public void delete(Long id) {
        ruoloRepository.deleteById(id);
    }
}