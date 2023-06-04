package com.example.capcha.controllers;

import com.example.capcha.history.History;
import com.example.capcha.repository.UserHistoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

@Controller
public class ConvertController {
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @GetMapping("/convert")
    public String convert(Model model){
        History history = new History("http://localhost:8080/convert", new Timestamp(System.currentTimeMillis()));
        userHistoryRepository.save(history);
        return "convert";
    }

    @PostMapping("/convert")
    public String SHA(@RequestParam String userInputString, Model model){
        String SHA = getSHA256Hash(userInputString);
        model.addAttribute("SHA", SHA);
        return "convert";
    }

    public static String getSHA256Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hexadecimal string representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
