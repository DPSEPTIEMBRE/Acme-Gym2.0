package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity{

	//Atributtes

	private String					nick;
	private String					link;
	private String					nameNetwork;
	
	//Getters
	
	@NotBlank
	public String getNick() {
		return nick;
	}
	
	@URL
	public String getLink() {
		return link;
	}
	
	@NotBlank
	public String getNameNetwork() {
		return nameNetwork;
	}
	
	//Setters
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setNameNetwork(String nameNetwork) {
		this.nameNetwork = nameNetwork;
	}
	
	

}
