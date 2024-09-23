package bertcoscia.Epicode_W20BW5.controllers;

import bertcoscia.Epicode_W20BW5.entities.Fattura;
import bertcoscia.Epicode_W20BW5.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public List<Fattura> getAllFatture() {
        return fatturaService.findAll();
    }

    @GetMapping("/{id}")
    public Fattura getFatturaById(@PathVariable UUID id) {
        return fatturaService.findById(id);
    }

    @PostMapping
    public Fattura createFattura(@RequestBody Fattura fattura) {
        return fatturaService.save(fattura);
    }

    @DeleteMapping("/{id}")
    public void deleteFattura(@PathVariable UUID id) {
        fatturaService.deleteById(id);
    }
}
