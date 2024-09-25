package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Fattura;
import bertcoscia.Epicode_W20BW5.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public List<Fattura> getAllFatture() {
        return fatturaService.getAllFatture();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fattura> getFatturaById(@PathVariable UUID id) {
        Optional<Fattura> fattura = fatturaService.getFatturaById(id);
        return fattura.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fattura createFattura(@RequestBody Fattura fattura) {
        return fatturaService.saveFattura(fattura);
    }

    @DeleteMapping("/{id}")
    public void deleteFattura(@PathVariable UUID id) {
        fatturaService.deleteFattura(id);
    }
}
