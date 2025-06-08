package com.keldorn.dto;

import com.keldorn.model.team.Player;

public record FootballPlayer(String name, String position) implements Player {
}
