package com.example.either.controller;

import com.example.either.dto.QuestionDTO;
import com.example.either.entity.Question;
import com.example.either.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public String list(Model model) {
        List<Question> questions = questionService.findAllQuestions();
        model.addAttribute("questions", questions);
        return "list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("question", new QuestionDTO());
        return "form";
    }

    @PostMapping
    public String createQuestion(@ModelAttribute Question question) {
        questionService.createQuestion(question);
        return "list";
    }

}
