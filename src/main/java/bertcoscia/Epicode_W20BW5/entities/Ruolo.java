package bertcoscia.Epicode_W20BW5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ruoli")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ruolo {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    @ManyToMany(mappedBy = "ruoli")
    private Set<User> users = new HashSet<>();

    public Ruolo(String nome) {
        this.nome = nome;
    }
}
