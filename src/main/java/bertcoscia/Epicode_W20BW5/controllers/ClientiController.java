package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Clienti;
import bertcoscia.Epicode_W20BW5.services.ClientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClientiController {
    @Autowired
    private ClientiService clientiService;

    // 1 --> GET ALL
    @GetMapping
    public List<Clienti> findAllCliente() {
        return clientiService.findAllClienti();
    }

    // 2 --> GET CLIENTE
    @GetMapping("/{clienteId}")
    public Clienti findByIdCliente(@PathVariable UUID clienteId) {
        return this.clientiService.findClienteById(clienteId);
    }

    //3 ----> DELETE CLIENTE

    @DeleteMapping("/{clienteId}")
    public void findByIdAndDelete(@PathVariable UUID clienteId) {
        this.clientiService.findByClienteIdAndDelete(clienteId);
    }
}
