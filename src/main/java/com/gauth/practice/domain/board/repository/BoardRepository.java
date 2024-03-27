package com.gauth.practice.domain.board.repository;

import com.gauth.practice.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
