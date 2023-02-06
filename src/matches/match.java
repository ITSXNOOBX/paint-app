package matches;

import java.util.ArrayList;

import teams.team;

public class match {
		private team local;
		private team outsider;
		
		private Double localPoints;
		private Double outsiderPoints;
		
		private ArrayList<score> scoreList = new ArrayList<score>();

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

		@Override
		public String toString() {
			return "L: " + local + " scored " + localPoints + ", O: " + outsider + " scored " + outsiderPoints;
		}
}
