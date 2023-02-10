package auth;

import java.io.Serializable;
import java.util.Objects;

public class user implements Serializable {
	private String username;
	private String email;
	private String haxPassword;
	
	private Integer rol;
	
	public user() {
		this.username = null;
		this.email = null;
		this.haxPassword = null;
		this.rol = null;
	}
	
	public user(String usr, String email, String pwd) {
		this.username = usr;
		this.email = email;
		this.haxPassword = pwd;
		this.rol = -1;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHaxPassword() {
		return haxPassword;
	}

	public void setHaxPassword(String haxPassword) {
		this.haxPassword = haxPassword;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		user other = (user) obj;
		return Objects.equals(email, other.email) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return username;
	}
	
	

}
