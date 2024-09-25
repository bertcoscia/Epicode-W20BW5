package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Clienti;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.payloads.NewClienteDTO;
import bertcoscia.Epicode_W20BW5.payloads.NewClienteRespDTO;
import bertcoscia.Epicode_W20BW5.services.ClientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    //4 ------> POST CLIENTE

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewClienteRespDTO save(@RequestBody @Validated NewClienteDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {

            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {


            return new NewClienteRespDTO(this.clientiService.saveCliente(body).getId());
        }

    }

    // 6 -----> PUT
    @PutMapping("/{clienteId}")
    public Clienti findByIdAndUpdate(@PathVariable UUID clienteId, @RequestBody Clienti body) {
        return this.clientiService.findByClienteIdAndUpdate(clienteId, body);
    }
}
