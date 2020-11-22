package com.salah.ask.model.ask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salah.ask.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "post")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question")
    @NotNull
    private String question;

    @Column(name = "answer")
    @NotNull
    private String answer;

    @Column(name = "likes")
    @NotNull
    private int likes;

    @Column(name = "is_answered")
    private boolean isAnswered;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

}
