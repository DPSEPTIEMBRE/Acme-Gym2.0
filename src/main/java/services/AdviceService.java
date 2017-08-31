
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Advice;
import repositories.AdviceRepository;

@Service
@Transactional
public class AdviceService {

	//Manage repositories

	@Autowired
	private AdviceRepository	adviceRepository;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private StepService			stepService;


	//Constructor

	public AdviceService() {
		super();
	}

	//CRUD Methods

	public Advice create() {
		Advice adv = new Advice();

		adv.setActor(customerService.create());
		adv.setNick(new String());
		adv.setStep(stepService.create());
		adv.setText(new String());

		return adv;
	}

	public Advice save(Advice entity) {
		Assert.notNull(entity);
		Advice advice = new Advice();

		if (exists(entity.getId())) {
			advice.setActor(entity.getActor());
			advice.setNick(entity.getNick());
			advice.setStep(entity.getStep());
			advice.setText(entity.getText());

			return adviceRepository.save(advice);
		} else {
			return adviceRepository.save(entity);
		}

	}

	public List<Advice> findAll() {
		return adviceRepository.findAll();
	}

	public Advice findOne(Integer id) {
		Assert.notNull(id);
		return adviceRepository.findOne(id);
	}

	public List<Advice> save(List<Advice> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		return adviceRepository.save(entities);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return adviceRepository.exists(id);
	}

	public void delete(Advice entity) {
		Assert.notNull(entity);
		adviceRepository.delete(entity);
	}

	//Other Methods

	public Object[] minMaxAvgDesviationAdvicesByWorkout() {
		Object[] res = adviceRepository.minMaxAvgDesviationAdvicesByWorkout();
		if (res[3] == null) {
			res[3] = 0.0;
		}
		return res;

	}

}
