package com.example.tictactoe.service.impl;

import com.example.tictactoe.dao.GameDao;
import com.example.tictactoe.dto.GameDto;
import com.example.tictactoe.dto.MoveDto;
import com.example.tictactoe.entity.Game;
import com.example.tictactoe.exception.GameEndedException;
import com.example.tictactoe.exception.GameNotFound;
import com.example.tictactoe.exception.InvalidMoveException;
import com.example.tictactoe.model.PlayerType;
import com.example.tictactoe.model.Symbol;
import com.example.tictactoe.service.*;
import com.example.tictactoe.util.Board;
import com.example.tictactoe.util.BoardChecker;
import com.example.tictactoe.util.Constants;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.tictactoe.util.Constants.COMPUTER;
import static com.example.tictactoe.util.Constants.DRAW;



/**
 * Service implementation for managing Tic Tac Toe games.
 */
@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    private final GameDao gameDAO;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final ValidationService validationService;


    @Autowired
    private final ComputerMoveService computerMoveService;

    private final static String GAME_NOT_FOUND = "Game not found with id: ";

    private final static String INVALID_MOVE = "Invalid move: ";

    private static final String GAME_ENDED = "Game has already ended.";

    /**
     * Constructs a new instance of GameServiceImpl.
     *
     * @param validationService    The service for validating moves.
     * @param computerMoveService  The service for making computer moves.
     * @param modelMapper          The ModelMapper for mapping entities to DTOs.
     */

    @Autowired
    public GameServiceImpl(GameDao gameDAO, ValidationService validationService,
                           ComputerMoveService computerMoveService,
                           ModelMapper modelMapper) {
        this.gameDAO = gameDAO;
        this.validationService = validationService;
        this.computerMoveService = computerMoveService;
        this.modelMapper = modelMapper;
    }

    /**
     * Starts a new Tic Tac Toe game with the provided human player's name.
     *
     * @param humanName The name of the human player.
     * @return The DTO representing the newly created game.
     */

    @Override
    public GameDto startGame(String humanName) {
        char[][] board = Board.initializeBoard();
        PlayerType firstPlayer = Math.random() < 0.5 ? PlayerType.HUMAN : PlayerType.COMPUTER;

        Symbol humanSymbol = (Math.random() < 0.5) ? Symbol.X : Symbol.O;
        Symbol computerSymbol = (humanSymbol == Symbol.X) ? Symbol.O : Symbol.X;

        Game game = new Game();
        game.setCurrentPlayer(firstPlayer);
        game.setHumanSymbol(humanSymbol);
        game.setComputerSymbol(computerSymbol);
        game.setHumanName(humanName);
        game.setBoard(board);

        log.info("current player is {} and id {}", game.getCurrentPlayer(), game.getId());

        game = gameDAO.save(game);

        GameDto gameDto = modelMapper.map(game, GameDto.class);

        if (firstPlayer == PlayerType.COMPUTER) {
            MoveDto computerMove = computerMoveService.makeComputerMove(gameDto);
            makeMove(computerMove, game.getId());
        }

        return gameDto;
    }

    /**
     * Makes a move in the Tic Tac Toe game.
     *
     * @param move   The DTO representing the move to be made.
     * @param gameId The ID of the game in which the move is made.
     * @return The DTO representing the updated game state after the move.
     */

    @Override
    public GameDto makeMove(MoveDto move, Long gameId) {
        Game game = gameDAO.findById(gameId);
        if (game == null) {
            throw new GameNotFound(GAME_NOT_FOUND + gameId);
        }

//        if (game.isOver()) {
//            throw new GameEndedException(GAME_ENDED);
//        }

        log.info("Move after finding game: {}", move.getColumn());

        if (!validationService.isValidMove(move, modelMapper.map(game, GameDto.class))) {
            throw new InvalidMoveException(INVALID_MOVE + move);
        }


        Symbol symbol = (game.getCurrentPlayer() == PlayerType.HUMAN) ? game.getHumanSymbol() : game.getComputerSymbol();
        game.getBoard()[move.getRow()][move.getColumn()] = symbol.getCharValue();

        if (BoardChecker.isWinningMove(game.getBoard(), symbol, move.getRow(), move.getColumn())) {
            game.setWinner(game.getCurrentPlayer() == PlayerType.HUMAN
                    ? game.getHumanName() : COMPUTER);
            game.setOver(true);
        } else if (BoardChecker.isDraw(game.getBoard())) {
            game.setOver(true);
            game.setWinner(DRAW);
        } else {
            game.setCurrentPlayer(game.getCurrentPlayer() == PlayerType.HUMAN ? PlayerType.COMPUTER : PlayerType.HUMAN);
            if (game.getCurrentPlayer() == PlayerType.COMPUTER) {
                MoveDto computerMove = computerMoveService.makeComputerMove(modelMapper.map(game, GameDto.class));
                makeMove(computerMove, game.getId());
            }
        }
        game = gameDAO.save(game);

        return modelMapper.map(game, GameDto.class);
    }

    /**
     * Retrieves the full details of a Tic Tac Toe game.
     *
     * @param gameId The ID of the game to retrieve.
     * @return The DTO representing the full details of the game.
     */

    @Override
    public GameDto getFullGameDetails(Long gameId) {
        Game game = gameDAO.findById(gameId);
        if (game == null) {
            throw new EntityNotFoundException("Game not found with id: " + gameId);
        }
        return modelMapper.map(game, GameDto.class);
    }
}