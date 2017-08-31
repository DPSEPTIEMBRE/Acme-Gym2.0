package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Advice extends DomainEntity{

	//Atributtes

	private String				text;
	private String				nick;
	private Step				step;
	private Actor				actor;
	
	//Getters
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Actor getActor() {
		return actor;
	}

	@NotBlank
	public String getText() {
		return text;
	}
	
	@NotBlank
	public String getNick() {
		return nick;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Step getStep() {
		return step;
	}
	
	//Setters
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public void setStep(Step step) {
		this.step = step;
	}
	
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	


}
