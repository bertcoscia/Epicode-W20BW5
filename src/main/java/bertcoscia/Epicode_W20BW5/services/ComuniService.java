package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Comune;
import bertcoscia.Epicode_W20BW5.entities.Provincia;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.repositories.ComuniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ComuniService {
    @Autowired
    ComuniRepository repository;

    @Autowired
    ProvinceService provinceService;

    public Comune findByNome(String nome) {
        return this.repository.findByNomeIgnoreCase(nome).orElseThrow(() -> new NotFoundException("Non è stato possibile trovare il comune " + nome));
    }

    public Comune findById(UUID id) {
        return this.repository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public void importData(String csvFilePath) {
        List<Comune> comuniList = new ArrayList<>();

        try (BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath))) {
            String currentLine;
            lineReader.readLine(); // Legge le righe

            while ((currentLine = lineReader.readLine()) != null) {
                String[] data = currentLine.split(";"); // Crea un array di stringhe per ogni linea del csv

                if (data.length < 4) continue; // Salta righe con dati incompleti

                if (data[3].equals("Verbano-Cusio-Ossola")) {
                    data[3] = "Verbania";
                } else if (data[3].equals("Valle d'Aosta/Vallée d'Aoste")) {
                    data[3] = "Aosta";
                } else if (data[3].equals("Monza e della Brianza")) {
                    data[3] = "Monza-Brianza";
                } else if (data[3].equals("Bolzano/Bozen")) {
                    data[3] = "Bolzano";
                } else if (data[3].equals("La Spezia")) {
                    data[3] = "La-Spezia";
                } else if (data[3].equals("Reggio nell'Emilia")) {
                    data[3] = "Reggio-Emilia";
                } else if (data[3].equals("Forlì-Cesena")) {
                    data[3] = "Forli-Cesena";
                } else if (data[3].equals("Pesaro e Urbino")) {
                    data[3] = "Pesaro-Urbino";
                } else if (data[3].equals("Ascoli Piceno")) {
                    data[3] = "Ascoli-Piceno";
                } else if (data[3].equals("Reggio Calabria")) {
                    data[3] = "Reggio-Calabria";
                } else if (data[3].equals("Vibo Valentia")) {
                    data[3] = "Vibo-Valentia";
                } else if (data[3].equals("Sud Sardegna")) {
                    data[3] = "Sud-Sardegna";
                }

                Provincia provinciaFound = provinceService.findByName(data[3]);
                Comune comune = new Comune(data[1], data[0], data[2], provinciaFound);
                comuniList.add(comune);
            }

            repository.saveAll(comuniList);
            System.out.println("Dati inseriti correttamente.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
