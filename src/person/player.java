package person;

import java.io.Serializable;
import java.util.Objects;

public class player extends person implements Serializable {
    private Integer team_number;
    private Double points;
    private Boolean headline; // If the player will be playing in the first half or not

    public player() {
        super();
        this.team_number = -1;
        this.points = -1.;
        this.headline = false;
    }

    public player(Integer team_number, Double points, Boolean headline) {
        super();
        this.team_number = team_number;
        this.points = points;
        this.headline = headline;
    }

    public player(String name, String[] surnames, String nan, Integer age, Integer team_number, Double points, Boolean headline) {
        super(name, surnames, nan, age);
        this.team_number = team_number;
        this.points = points;
        this.headline = headline;
    }

    public player(player other) {
        super(other);
        this.team_number = other.team_number;
        this.points = other.points;
        this.headline = other.headline;
    }

    public Integer getTeam_number() {
        return team_number;
    }

    public void setTeam_number(Integer team_number) {
        this.team_number = team_number;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Boolean getHeadline() {
        return headline;
    }

    public void setHeadline(Boolean headline) {
        this.headline = headline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        player player = (player) o;

        if (!Objects.equals(team_number, player.team_number)) return false;
        if (!super.equals(player)) return false; // Custom check to check if the nan is the same
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (team_number != null ? team_number.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + (headline != null ? headline.hashCode() : 0);
        return result;
    }
    
}
