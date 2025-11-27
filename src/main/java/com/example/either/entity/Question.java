package com.example.either.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, length = 200)
    private String optionA;
    @Column(nullable = false, length = 200)
    private String optionB;

    public Question(String title, String optionA, String optionB) {
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
    }

    @OneToMany(mappedBy = "question",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Answer> answers;
}
