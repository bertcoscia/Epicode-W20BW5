package bertcoscia.Epicode_W20BW5.services;

import bertcoscia.Epicode_W20BW5.entities.Cliente;
import bertcoscia.Epicode_W20BW5.entities.Ruolo;
import bertcoscia.Epicode_W20BW5.entities.User;
import bertcoscia.Epicode_W20BW5.exceptions.BadRequestException;
import bertcoscia.Epicode_W20BW5.exceptions.NotFoundException;
import bertcoscia.Epicode_W20BW5.payloads.UserDTO;
import bertcoscia.Epicode_W20BW5.payloads.UserUpRuoloDTO;
import bertcoscia.Epicode_W20BW5.repositories.ClientiRepository;
import bertcoscia.Epicode_W20BW5.repositories.RuoloRepository;
import bertcoscia.Epicode_W20BW5.repositories.UsersRepository;
import bertcoscia.Epicode_W20BW5.tools.MailgunSender;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private RuoloRepository ruoloRepository;
    @Autowired
    private MailgunSender mailgunSender;
    @Autowired
    private ClientiRepository clientiRepository;

    public User saveUser(UserDTO body) {
        if (body == null) {
            throw new BadRequestException("L'email deve avere un body!");
        } else if (this.usersRepository.existsByEmail(body.email())) {
            throw new BadRequestException("L'email " + body.email() + " è già in uso!");
        } else {
            Ruolo ruolo = ruoloRepository.findByNome("USER");
            User user = new User(body.username(), body.email(), bcrypt.encode(body.password()), body.nome(),
                    body.cognome(), "https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome(),
                    Set.of(ruolo));
            return this.usersRepository.save(user);
        }
    }

    public User findById(UUID userId) {
        return this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String email) {
        return usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));
    }

    public Page<User> findAll(int page, int size, String sortBy) {
        if (page > 20) page = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.usersRepository.findAll(pageable);
    }

    public void findByIdAndDelete(UUID userId) {
        User found = findById(userId);
        this.usersRepository.delete(found);
    }

    public User findByIdAndUpdate(UUID userId, UserDTO updateBody) {
        User found = findById(userId);
        if (this.usersRepository.existsByEmail(updateBody.email()) && !found.getEmail().equals(updateBody.email())) {
            throw new BadRequestException("L'email " + updateBody.email() + " è già in uso!");
        } else {
            found.setNome(updateBody.nome());
            found.setCognome(updateBody.cognome());
            found.setEmail(updateBody.email());
            found.setPassword(updateBody.password());
            return usersRepository.save(found);
        }
    }

    public User uploadImage(UUID dipendenteId, MultipartFile file) throws IOException {
        User dipendente = findById(dipendenteId);
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        dipendente.setAvatarUrl(url);
        return usersRepository.save(dipendente);
    }

    public User updateRuolo(UUID userId, UserUpRuoloDTO nomeRuolo) {
        User user = findById(userId);
        Ruolo ruolo = ruoloRepository.findByNome(nomeRuolo.nomeRuolo().toUpperCase());
        if (ruolo == null) {
            throw new BadRequestException("Ruolo non trovato: " + nomeRuolo);
        }
        if (user.getRuoli() == null) {
            user.setRuoli(new HashSet<>());
        }
        user.getRuoli().add(ruolo);
        return usersRepository.save(user);
    }

    public String sendEmailToClient(UUID clienteId, String subject, String body) {
        Cliente client = clientiRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Client con id " + clienteId + " non trovato"));

        mailgunSender.sendEmail(client, subject, body);
        return "Email inviata a " + client.getEmail();
    }
}


