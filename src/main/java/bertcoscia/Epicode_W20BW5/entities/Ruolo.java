package bertcoscia.Epicode_W20BW5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
public class Ruolo {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    @ManyToMany(mappedBy = "ruoli")
    private Set<User> users = new HashSet<>();
}
