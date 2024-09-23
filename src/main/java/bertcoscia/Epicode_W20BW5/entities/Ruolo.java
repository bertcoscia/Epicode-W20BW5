package bertcoscia.Epicode_W20BW5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ruoli_permessi",
            joinColumns = @JoinColumn(name = "ruolo_id"),
            inverseJoinColumns = @JoinColumn(name = "permesso_id")
    )
    private List<Permesso> permessi = new ArrayList<>();
};
