package br.com.app.cleanarchitecture.infrastructure.dataprovider.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String username;

    @NotBlank
    @Column
    private String email;

    @NotBlank
    @Column
    private String password;

    @NotBlank
    @Column
    private String name;

    @Column
    protected String role;

    @Column
    private boolean enabled;

}
