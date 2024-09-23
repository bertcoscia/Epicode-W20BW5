package bertcoscia.Epicode_W20BW5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String nome;
    private String cognome;
    private String avatarUrl;
    @ManyToMany
    @JoinTable(name = "user_ruolo", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    private Set<Ruolo> ruoli = new HashSet<>();

    public User(String username, String email, String password, String nome, String cognome, String avatarUrl) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.avatarUrl = avatarUrl;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ruoli.stream().map(ruolo -> new SimpleGrantedAuthority(ruolo.getNome())).collect(Collectors.toList());
    }
}
