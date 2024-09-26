package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Cliente;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.payloads.NewClienteDTO;
import bertcoscia.Epicode_W20BW5.payloads.NewEntityRespDTO;
import bertcoscia.Epicode_W20BW5.services.ClientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clienti")
public class ClientiController {
    @Autowired
    private ClientiService clientiService;

    // 1 --> GET ALL
    @GetMapping
    public Page<Cliente> findAllCliente(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam Map<String,String> params) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return this.clientiService.findAllClienti(page, size, sortBy, direction, params);
    }

    // 2 --> GET CLIENTE

    @GetMapping("/{clienteId}")
    public Cliente findByIdCliente(@PathVariable UUID clienteId) {
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
    public NewEntityRespDTO save(@RequestBody @Validated NewClienteDTO body, BindingResult validationResult) throws org.apache.coyote.BadRequestException {

        if (validationResult.hasErrors()) {

            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));

            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {


            return new NewEntityRespDTO(this.clientiService.saveCliente(body).getId());
        }

    }

    // 6 -----> PUT
    @PutMapping("/{clienteId}")
    public Cliente findByIdAndUpdate(@PathVariable UUID clienteId, @RequestBody Cliente body) throws org.apache.coyote.BadRequestException {
        return this.clientiService.findByClienteIdAndUpdate(clienteId, body);
    }

    // 7 --> UPLOAD
    @PostMapping("/{clienteId}/logoAziendale")
    public void uploadLogoAziendale(@RequestParam("logoAziendale") MultipartFile image) throws IOException {
        this.clientiService.uploadImg(image);
    }

    @GetMapping("/filter-fatturato")
    public Page<Cliente> findByFatturatoAnnuale(
            @RequestParam double fatturato,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.clientiService.findByFatturatoAnnuale(fatturato, page, size, sortBy);
    }

    @GetMapping("/filter-dataInserimento")
    public Page<Cliente> findByDataInserimento(
            @RequestParam LocalDate data,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nomeSocieta") String sortBy) {
        return this.clientiService.findByDataInserimento(data, page, size, sortBy);
    }

    @GetMapping("/filter-dataUltimoContatto")
    public Page<Cliente> findByDataUltimoContatto(
            @RequestParam LocalDate data,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nomeSocieta") String sortBy) {
        return this.clientiService.findByDataUltimoContatto(data, page, size, sortBy);
    }

    @GetMapping("/filter-nome")
    public Page<Cliente> findByNomeLike(
            @RequestParam String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nomeSocieta") String sortBy) {
        return this.clientiService.findByNomeLike(nome, page, size, sortBy);
    }
}
