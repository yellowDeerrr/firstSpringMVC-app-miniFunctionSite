package com.example.capcha.controllers;

import com.example.capcha.history.History;
import com.example.capcha.repository.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Random;

@Controller
public class CaptchaController {
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    private String captcha;

    @GetMapping("/")
    public String choose(){
        return "choose";
    }

    @GetMapping("/captcha")
    public String firstPage(Model model) {
        History history = new History("http://localhost:8080/captcha", new Timestamp(System.currentTimeMillis()));
        userHistoryRepository.save(history);
        generateCaptcha();
        model.addAttribute("captcha", captcha);
        return "main";
    }

    @PostMapping("/captcha")
    public String res(@RequestParam String answer, Model model) {
        if (answer.equals(captcha)) {
            String res = "successful";
            model.addAttribute("res", res);
        } else {
            String res = "unsuccessful";
            model.addAttribute("res", res);
        }
        generateCaptcha();
        model.addAttribute("captcha", captcha); // Додати значення captcha до моделі
        return "main";
    }

    private void generateCaptcha(){
        int iterations = 5; // Кількість ітерацій

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < iterations; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a'); // random char form 'a' by 'z'
            stringBuilder.append(randomChar);
        }
        captcha = stringBuilder.toString();
    }
}
