package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Ruolo;
import bertcoscia.Epicode_W20BW5.services.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ruoli")
public class RuoloController {
    @Autowired
    private RuoloService ruoloService;

    @GetMapping
    public List<Ruolo> getAllRuoli() {
        return ruoloService.findAll();
    }

    @PostMapping
    public Ruolo createRuolo(@RequestBody Ruolo ruolo) {
        return ruoloService.save(ruolo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ruolo> getRuoloById(@PathVariable Long id) {
        return ruoloService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRuolo(@PathVariable Long id) {
        ruoloService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
