package com.salah.ask.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@ToString
@Table(name = "role")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private UserRoles role;

    public Role(@NotBlank UserRoles role) {
        this.role = role;
    }

}

