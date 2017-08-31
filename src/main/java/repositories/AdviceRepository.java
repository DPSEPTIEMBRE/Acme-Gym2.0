
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Advice;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, Integer> {

	//El m�nimo, m�ximo, media y desviaci�n est�ndar del n�mero de consejos por rutina.
	@Query("select min(s.advices.size),max(s.advices.size),avg(s.advices.size),stddev(s.advices.size) from Workout w join w.steps s")
	Object[] minMaxAvgDesviationAdvicesByWorkout();

}
