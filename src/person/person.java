package person;

import java.io.Serializable;
import java.util.Objects;

/*
 * @author t5
 * @version 1.0.5
 */
public class person implements Serializable {
    private String name;
    private String[] surnames;
    private String nan;
    private Integer age;


    public person() {
        this.name = "Unknown";
        this.surnames = new String[]{"Unknown"};
        this.nan = "Unknown";
        this.age = -1;
    }
    public person(String name, String[] surnames, String nan, Integer age) {
        this.name = name;
        this.surnames = surnames;
        this.nan = nan;
        this.age = age;
    }

    public person(person other) {
        this.name = other.name;
        this.surnames = other.surnames;
        this.nan = other.nan;
        this.age = other.age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSurnames() {
        return surnames;
    }

    public void setSurnames(String[] surnames) {
        this.surnames = surnames;
    }

    public String getNan() {
        return nan;
    }

    public void setNan(String nan) {
        this.nan = nan;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        person person = (person) o;

        return Objects.equals(nan, person.nan);
    }

    @Override
    public int hashCode() {
        return nan != null ? nan.hashCode() : 0;
    }
    
    @Override
	public String toString() {
		return name;
	}
}
