package com.salah.ask.model.ask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.salah.ask.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "post")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable {

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

    @Past
    @Column(name = "date")
    private Timestamp date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

}
