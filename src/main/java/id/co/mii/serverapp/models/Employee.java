package id.co.mii.serverapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;
    @Column(name = "employee_name", length = 50, nullable = false)
    private String name;
    @Column(name = "employee_email", nullable = false)
    private String email;
    @Column(name = "employee_phone", length = 13)
    private String phone;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;
}
