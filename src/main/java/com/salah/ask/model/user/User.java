package com.salah.ask.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salah.ask.model.ask.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "`User`",
        indexes = @Index(name = "idx_user_email", columnList = "email", unique = true))
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Collection<Role> roles;

    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Friendship> friends;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Post> posts;

    public void addRole (Role role){
        if(roles == null){
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    public String getFullName() {
        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    }
}
