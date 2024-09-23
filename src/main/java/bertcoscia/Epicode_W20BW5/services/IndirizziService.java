package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Comune;
import bertcoscia.Epicode_W20BW5.entities.Indirizzo;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.payloads.NewEntityRespDTO;
import bertcoscia.Epicode_W20BW5.payloads.NewIndirizziDTO;
import bertcoscia.Epicode_W20BW5.repositories.IndirizziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizziService {
    @Autowired
    IndirizziRepository repository;

    @Autowired
    ComuniService comuniService;

    public Indirizzo save(NewIndirizziDTO body) {
        if (this.repository.existsByViaAndCivicoAndLocalita(body.via(), body.civico(), body.localita())) throw new BadRequestException("Esiste già un indirizzo registrato a " + body.via() + " " + body.civico() + " presso la località " + body.localita());
        Comune comuneFound = this.comuniService.findByNome(body.comune());
        Indirizzo newIndirizzo = new Indirizzo(body.via(), body.civico(), body.localita(), comuneFound);
        return this.repository.save(newIndirizzo);
    }

    public List<Indirizzo> findAll() {
        return this.repository.findAll();
    }
}
