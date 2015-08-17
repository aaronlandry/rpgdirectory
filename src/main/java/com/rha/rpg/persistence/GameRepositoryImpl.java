package com.rha.rpg.persistence;

import com.rha.rpg.model.Game;

/**
 * Implementation of GameRepository.
 * @author Aaron
 */
@org.springframework.stereotype.Repository
public class GameRepositoryImpl extends AbstractRepository<Long,Game> implements GameRepository {
    
}