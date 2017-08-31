package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer>{

	//Rutinas de un gimnasio
	@Query("select g.workouts from Gym g where g.id=?1")
	List<Workout> workoutsByGym(int gym_id);
	
	//El mínimo, media y máximo de rutinas por gimnasio
	@Query("select min(g.workouts.size),avg(g.workouts.size),max(g.workouts.size) from Gym g")
	Object[] minAvgMaxWorkoutByGym();
}
