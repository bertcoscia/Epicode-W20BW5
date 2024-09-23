package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Indirizzo;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.payloads.NewEntityRespDTO;
import bertcoscia.Epicode_W20BW5.payloads.NewIndirizziDTO;
import bertcoscia.Epicode_W20BW5.services.IndirizziService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/indirizzi")
public class IndirizziController {
    @Autowired
    IndirizziService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    public List<Indirizzo> findAll() {
        return this.service.findAll();
    }
}
