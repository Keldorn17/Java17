package com.keldorn.dto;

import com.keldorn.model.team.Player;

public record BaseballPlayer(String name, String position) implements Player {
}
