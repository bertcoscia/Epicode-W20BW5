package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Clienti;
import bertcoscia.Epicode_W20BW5.repositories.ClientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientiService {

    @Autowired
    private ClientiRepository clientiRepository;


    public List<Clienti> findAllClienti() {
        return clientiRepository.findAll();
    }


    /*public Clienti findClienteById(UUID clienteId) {
        return this.clientiRepository.findById(clienteId).orElseThrow(() -> new NotFoundException(clienteId));
    }*/
}