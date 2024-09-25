package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Clienti;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.payloads.NewClienteDTO;
import bertcoscia.Epicode_W20BW5.repositories.ClientiRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ClientiService {

    @Autowired
    private ClientiRepository clientiRepository;
    @Autowired
    private Cloudinary cloudinaryUploader;

    //Find All
    public List<Clienti> findAllClienti() {
        return clientiRepository.findAll();
    }

    // Find by ID
    public Clienti findClienteById(UUID clienteId) {
        return this.clientiRepository.findById(clienteId).orElseThrow(() -> new NotFoundException(clienteId));
    }

    // DELETE
    public void findByClienteIdAndDelete(UUID clienteId) {
        Clienti found = this.findClienteById(clienteId);
        this.clientiRepository.delete(found);
    }

    //Salvataggio
    public Clienti saveCliente(NewClienteDTO body) {
        this.clientiRepository.findByEmail(body.email()).ifPresent(
                dipendente -> {
                    try {
                        throw new BadRequestException("L'email " + body.email() + "è già in uso!");
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        Clienti newCliente = new Clienti(body.cognome(), body.dataInserimento(), body.dataUltimoContatto(), body.email(), body.emailContatto(),
                body.fatturatoAnnuale(), body.indirizzi(), body.logoAziendale(), body.nomeContatto(), body.nomeSocieta(), body.partitaIva(), body.pec(), body.sedeLegale(), body.sedeOperativa(), body.telefono(), body.telefonoContatto(), body.tipoCliente());
        Clienti savedCliente = this.clientiRepository.save(newCliente);
        return savedCliente;
    }

    //Modifica
    public Clienti findByClienteIdAndUpdate(UUID clienteId, Clienti newClienteData) {
        // 4.1 Se la mail è già presente
        this.clientiRepository.findByEmail(newClienteData.getEmail()).ifPresent(
                dipendente -> {
                    try {
                        throw new BadRequestException("L'email " + newClienteData.getEmail() + "è già in uso");
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        Clienti foundCliente = this.findClienteById(clienteId);
        foundCliente.setCognome(newClienteData.getCognome());
        foundCliente.setDataInserimento(newClienteData.getDataInserimento());
        foundCliente.setDataUltimoContatto(newClienteData.getDataUltimoContatto());
        foundCliente.setEmail(newClienteData.getEmail());
        foundCliente.setEmailContatto(newClienteData.getEmailContatto());
        foundCliente.setFatturatoAnnuale(newClienteData.getFatturatoAnnuale());
        foundCliente.setIndirizzi(newClienteData.getIndirizzi());
        foundCliente.setLogoAziendale(newClienteData.getLogoAziendale());
        foundCliente.setNomeContatto(newClienteData.getNomeContatto());
        foundCliente.setNomeSocieta(newClienteData.getNomeSocieta());
        foundCliente.setPartitaIva(newClienteData.getPartitaIva());
        foundCliente.setPec(newClienteData.getPec());
        foundCliente.setSedeLegale(newClienteData.getSedeLegale());
        foundCliente.setSedeOperativa(newClienteData.getSedeOperativa());
        foundCliente.setTelefono(newClienteData.getTelefono());
        foundCliente.setTelefonoContatto(newClienteData.getTelefonoContatto());
        foundCliente.setTipoCliente(newClienteData.getTipoCliente());
        return this.clientiRepository.save(foundCliente);
    }

    //Find Email
    public Clienti findByEmail(String email) {
        return clientiRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }

    // IMG CLOUDINARY
    public void uploadImg(MultipartFile file) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        System.out.println("URL: " + url);
    }

}

