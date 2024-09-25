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
                Provincia provinciaFound = provinceService.findByName(data[3]);
                Comune comune = new Comune(data[0], data[1], data[2], provinciaFound);
                comuniList.add(comune);
            }

            List<Comune> comuniSassari = comuniList.stream()
                    .filter(comune -> comune.getProgressivoProvincia().equals("090"))
                    .toList();
            for (int i = 0; i < comuniSassari.size(); i++) {
                if (i < 9) {
                    comuniSassari.get(i).setProgressivoComune("00" + (i + 1));
                } else if (i < 99) {
                    comuniSassari.get(i).setProgressivoComune("0" + (i + 1));
                } else {
                    comuniSassari.get(i).setProgressivoComune(String.valueOf(i + 1));
                }
            }

            repository.saveAll(comuniList);
            System.out.println("Dati inseriti correttamente.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
