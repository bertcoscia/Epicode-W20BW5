package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.StatoFattura;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.payloads.StatoFatturaDTO;
import bertcoscia.Epicode_W20BW5.services.StatoFattureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statofatture")
public class StatoFattureController {
    @Autowired
    private StatoFattureService statoFattureService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    /*public Page<StatoFattura> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return this.statoFattureService.findAll(page, size, sortBy);
    }*/
    public List<StatoFattura> findAll() {
        return this.statoFattureService.findAll();
    }

    @GetMapping("/{statoFatturaId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public StatoFattura findById(@PathVariable UUID statoFatturaId) {
        return this.statoFattureService.findById(statoFatturaId);
    }

    @PutMapping("/{statoFatturaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StatoFattura findByIdAndUpdate(@PathVariable UUID statoFatturaId, @RequestBody @Validated StatoFatturaDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return this.statoFattureService.findByIdAndUpdate(statoFatturaId, body);
        }
    }

    @DeleteMapping("/{statoFatturaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable UUID statoFatturaId) {
        this.statoFattureService.findByIdAndDelete(statoFatturaId);
    }
}
