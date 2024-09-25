package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Indirizzo;
import bertcoscia.Epicode_W20BW5.entities.Provincia;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.payloads.NewProvinceDTO;
import bertcoscia.Epicode_W20BW5.repositories.ProvincieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;


@Service
public class ProvinceService {
    @Autowired
    private ProvincieRepository repository;

    public Provincia findByName(String provincia) {
        return this.repository.findByNome(provincia).orElseThrow(() -> new NotFoundException("Non è stato possibile trovare la provincia con nome " + provincia));
    }

    public Provincia save(NewProvinceDTO body) {
        if (this.repository.existsByNomeAndSigla(body.nome(), body.sigla())) throw new BadRequestException("Esista già una provincia con nome " + body.nome() + " e sigla " + body.sigla());
        return this.repository.save(new Provincia(body.nome(), body.sigla(), body.regione()));
    }

    public Provincia findById(UUID id) {
        return this.repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<Provincia> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.repository.findAll(pageable);
    }

    public Provincia findByIdAndUpdate(UUID id, Provincia body) {
        Provincia found = this.findById(id);
        if (this.repository.existsByNomeAndSigla(body.getNome(), body.getSigla()) && !found.getIdProvincia().equals(body.getIdProvincia())) throw new BadRequestException("Esista già una provincia con nome " + body.getNome() + " e sigla " + body.getSigla());
        found.setNome(body.getNome());
        found.setSigla(body.getSigla());
        found.setRegione(body.getRegione());
        return this.repository.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        this.repository.delete(this.findById(id));
    }


    public void importData(String csvFilePath) {
        List<Provincia> provinceList = new ArrayList<>();

        try (BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath))) {
            String currentLine;
            lineReader.readLine(); // Legge le righe

            while ((currentLine = lineReader.readLine()) != null) {
                String[] data = currentLine.split(";"); // Crea un array di stringhe per ogni linea del csv

                switch (data[1]) {
                    case "Verbania" -> data[1] = "Verbano-Cusio-Ossola";
                    case "Aosta" -> data[1] = "Valle d'Aosta/Vallée d'Aoste";
                    case "Monza-Brianza" -> data[1] = "Monza e della Brianza";
                    case "Bolzano" -> data[1] = "Bolzano/Bozen";
                    case "La-Spezia" -> data[1] = "La Spezia";
                    case "Reggio-Emilia" -> data[1] = "Reggio nell'Emilia";
                    case "Forli-Cesena" -> data[1] = "Forlì-Cesena";
                    case "Pesaro-Urbino" -> data[1] = "Pesaro e Urbino";
                    case "Ascoli-Piceno" -> data[1] = "Ascoli Piceno";
                    case "Reggio-Calabria" -> data[1] = "Reggio Calabria";
                    case "Vibo-Valentia" -> data[1] = "Vibo Valentia";
                    case "Carbonia Iglesias", "Medio Campidano" -> {
                        data[1] = "Sud Sardegna";
                        data[0] = "SU";
                    }
                    case "Olbia Tempio" -> data[1] = "Sassari";
                    case "Ogliastra" -> data[1] = "Nuoro";
                    case "Roma" -> data[0] = "RM";
                }

                Provincia newProvincia = new Provincia(data[1], data[0], data[2]);
                Predicate<Provincia> existsByName = provincia ->
                        provincia.getNome().equals(newProvincia.getNome());
                if (provinceList.stream().noneMatch(existsByName)) provinceList.add(newProvincia);
            }

            repository.saveAll(provinceList);
            System.out.println("Dati inseriti correttamente.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}