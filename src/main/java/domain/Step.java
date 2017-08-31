package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Step extends DomainEntity {

	//Atributtes

	private String				videoTutorial;
	private String				description;
	private List<Advice>		advices;
	
	//Getters
	
	@NotNull
	@OneToMany
	public List<Advice> getAdvices() {
		return advices;
	}

	@URL
	public String getVideoTutorial() {
		return videoTutorial;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	
	//Setters
	
	public void setVideoTutorial(String videoTutorial) {
		this.videoTutorial = videoTutorial;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setAdvices(List<Advice> advices) {
		this.advices = advices;
	}

	

}
