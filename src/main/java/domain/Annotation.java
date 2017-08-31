
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Annotation extends DomainEntity {

	//Atributtes

	private Date		momentWritten;
	private String		text;
	private Integer		rate;
	private Actor		actorWrites;
	private Actor		actorStores;
	private Activity	activity;
	private Gym			gym;
	private Workout		workout;


	//Getters

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getMomentWritten() {
		return momentWritten;
	}

	@NotBlank
	public String getText() {
		return text;
	}

	@NotNull
	@Range(min = 0, max = 3)
	public Integer getRate() {
		return rate;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Actor getActorWrites() {
		return actorWrites;
	}

	@ManyToOne(optional = true)
	public Actor getActorStores() {
		return actorStores;
	}

	@ManyToOne(optional = true)
	public Activity getActivity() {
		return activity;
	}

	@ManyToOne(optional = true)
	public Workout getWorkout() {
		return workout;
	}

	@ManyToOne(optional = true)
	public Gym getGym() {
		return gym;
	}

	//Setters

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void setMomentWritten(Date momentWritten) {
		this.momentWritten = momentWritten;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public void setActorWrites(Actor actorWrites) {
		this.actorWrites = actorWrites;
	}

	public void setActorStores(Actor actorStores) {
		this.actorStores = actorStores;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

}
