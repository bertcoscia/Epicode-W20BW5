package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Comune;
import bertcoscia.Epicode_W20BW5.entities.Indirizzo;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.payloads.NewIndirizziDTO;
import bertcoscia.Epicode_W20BW5.repositories.IndirizziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IndirizziService {
    @Autowired
    IndirizziRepository repository;

    @Autowired
    ComuniService comuniService;

    public Indirizzo save(NewIndirizziDTO body) {
        if (this.repository.existsByViaAndCivicoAndComuneNome(body.via(), body.civico(), body.comune())) throw new BadRequestException("Esiste già un indirizzo registrato a " + body.via() + " " + body.civico() + " presso il comune di " + body.comune());
        Comune comuneFound = this.comuniService.findByNome(body.comune());
        Indirizzo newIndirizzo = new Indirizzo(body.via(), body.civico(), body.localita(), comuneFound);
        return this.repository.save(newIndirizzo);
    }

    public Page<Indirizzo> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.repository.findAll(pageable);
    }

    public Indirizzo findById(UUID id) {
        return this.repository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Indirizzo findByIdAndUpdate(UUID id, Indirizzo body) {
        Indirizzo indirizzoFound = this.findById(id);
        if (this.repository.existsByViaAndCivicoAndComuneIdComune(body.getVia(), body.getCivico(), body.getComune().getIdComune()) && !indirizzoFound.getIdIndirizzo().equals(body.getIdIndirizzo())) throw new BadRequestException("Esiste già un indirizzo registrato a " + body.getVia() + " " + body.getCivico() + " presso la località " + body.getLocalita());
        Comune comuneFound = this.comuniService.findById(body.getComune().getIdComune());
        indirizzoFound.setCap(body.getCap());
        indirizzoFound.setVia(body.getVia());
        indirizzoFound.setCivico(body.getCivico());
        indirizzoFound.setLocalita(body.getLocalita());
        return this.repository.save(indirizzoFound);
    }

    public void findByIdAndDelete(UUID id) {
        Indirizzo found = this.findById(id);
        this.repository.delete(found);
    }
}
