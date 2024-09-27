package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Provincia;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.payloads.NewEntityRespDTO;
import bertcoscia.Epicode_W20BW5.payloads.NewProvinceDTO;
import bertcoscia.Epicode_W20BW5.services.ProvinceService;
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
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    ProvinceService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewEntityRespDTO save(@RequestBody @Validated NewProvinceDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(messages);
        } else {
            return new NewEntityRespDTO(this.service.save(body).getIdProvincia());
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Page<Provincia> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy) {
        return this.service.findAll(page, size, sortBy);
    }

    @GetMapping("/{idProvincia}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Provincia findById(@PathVariable UUID idProvincia) {
        return this.service.findById(idProvincia);
    }

    @PutMapping("/{idProvincia}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Provincia findByIdAndUpdate(@PathVariable UUID idProvincia, @RequestBody @Validated Provincia body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException(messages);
        } else {
            return this.service.findByIdAndUpdate(idProvincia, body);
        }
    }

    @DeleteMapping("/{idProvincia}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID idProvincia) {
        this.service.findByIdAndDelete(idProvincia);
    }
}
