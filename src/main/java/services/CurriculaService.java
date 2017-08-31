
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curricula;
import domain.SocialIdentity;
import domain.Trainer;
import repositories.CurriculaRepository;
import security.LoginService;

@Service
@Transactional
public class CurriculaService {

	//Manage repositories

	@Autowired
	private CurriculaRepository	curriculaRepository;

	@Autowired
	private TrainerService		trainerService;

	@Autowired
	private LoginService		loginService;


	//Constructor

	public CurriculaService() {
		super();
	}

	//CRUD Methods

	public Curricula create() {
		Curricula cur = new Curricula();

		cur.setEducation(new String());
		cur.setNameTrainer(new String());
		cur.setSocialIdentitys(new ArrayList<SocialIdentity>());
		cur.setSpecialities(new ArrayList<String>());
		cur.setWorkExperience(new String());

		return cur;
	}

	public Curricula createDefaut(Trainer trainer) {
		Curricula cur = new Curricula();
		cur.setEducation("defaut");
		cur.setNameTrainer(trainer.getActorName() + " " + trainer.getSurname());
		cur.setSocialIdentitys(new ArrayList<SocialIdentity>());
		List<String> specialites = new ArrayList<String>();
		specialites.add("defaut");
		cur.setSpecialities(specialites);
		cur.setWorkExperience("defaut");

		Curricula saved = curriculaRepository.save(cur);

		List<Curricula> curriculas = trainer.getCurriculas();
		curriculas.add(saved);
		trainer.setCurriculas(curriculas);
		trainerService.save(trainer);

		return saved;

	}

	public Curricula save(Curricula entity) {
		Assert.notNull(entity);
		Curricula cur = new Curricula();

		if (exists(entity.getId())) {
			cur = findOne(entity.getId());
			cur.setEducation(entity.getEducation());
			cur.setNameTrainer(entity.getNameTrainer());
			cur.setSocialIdentitys(entity.getSocialIdentitys());
			cur.setSpecialities(entity.getSpecialities());
			cur.setWorkExperience(entity.getWorkExperience());

			return curriculaRepository.save(cur);
		} else {

			Curricula saved = curriculaRepository.save(entity);
			Trainer trainer = trainerService.selectByUsername(LoginService.getPrincipal().getUsername());
			List<Curricula> curriculas = trainer.getCurriculas();
			curriculas.add(saved);
			trainer.setCurriculas(curriculas);
			trainerService.save(trainer);

			return saved;
		}

	}

	public List<Curricula> findAll() {
		return curriculaRepository.findAll();
	}

	public Curricula findOne(Integer id) {
		Assert.notNull(id);
		return curriculaRepository.findOne(id);
	}

	public List<Curricula> save(List<Curricula> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());

		return curriculaRepository.save(entities);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return curriculaRepository.exists(id);
	}

	public void delete(Curricula entity) {
		Assert.notNull(entity);
		curriculaRepository.delete(entity);
	}

	//Other Methods

	public List<Curricula> curriculumsByTrainer(int trainer_id) {
		Assert.notNull(trainer_id);
		return curriculaRepository.curriculumsByTrainer(trainer_id);
	}

	public void flush() {
		curriculaRepository.flush();
	}

}
