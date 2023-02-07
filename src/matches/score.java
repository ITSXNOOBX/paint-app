package matches;

import java.io.Serializable;

import person.player;
import teams.team;

public class score implements Serializable {
	private Integer local = 0; // null, 0 = local, 1 = Outsider
	private player scorer;
	private Double points;
	
	public score() {
		scorer = null;
		points=null;
	}
	
	public score(player ply, Double points) {
		this.scorer = ply;
		this.points = points;
	}
	
	public score(player ply, Double points, Boolean local) {
		this.scorer = ply;
		this.points = points;
		this.local = local ? 1 : 2;
	}
	
	public player getScorer() {
		return scorer;
	}
	public void setScorer(player scorer) {
		this.scorer = scorer;
	}
	public Double getPoints() {
		return points;
	}
	public void setPoints(Double points) {
		this.points = points;
	}
	public Boolean isLocal() {
		return local == 1;
	}
	
	@Override
	public String toString() {
		if (local == 2)
			return "O | " + scorer + " scored " + points + " points";
		else if (local == 1)
			return "L | " +scorer + " scored " + points + " points";
		
		return scorer + " scored " + points + " points";
	}
}
