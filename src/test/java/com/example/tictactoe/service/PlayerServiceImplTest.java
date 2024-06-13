package com.example.tictactoe.service;

import com.example.tictactoe.dao.PlayerDao;
import com.example.tictactoe.dto.PlayerDto;
import com.example.tictactoe.entity.Player;
import com.example.tictactoe.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {

    @Mock
    private PlayerDao playerDAO;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private Player player;
    private PlayerDto playerDto;

    @BeforeEach
    public void setUp() {
        player = new Player();
        player.setName("Test Player");


        playerDto = new PlayerDto();
        playerDto.setName("Test Player");
    }

    @Test
    public void createPlayer_ShouldReturnPlayerDto() {
        // Arrange
        when(playerDAO.save(any(Player.class))).thenReturn(player);
        when(modelMapper.map(any(Player.class), eq(PlayerDto.class))).thenReturn(playerDto);

        // Act
        PlayerDto result = playerService.createPlayer("Test Player");

        // Assert
        assertEquals(playerDto.getName(), result.getName());
        verify(playerDAO, times(1)).save(any(Player.class));
        verify(modelMapper, times(1)).map(any(Player.class), eq(PlayerDto.class));
    }
}
