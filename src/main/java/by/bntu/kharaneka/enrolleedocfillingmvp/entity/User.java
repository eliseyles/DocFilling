package by.bntu.kharaneka.enrolleedocfillingmvp.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_info_id", referencedColumnName = "id")
    private Contract contract;
}
