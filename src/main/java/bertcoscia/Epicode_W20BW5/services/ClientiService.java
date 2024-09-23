package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Clienti;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.repositories.ClientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientiService {

    @Autowired
    private ClientiRepository clientiRepository;

    //Find All
    public List<Clienti> findAllClienti() {
        return clientiRepository.findAll();
    }

    // Find by ID
    public Clienti findClienteById(UUID clienteId) {
        return this.clientiRepository.findById(clienteId).orElseThrow(() -> new NotFoundException(clienteId));
    }

    // DELETE
    public void findByClienteIdAndDelete(UUID clienteId) {
        Clienti found = this.findClienteById(clienteId);
        this.clientiRepository.delete(found);


    }
}