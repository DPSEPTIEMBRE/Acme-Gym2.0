package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Step;

@Repository
public interface StepRepository extends JpaRepository<Step, Integer>{
	
	//El mínimo, media y máximo de pasos por rutina
	@Query("select min(w.steps.size),avg(w.steps.size),max(w.steps.size) from Workout w")
	Object[] minAvgMaxStepByWorkout();

}
