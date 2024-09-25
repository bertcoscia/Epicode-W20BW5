package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Ruolo;
import bertcoscia.Epicode_W20BW5.payloads.RuoloDTO;
import bertcoscia.Epicode_W20BW5.services.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ruoli")
public class RuoloController {
    @Autowired
    private RuoloService ruoloService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Ruolo> getAllRuoli() {
        return ruoloService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Ruolo createRuolo(@RequestBody @Validated RuoloDTO ruolo) {
        return ruoloService.save(ruolo);
    }

    @GetMapping("/{ruoloId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Ruolo getRuoloById(@PathVariable UUID ruoloId) {
        return ruoloService.findById(ruoloId);
    }

    @DeleteMapping("/{ruoloId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteRuolo(@PathVariable UUID ruoloId) {
        ruoloService.delete(ruoloId);
    }
}
