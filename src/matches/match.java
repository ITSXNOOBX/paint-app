package matches;

import java.io.Serializable;
import java.util.ArrayList;

import teams.team;

public class match implements Serializable {
		private team local;
		private team outsider;
		
		private Double localPoints;
		private Double outsiderPoints;
		
		private ArrayList<score> scoreList;
		
		public match(team local, Double localPoints, team outsider, Double outsiderPoints, ArrayList<score> scoreList) {
			this.local = local;
			this.localPoints = localPoints;
			
			this.outsider = outsider;
			this.outsiderPoints = outsiderPoints;
			
			this.scoreList = scoreList;
		}

		public team getLocal() {
			return local;
		}

		public void setLocal(team local) {
			this.local = local;
		}

		public team getOutsider() {
			return outsider;
		}

		public void setOutsider(team outsider) {
			this.outsider = outsider;
		}

		public Double getLocalPoints() {
			return localPoints;
		}

		public void setLocalPoints(Double localPoints) {
			this.localPoints = localPoints;
		}

		public Double getOutsiderPoints() {
			return outsiderPoints;
		}

		public void setOutsiderPoints(Double outsiderPoints) {
			this.outsiderPoints = outsiderPoints;
		}

		public ArrayList<score> getScoreList() {
			return scoreList;
		}

		public void setScoreList(ArrayList<score> scoreList) {
			this.scoreList = scoreList;
		}
		
		public team getWinner() {
			return localPoints > outsiderPoints ? local : outsider;
		}
		
		public team getLooser() {
			return localPoints < outsiderPoints ? local : outsider;
		}

		@Override
		public String toString() {
			return "Local team:  " + local + " scored " + localPoints + " vs Outsider team: " + outsider + " scored " + outsiderPoints + "; Winner: " + getWinner();
		}
}
