package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Indirizzo;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.payloads.NewEntityRespDTO;
import bertcoscia.Epicode_W20BW5.payloads.NewIndirizziDTO;
import bertcoscia.Epicode_W20BW5.services.IndirizziService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/indirizzi")
public class IndirizziController {
    @Autowired
    IndirizziService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewEntityRespDTO save(@RequestBody @Validated NewIndirizziDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(messages);
        } else {
            return new NewEntityRespDTO(this.service.save(body).getIdIndirizzo());
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Page<Indirizzo> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "via") String sortBy) {
        return this.service.findAll(page, size, sortBy);
    }

    @GetMapping("/{idIndirizzo}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Indirizzo findById(@PathVariable UUID idIndirizzo) {
        return this.service.findById(idIndirizzo);
    }

    @PutMapping("/{idIndirizzo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Indirizzo findByIdAndUpdate(@PathVariable UUID idIndirizzo, @RequestBody @Validated Indirizzo body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(messages);
        } else {
            return this.service.findByIdAndUpdate(idIndirizzo, body);
        }
    }

    @DeleteMapping("/{idIndirizzo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID idIndirizzo) {
        this.service.findByIdAndDelete(idIndirizzo);
    }
}
