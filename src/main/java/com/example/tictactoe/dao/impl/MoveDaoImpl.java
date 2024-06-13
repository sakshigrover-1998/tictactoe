package com.example.tictactoe.dao.impl;

import com.example.tictactoe.dao.MoveDao;
import com.example.tictactoe.entity.Move;
import com.example.tictactoe.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MoveDaoImpl implements MoveDao {

    @Autowired
    private MoveRepository moveRepository;

    @Override
    public Move findById(Long id) {
        return moveRepository.findById(id).orElse(null);
    }

    @Override
    public Move save(Move move) {
        return moveRepository.save(move);
    }

    @Override
    public void deleteById(Long id) {
        moveRepository.deleteById(id);
    }
}