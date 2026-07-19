package com.cognizant.ormlearn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "attempt_option")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attempt_option_id")
    private Integer attemptOptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_question_id", nullable = false)
    @ToString.Exclude
    private AttemptQuestion attemptQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;
}
