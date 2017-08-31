package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curricula;

@Repository
public interface CurriculaRepository extends JpaRepository<Curricula, Integer>{
	
	//Curriculum de un actor
	@Query("select a.curriculas from Actor a where a.id=?1")
	List<Curricula> curriculumsByTrainer(int trainer_id);

}
