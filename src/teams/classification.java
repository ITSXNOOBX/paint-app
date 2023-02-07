package teams;

public class classification extends team {
	
	public classification(team t){
		super(t);
	}
	
	public String toString() {
		return "(" +super.getKlasifikation() +") " + super.getName() + ", points: " + super.getPoints();
	}
}
