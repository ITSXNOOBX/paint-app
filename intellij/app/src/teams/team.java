package teams;

import person.coach;
import person.player;

import java.util.ArrayList;
import java.util.Objects;

public class team {
    private String name;
    private String code;
    private Integer klasifikation;
    private Integer points;
    private ArrayList<player> players;
    private coach coach;


    public team() {
        this.name = "";
        this.code = "";
        this.klasifikation = 0;
        this.points = 0;
        this.players = new ArrayList<player>();
        this.coach = null;
    }

    public team(String name, String code, Integer klasifikation, Integer points, ArrayList<player> players, coach coach) {
        this.name = name;
        this.code = code;
        this.klasifikation = klasifikation;
        this.points = points;
        this.players = players;
        this.coach = coach;
    }

    public team(team other) {
        this.name = other.name;
        this.code = other.code;
        this.klasifikation = other.klasifikation;
        this.points = other.points;
        this.players = new ArrayList<player>(other.players);
        this.coach = other.coach;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getKlasifikation() {
        return klasifikation;
    }

    public void setKlasifikation(Integer klasifikation) {
        this.klasifikation = klasifikation;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public ArrayList<player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<player> players) {
        this.players = players;
    }

    public person.coach getCoach() {
        return coach;
    }

    public void setCoach(person.coach coach) {
        this.coach = coach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        team team = (team) o;

        if (!Objects.equals(name, team.name)) return false;
        return Objects.equals(code, team.code);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
