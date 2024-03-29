package com.salah.ask.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@ToString
@Table(name = "role")
@JsonIgnoreProperties(ignoreUnknown = true)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", unique = true)
    @Enumerated(EnumType.STRING)
    @NotBlank
    private UserRoles role;

    public Role(@NotBlank UserRoles role) {
        this.role = role;
    }

}

