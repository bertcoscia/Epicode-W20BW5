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
    /*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ruoli_permessi",
            joinColumns = @JoinColumn(name = "ruolo_id"),
            inverseJoinColumns = @JoinColumn(name = "permesso_id")
    )*/
    //private List<Permesso> permessi = new ArrayList<>();
}
