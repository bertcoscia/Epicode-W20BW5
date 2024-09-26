package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Fattura;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.payloads.FatturaDTO;
import bertcoscia.Epicode_W20BW5.payloads.NewEntityRespDTO;
import bertcoscia.Epicode_W20BW5.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public Page<Fattura> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return fatturaService.findAll(page, size, sortBy);
    }

    @GetMapping("/{fatturaId}")
    public Fattura findById(@PathVariable UUID fatturaId) {
        return this.fatturaService.findById(fatturaId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewEntityRespDTO save(@RequestBody @Validated FatturaDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return new NewEntityRespDTO(this.fatturaService.saveFattura(body).getId());
        }

    }

    @DeleteMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable UUID fatturaId) {
        fatturaService.findByIdAndDelete(fatturaId);
    }

    @PutMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura findByIdAndUpdate(@PathVariable UUID fatturaId, @RequestBody @Validated FatturaDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return this.fatturaService.findByIdAndUpdate(fatturaId, body);
        }
    }
}
