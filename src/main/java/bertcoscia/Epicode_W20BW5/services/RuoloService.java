package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Ruolo;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.payloads.RuoloDTO;
import bertcoscia.Epicode_W20BW5.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RuoloService {
    @Autowired
    private RuoloRepository ruoloRepository;

    public List<Ruolo> findAll() {
        return ruoloRepository.findAll();
    }

    public Ruolo save(RuoloDTO body) {
        if (body == null) {
            throw new BadRequestException("Devi inserire il body del Ruolo!");
        } else if (ruoloRepository.existsByNome(body.nome())) {
            throw new BadRequestException("Questo Ruolo è gia presente!");
        } else {
            Ruolo ruolo = new Ruolo(body.nome().toUpperCase());
            return ruoloRepository.save(ruolo);
        }
    }

    public Ruolo findById(UUID ruoloId) {
        return ruoloRepository.findById(ruoloId).orElseThrow(() -> new NotFoundException(ruoloId));
    }

    public void delete(UUID ruoloId) {
        Ruolo ruolo = findById(ruoloId);
        ruoloRepository.delete(ruolo);
    }
}