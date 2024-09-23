package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Provincia;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.repositories.ProvincieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProvinceService {
    @Autowired
    private ProvincieRepository repository;

    public Provincia findByName(String provincia) {
        return this.repository.findByNome(provincia).orElseThrow(() -> new NotFoundException("Non è stato possibile trovare la provincia con nome " + provincia));
    }

    public void importData(String csvFilePath) {
        List<Provincia> provinceList = new ArrayList<>();

        try (BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath))) {
            String currentLine;
            lineReader.readLine(); // Legge le righe

            while ((currentLine = lineReader.readLine()) != null) {
                String[] data = currentLine.split(";"); // Crea un array di stringhe per ogni linea del csv

                if (data.length < 3) continue; // Salta righe con dati incompleti

                Provincia provincia = new Provincia(data[1], data[0], data[2]);
                provinceList.add(provincia);
            }

            repository.saveAll(provinceList);
            System.out.println("Dati inseriti correttamente.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}