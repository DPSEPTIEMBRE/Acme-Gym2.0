
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Advice;
import domain.Step;
import repositories.StepRepository;

@Service
@Transactional
public class StepService {

	//Manage repositories

	@Autowired
	private StepRepository	stepRepository;

	//Manager services

	@Autowired
	private AdviceService	adviceService;


	//Constructor

	public StepService() {
		super();
	}

	//CRUD Methods

	public Step create() {
		Step step = new Step();

		step.setAdvices(new ArrayList<Advice>());
		step.setDescription(new String());
		step.setVideoTutorial(new String());

		return step;
	}

	public Step save(Step entity) {
		Assert.notNull(entity);
		Step step = new Step();

		if (exists(entity.getId())) {
			step.setAdvices(entity.getAdvices());
			step.setDescription(entity.getDescription());
			step.setVideoTutorial(entity.getVideoTutorial());

			return stepRepository.save(step);
		} else {
			return stepRepository.save(entity);
		}

	}

	public List<Step> findAll() {
		return stepRepository.findAll();
	}

	public Step findOne(Integer id) {
		Assert.notNull(id);
		return stepRepository.findOne(id);
	}

	public List<Step> save(List<Step> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		return stepRepository.save(entities);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return stepRepository.exists(id);
	}

	public void delete(Step entity) {
		Assert.notNull(entity);

		for (Advice a : entity.getAdvices()) {
			adviceService.delete(a);
		}

		entity.setAdvices(new ArrayList<Advice>());
		stepRepository.delete(entity);
	}

	//Other Methods

	public Object[] minAvgMaxStepByWorkout() {
		return stepRepository.minAvgMaxStepByWorkout();
	}

	public Step clone(Step p) {
		Step res = new Step();
		res.setVideoTutorial(new String(p.getVideoTutorial()));
		res.setAdvices(new ArrayList<Advice>());

		return stepRepository.save(res);
	}

}
