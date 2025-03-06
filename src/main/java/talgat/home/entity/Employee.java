package talgat.home.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "please fill email")
    @Column(name = "email_id", nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Person person;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "company_id")
    private Company company;
}
