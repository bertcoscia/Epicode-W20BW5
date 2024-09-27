package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Comune;
import bertcoscia.Epicode_W20BW5.entities.Provincia;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.payloads.NewComuniDTO;
import bertcoscia.Epicode_W20BW5.payloads.NewEntityRespDTO;
import bertcoscia.Epicode_W20BW5.repositories.ComuniRepository;
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

    public Comune save(NewComuniDTO body) {
        Provincia provinciaFound = this.provinceService.findByNomeIgnoreCase(body.nomeProvincia());
        if (this.repository.existsByNomeAndProvinciaNome(body.nome(), provinciaFound.getNome())) throw new BadRequestException("Esiste già un comune chiamato " + body.nome() + " nella provincia di " + provinciaFound.getNome());
        if (this.repository.existsByProgressivoProvinciaAndProgressivoComune(body.progressivoProvincia(), body.progressivoComune())) throw new BadRequestException("Esiste già un comune con codice progressivo " + body.progressivoComune() + " nella provincia di " + provinciaFound.getNome());
        if (body.progressivoComune() == null) {
            int newProgressivoComune = Integer.parseInt(this.repository.findMaxProgressivoProvincia(body.progressivoProvincia())) + 1;
            return this.repository.save(new Comune(body.progressivoProvincia(), String.valueOf(newProgressivoComune), body.nome(), provinciaFound));
        } else {
            return this.repository.save(new Comune(body.progressivoProvincia(), body.progressivoComune(), body.nome(), provinciaFound));
        }
    }

    public Page<Comune> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.repository.findAll(pageable);
    }

    public Comune findByIdAndUpdate(UUID id, Comune body) {
        Comune found = this.findById(id);
        if (this.repository.existsByNomeAndProvinciaNome(body.getNome(), body.getProvincia().getNome()) && !found.getIdComune().equals(body.getIdComune())) throw new BadRequestException("Esista già un comune chiamato " + body.getNome() + " nella provincia di " + body.getProvincia().getNome());
        found.setNome(body.getNome());
        found.setProgressivoProvincia(body.getProgressivoProvincia());
        found.setProgressivoComune(body.getProgressivoComune());
        found.setProvincia(body.getProvincia());
        return this.repository.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        this.repository.delete(this.findById(id));
    }


    public void importData(String csvFilePath) {
        List<Comune> comuniList = new ArrayList<>();

        try (BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath))) {
            String currentLine;
            lineReader.readLine(); // Legge le righe

            while ((currentLine = lineReader.readLine()) != null) {
                String[] data = currentLine.split(";"); // Crea un array di stringhe per ogni linea del csv
                Provincia provinciaFound = provinceService.findByNomeIgnoreCase(data[3]);
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
