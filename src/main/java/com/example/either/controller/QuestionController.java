package com.example.either.controller;

import com.example.either.dto.AnswerDTO;
import com.example.either.dto.QuestionDTO;
import com.example.either.entity.Answer;
import com.example.either.entity.Question;
import com.example.either.service.AnswerService;
import com.example.either.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;

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
    public String createQuestion(@ModelAttribute Question question, RedirectAttributes redirectAttributes) {
        questionService.createQuestion(question);
        redirectAttributes.addFlashAttribute("message","질문이 등록되었습니다.");
        return "redirect:/questions";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        List<Answer> answers = answerService.getAnswers(id);
        Long countA = answerService.countByAnswerTextAndQuestion("A", question);
        Long countB = answerService.countByAnswerTextAndQuestion("B", question);
        Long sum = countA + countB;
        model.addAttribute("percentA", Math.round((double) countA/sum*1000.0)/10.0);
        model.addAttribute("percentB", Math.round((double) countB/sum*1000.0)/10.0);
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);
        return "detail";
    }

    @PostMapping("/{questionId}/answers")
    public String addAnswer(@PathVariable Long questionId, @ModelAttribute Answer answer, RedirectAttributes redirectAttributes) {
        answerService.createAnswer(answer, questionId);
        redirectAttributes.addFlashAttribute("message","답변이 등록되었습니다.");
        return "redirect:/questions/" + questionId;
    }

}
