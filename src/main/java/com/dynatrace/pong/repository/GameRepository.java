package com.dynatrace.pong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dynatrace.pong.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g.scorePlayer1 FROM Game g WHERE g.id = :gameId")
    int getPlayer1Score(@Param("gameId") Long gameId);

    @Query("SELECT g.scorePlayer2 FROM Game g WHERE g.id = :gameId")
    int getPlayer2Score(@Param("gameId") Long gameId);

    @Modifying
    @Query("UPDATE Game g SET g.scorePlayer1 = :newScore WHERE g.id = :gameId")
    void updatePlayer1Score(@Param("gameId") Long gameId, @Param("newScore") int newScore);

    @Modifying
    @Query("UPDATE Game g SET g.scorePlayer2 = :newScore WHERE g.id = :gameId")
    void updatePlayer2Score(@Param("gameId") Long gameId, @Param("newScore") int newScore);
}
    


