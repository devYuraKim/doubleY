package com.example.either.repository;

import com.example.either.entity.Answer;
import com.example.either.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
    Long countByAnswerTextAndQuestion(String answerText, Question question);
}
