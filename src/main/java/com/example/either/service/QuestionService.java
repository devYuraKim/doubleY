package com.example.either.service;

import com.example.either.entity.Question;
import com.example.either.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    public List<Question> findAllQuestions() {
        System.out.println(questionRepository.findAll());
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id){
        return questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Question Not Found"));
    }
}
