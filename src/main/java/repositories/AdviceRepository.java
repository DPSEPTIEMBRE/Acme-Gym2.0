
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Advice;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, Integer> {

	//El mínimo, máximo, media y desviación estándar del número de consejos por rutina.
	@Query("select min(s.advices.size),max(s.advices.size),avg(s.advices.size),stddev(s.advices.size) from Workout w join w.steps s")
	Object[] minMaxAvgDesviationAdvicesByWorkout();

}
