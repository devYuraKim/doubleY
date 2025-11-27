package com.example.either.service;

import com.example.either.entity.Answer;
import com.example.either.entity.Question;
import com.example.either.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public void createAnswer(Answer answer, Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        answer.setQuestion(question);
        answerRepository.save(answer);
    }

    public List<Answer> getAnswers(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public Long countByAnswerTextAndQuestion(String answerText, Question question) {
        return answerRepository.countByAnswerTextAndQuestion(answerText, question);
    }
}
