
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	//Los clientes que se han apuntado a mas actividades
	@Query("select c from Customer c where c.activities.size = (select max(d.activities.size) from Customer d)")
	List<Customer> customersWithMoreActivities();

	//El m�nimo, m�ximo, media y desviaci�n est�ndar del n�mero de gimnasios por cliente
	@Query("select min(c.gyms.size), max(c.gyms.size), avg(c.gyms.size),stddev(c.gyms.size) from Customer c")
	Object[] minMaxAvgDesviationGymsByCustomers();

	//La media y desviaci�n est�ndar del n�mero de notas asociadas a clientes
	@Query("select avg(c.annotationStore.size + c.annotationWriter.size),stddev(c.annotationStore.size + c.annotationWriter.size) from Customer c")
	Object[] avgDesviationNotesByCustomers();

	//La media y desviaci�n est�ndar del n�mero de estrellas por cliente
	@Query("select avg(s.rate),stddev(s.rate) from Customer c join c.annotationStore s")
	Object[] avgDesviationStarsByCustomers();

	//La media de estrellas por cliente, agrupadas por pa�s
	@Query("select avg(s.rate) from Customer c join c.annotationStore s group by c.country")
	Double avgStarsCountryByCustomers();

	//La media de estrellas por cliente, agrupadas por ciudad
	@Query("select avg(s.rate) from Customer c join c.annotationStore s group by c.city")
	Double avgStarsCityByCustomers();

	//Media estrellas asociadas a administrador
	@Query("select avg(n.rate) from Customer a join a.annotationStore n where a.id=?1")
	Double avgStarsByCustomer(int customer_id);

}
