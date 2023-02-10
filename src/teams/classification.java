package teams;

/*
 * @author t5
 * @version 1.1.2
 * @see team class
 */
public class classification extends team {
	
	public classification(team t){
		super(t);
	}
	
	public String toString() {
		return "(" +super.getKlasifikation() +") " + super.getName() + ", points: " + super.getPoints();
	}
}
