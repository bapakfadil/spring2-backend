package id.co.mii.serverapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data // Otomatisasi penerapan Setter Getter oleh Lombok
@AllArgsConstructor // Otomatisasi penulisan semua Args constructor oleh Lombok
@NoArgsConstructor // Otomatisasi penulisan NoArgs pada constructor oleh Lombok
@Entity
@Table(name = "tb_region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer id;

    @Column(name = "region_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "region") // Menambah relasi OneToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Json untuk fix StackOverflowError
    private List<Country> countries;
}