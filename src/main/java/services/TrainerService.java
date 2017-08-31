
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Annotation;
import domain.Curricula;
import domain.Trainer;
import repositories.TrainerRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class TrainerService {

	//Manager repositories

	@Autowired
	private TrainerRepository	trainerRepository;

	@Autowired
	private CurriculaService	curriculaService;


	//Constructor

	public TrainerService() {
		super();
	}

	//CRUD Methods

	public Trainer create() {

		Trainer trainer = new Trainer();

		trainer.setActorName(new String());
		trainer.setAnnotationStore(new ArrayList<Annotation>());
		trainer.setAnnotationWriter(new ArrayList<Annotation>());
		trainer.setCity(new String());
		trainer.setCountry(new String());
		trainer.setEmail(new String());
		trainer.setPhone(new String());
		trainer.setPostalAddress(new String());
		trainer.setSurname(new String());
		trainer.setCurriculas(new ArrayList<Curricula>());

		Authority auth = new Authority();
		auth.setAuthority("TRAINER");
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(auth));
		account.setUsername(new String());
		account.setPassword(new String());
		account.setActivate(true);

		trainer.setUserAccount(account);

		return trainer;
	}

	public boolean exists(Integer arg0) {
		Assert.notNull(arg0);
		return trainerRepository.exists(arg0);
	}

	public void delete(Trainer arg0) {
		Assert.notNull(arg0);
		trainerRepository.delete(arg0);
	}

	public List<Trainer> findAll() {
		return trainerRepository.findAll();
	}

	public Trainer findOne(Integer arg0) {
		Assert.notNull(arg0);
		return trainerRepository.findOne(arg0);
	}

	public List<Trainer> save(List<Trainer> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		return trainerRepository.save(entities);
	}

	public Trainer save(Trainer trainer) {
		Assert.notNull(trainer);
		Trainer trai = null;

		if (exists(trainer.getId())) {
			trai = findOne(trainer.getId());
			trai.setActorName(trainer.getActorName());
			trai.setCity(trainer.getCity());
			trai.setCountry(trainer.getCountry());
			trai.setEmail(trainer.getEmail());
			trai.setPhone(trainer.getPhone());
			trai.setPostalAddress(trainer.getPostalAddress());
			trai.setSurname(trainer.getSurname());
			trai.setCurriculas(trainer.getCurriculas());

			trai = trainerRepository.save(trai);
		} else {

			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			trainer.getUserAccount().setPassword(encoder.encodePassword(trainer.getUserAccount().getPassword(), null));

			trai = trainerRepository.save(trainer);

		}

		return trai;
	}

	//Others Methods

	public Object[] avgDesviationStarsByTrainers() {
		Object[] res = trainerRepository.avgDesviationStarsByTrainers();
		if (res[1] == null) {
			res[1] = 0.0;
		}
		return res;
	}

	public Object[] avgDesviationNotesByTrainers() {
		Object[] res = trainerRepository.avgDesviationNotesByTrainers();
		if (res[1] == null) {
			res[1] = 0.0;
		}
		return res;
	}

	public Double avgStarsCountryByTrainers() {
		Double res = trainerRepository.avgStarsCountryByTrainers();
		if (res == null) {
			res = 0.0;
		}
		return res;
	}

	public Double avgStarsCityByTrainers() {
		Double res = trainerRepository.avgStarsCityByTrainers();
		if (res == null) {
			res = 0.0;
		}
		return res;
	}

	public List<Annotation> annotationsByTrainer(Integer trainer_id) {
		return trainerRepository.annotationsByTrainer(trainer_id);
	}

	public Double avgStarsByTrainer(int trainer_id) {
		Assert.notNull(trainer_id);
		return trainerRepository.avgStarsByTrainer(trainer_id);
	}

	public Trainer selectByUsername(String username) {
		return trainerRepository.selectByUsername(username);
	}

	public Double avgStar(Trainer a) {
		Double res = 0.0;
		Integer up = 0;
		Integer total = a.getAnnotationStore().size() + a.getAnnotationWriter().size();

		for (Annotation an : a.getAnnotationStore()) {
			up = up + an.getRate();
		}
		for (Annotation an : a.getAnnotationWriter()) {
			up = up + an.getRate();
		}

		res = new Double(up / total);

		return res;
	}

}
