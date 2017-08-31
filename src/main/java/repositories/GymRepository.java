
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Activity;
import domain.Annotation;
import domain.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer> {

	//Lista de actividades de un gimnasio especifico
	@Query("select g.activities from Gym g where g.id=?1")
	List<Activity> activitiesByGym(int gym_id);

	//Gimnasio que ofrece mas actividades
	@Query("select g from Gym g where g.activities.size = (select max(a.activities.size) from Gym a where a.isDelete=false) and g.isDelete=false")
	List<Gym> gymWithMoreActivities();

	//El m�nimo, m�ximo, media y desviaci�n est�ndar del n�mero de clientes por gimnasio
	@Query("select min(g.customers.size), max(g.customers.size), avg(g.customers.size),stddev(g.customers.size) from Gym g")
	Object[] minMaxAvgDesviationCustomersForGym();

	//Notas asociadas a gimnasio
	@Query("select a.annotations from Gym a where a.id=?1")
	List<Annotation> annotationsByGym(int gym_id);

	//La media y desviaci�n est�ndar del n�mero de notas asociadas a gimnasios
	@Query("select avg(c.annotations.size),stddev(c.annotations.size) from Gym c")
	Object[] avgDesviationNotesByGym();

	//La media y desviaci�n est�ndar del n�mero de estrellas por gimnasio
	@Query("select avg(s.rate),stddev(s.rate) from Gym c join c.annotations s")
	Object[] avgDesviationStarsByGym();

	//Media estrellas asociadas a gimnasio
	@Query("select avg(n.rate) from Gym a join a.annotations n where a.id=?1")
	Double avgStarsByGym(int gym_id);

}
