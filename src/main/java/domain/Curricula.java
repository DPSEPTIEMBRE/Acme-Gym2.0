package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Curricula extends DomainEntity{
	
	//Atributtes

	private String					nameTrainer;
	private List<String>			specialities;
	private String					education;
	private String					workExperience;
	private List<SocialIdentity>	socialIdentitys;
	
	//Getters
	
	@NotBlank
	public String getNameTrainer() {
		return nameTrainer;
	}
	
	@NotEmpty
	@ElementCollection(targetClass = String.class)
	public List<String> getSpecialities() {
		return specialities;
	}
	
	@NotBlank
	public String getEducation() {
		return education;
	}
	
	@NotBlank
	public String getWorkExperience() {
		return workExperience;
	}
	
	@NotNull
	@OneToMany
	public List<SocialIdentity> getSocialIdentitys() {
		return socialIdentitys;
	}
	
	//Setters
	
	public void setNameTrainer(String nameTrainer) {
		this.nameTrainer = nameTrainer;
	}
	
	public void setSpecialities(List<String> specialities) {
		this.specialities = specialities;
	}
	
	public void setEducation(String education) {
		this.education = education;
	}
	
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	
	public void setSocialIdentitys(List<SocialIdentity> socialIdentitys) {
		this.socialIdentitys = socialIdentitys;
	}
	
	
	

}
