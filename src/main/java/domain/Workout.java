package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Workout extends DomainEntity{

	//Atributtes

	private String				title;
	private String				description;
	private List<Step>			steps;
	private List<Annotation>	annotations;
	
	//Getters
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	
	@NotNull
	@OneToMany
	public List<Step> getSteps() {
		return steps;
	}
	
	@NotNull
	@OneToMany
	public List<Annotation> getAnnotations() {
		return annotations;
	}
	
	//Setters
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
	
	

}
