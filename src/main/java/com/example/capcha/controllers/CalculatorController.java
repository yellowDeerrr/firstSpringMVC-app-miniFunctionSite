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

@Controller
public class CalculatorController {
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @GetMapping("/calculator")
    public String calculator(Model model){
        History history = new History("http://localhost:8080/convert", new Timestamp(System.currentTimeMillis()));
        userHistoryRepository.save(history);
        return "calculator";
    }

    @PostMapping("/calculator")
    public String sum(@RequestParam int firstNum, @RequestParam int secondNum, @RequestParam String operator, Model model){
        int result = switch (operator) {
            case "add" -> firstNum + secondNum;
            case "subtract" -> firstNum - secondNum;
            case "multiply" -> firstNum * secondNum;
            case "divide" -> firstNum / secondNum;
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };

        model.addAttribute("result", result);
        return "calculator";
    }
}
