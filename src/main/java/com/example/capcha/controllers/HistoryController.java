package com.example.capcha.controllers;

import com.example.capcha.history.History;
import com.example.capcha.repository.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @GetMapping("/history")
    public String history(Model model){
        History history = new History("http://localhost:8080/history", new Timestamp(System.currentTimeMillis()));
        userHistoryRepository.save(history);

        List<History> historyList = userHistoryRepository.findAll();
        model.addAttribute("history", historyList);
        return "userHistory";
    }
}
