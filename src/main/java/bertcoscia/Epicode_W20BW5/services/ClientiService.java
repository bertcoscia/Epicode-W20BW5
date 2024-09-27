package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Cliente;
import bertcoscia.Epicode_W20BW5.entities.Indirizzo;
import bertcoscia.Epicode_W20BW5.enums.TipoCliente;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.payloads.NewClienteDTO;
import bertcoscia.Epicode_W20BW5.repositories.ClientiRepository;
import bertcoscia.Epicode_W20BW5.specs.ClientiSpecs;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Service
public class ClientiService {

    @Autowired
    private ClientiRepository clientiRepository;
    @Autowired
    private Cloudinary cloudinaryUploader;
    @Autowired
    private IndirizziService indirizziService;

    //Find All
    public Page<Cliente> findAllClienti(int page, int size, String sortBy, Sort.Direction direction, Map<String, String> params) {
        if (page > 100) page = 100;
        Pageable pageable;
        if ("provincia".equalsIgnoreCase(sortBy)) {
            pageable = PageRequest.of(page, size, Sort.by(direction, "sedeLegale.comune.provincia.nome"));
        } else {
            pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        }
        Specification<Cliente> spec = Specification.where(null);
        if (params.containsKey("fatturato")) {
            Long fatturato = Long.valueOf(params.get("fatturato"));
            spec = spec.and(ClientiSpecs.hasFatturato(fatturato));
        }
        if (params.containsKey("dataInserimento")) {
            LocalDate dataInserimento = LocalDate.parse(params.get("dataInserimento"));
            spec = spec.and(ClientiSpecs.hasDataInserimento(dataInserimento));
        }
        if (params.containsKey("dataUltimoContatto")) {
            LocalDate dataUltimoContatto = LocalDate.parse(params.get("dataUltimoContatto"));
            spec = spec.and(ClientiSpecs.hasDataUltimoContatto(dataUltimoContatto));
        }
        if (params.containsKey("nome")) {
            String nome = params.get("nome");
            spec = spec.and(ClientiSpecs.hasNomeLike(nome));
        }
        return clientiRepository.findAll(spec, pageable);
    }

    // Find by ID
    public Cliente findClienteById(UUID clienteId) {
        return this.clientiRepository.findById(clienteId).orElseThrow(() -> new NotFoundException(clienteId));
    }

    // DELETE
    public void findByClienteIdAndDelete(UUID clienteId) {
        Cliente found = this.findClienteById(clienteId);
        this.clientiRepository.delete(found);
    }

    //Salvataggio
    public Cliente saveCliente(NewClienteDTO body) throws BadRequestException {
        if (this.clientiRepository.existsByEmail(body.email()))
            throw new BadRequestException("Esiste già un cliente con email " + body.email());
        if (this.clientiRepository.existsByPartitaIva(body.partitaIva()))
            throw new BadRequestException("Esiste già un cliente con partita IVA " + body.partitaIva());
        if (this.clientiRepository.existsByPec(body.pec()))
            throw new BadRequestException("Esiste già un cliente con PEC " + body.pec());
        LocalDate dataInserimento = LocalDate.parse(body.dataInserimento());
        LocalDate dataUltimoContatto = LocalDate.parse(body.dataUltimoContatto());
        TipoCliente tipoCliente;
        if (body.tipoCliente().equalsIgnoreCase("PA")) {
            tipoCliente = TipoCliente.PA;
        } else if (body.tipoCliente().equalsIgnoreCase("SAS")) {
            tipoCliente = TipoCliente.SAS;
        } else if (body.tipoCliente().equalsIgnoreCase("SPA")) {
            tipoCliente = TipoCliente.SPA;
        } else if (body.tipoCliente().equalsIgnoreCase("SRL")) {
            tipoCliente = TipoCliente.SRL;
        } else {
            throw new BadRequestException("Tipo cliente non valido. Scegliere uno tra PA, SAS, SPA, SRL");
        }
        Indirizzo sedeLegaleFound = this.indirizziService.findById(UUID.fromString(body.sedeLegale()));
        Indirizzo sedeOperativaFound = this.indirizziService.findById(UUID.fromString(body.sedeOperativa()));
        return this.clientiRepository.save(new Cliente(body.nomeSocieta(), body.partitaIva(), body.email(), dataInserimento, dataUltimoContatto,
                body.fatturatoAnnuale(), body.pec(), body.telefono(), body.emailContatto(), body.nomeContatto(), body.cognome(), body.telefonoContatto(),
                body.logoAziendale(), tipoCliente, sedeLegaleFound, sedeOperativaFound));
    }

    //Modifica
    public Cliente findByClienteIdAndUpdate(UUID clienteId, Cliente body) throws BadRequestException {
        Cliente clienteFound = this.findClienteById(clienteId);
        if (!clienteFound.getId().equals(body.getId()) && this.clientiRepository.existsByEmail(body.getEmail()))
            throw new BadRequestException("Esiste già un cliente con email " + body.getEmail());
        if (!clienteFound.getId().equals(body.getId()) && this.clientiRepository.existsByPartitaIva(body.getPartitaIva()))
            throw new BadRequestException("Esiste già un cliente con partita IVA " + body.getPartitaIva());
        if (!clienteFound.getId().equals(body.getId()) && this.clientiRepository.existsByPec(body.getPec()))
            throw new BadRequestException("Esiste già un cliente con PEC " + body.getPec());
        clienteFound.setCognome(body.getCognome());
        clienteFound.setDataInserimento(body.getDataInserimento());
        clienteFound.setDataUltimoContatto(body.getDataUltimoContatto());
        clienteFound.setEmail(body.getEmail());
        clienteFound.setEmailContatto(body.getEmailContatto());
        clienteFound.setFatturatoAnnuale(body.getFatturatoAnnuale());
        clienteFound.setLogoAziendale(body.getLogoAziendale());
        clienteFound.setNomeContatto(body.getNomeContatto());
        clienteFound.setNomeSocieta(body.getNomeSocieta());
        clienteFound.setPartitaIva(body.getPartitaIva());
        clienteFound.setPec(body.getPec());
        Indirizzo sedeLegaleFound = this.indirizziService.findById(body.getSedeLegale().getIdIndirizzo());
        clienteFound.setSedeLegale(sedeLegaleFound);
        Indirizzo sedeOperativaFound = this.indirizziService.findById(body.getSedeOperativa().getIdIndirizzo());
        clienteFound.setSedeOperativa(sedeOperativaFound);
        clienteFound.setTelefono(body.getTelefono());
        clienteFound.setTelefonoContatto(body.getTelefonoContatto());
        clienteFound.setTipoCliente(body.getTipoCliente());
        return this.clientiRepository.save(clienteFound);
    }

    //Find Email
    public Cliente findByEmail(String email) {
        return clientiRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }

    // IMG CLOUDINARY
    public void uploadImg(MultipartFile file, UUID clienteId) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        System.out.println("URL: " + url);
        Cliente cliente = clientiRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente non trovato"));
        cliente.setLogoAziendale(url);
        clientiRepository.save(cliente);
    }

}

