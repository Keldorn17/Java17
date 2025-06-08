package com.keldorn;

import com.keldorn.dto.Affiliation;
import com.keldorn.dto.BaseballPlayer;
import com.keldorn.dto.FootballPlayer;
import com.keldorn.model.location.*;
import com.keldorn.model.team.Team;

public class Main {
    public static void main(String[] args) {
        baseballTest();
        locationTest();
    }

    private static void separator() {
        System.out.println("-".repeat(30));
    }

    private static void baseballTest() {
        separator();
        var philly = new Affiliation("city", "Philadelphia, PA", "US");
        Team<BaseballPlayer, Affiliation> phillies = new Team<>("Philadelphia Phillies", philly);
        Team<BaseballPlayer, Affiliation> astro = new Team<>("Houston Astros");
        scoreResult(phillies, 3, astro, 4);

        var harper = new BaseballPlayer("B Harper", "Right Fielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
        var tex = new FootballPlayer("Tex Walker", "Centre half forward");
        phillies.addTeamMember(harper);
        phillies.addTeamMember(marsh);
//        phillies.addTeamMember(tex);
        phillies.listTeamMembers();
    }

    private static void scoreResult(Team team1, int t1_score, Team team2, int t2_score) {
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    private static void locationTest() {
        separator();
        Mappable grandCanyon = new Park("Grand Canyon", new Location(40.1021, -75.4231));
        Mappable yellowstone = new Park("Yellowstone", new Location(44.4882, -110.5916));
        Mappable missouri = new River("Missouri", new Location(45.9239, -111.4983),
                new Location(38.8146, -90.1218));

        Layer<Mappable> layers = new Layer<>(grandCanyon, yellowstone);
        layers.addElement(missouri);
        layers.renderLayer();
    }
}
