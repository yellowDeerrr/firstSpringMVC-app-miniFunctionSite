package com.example.capcha.repository;

import com.example.capcha.history.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<History, Long> {
}
