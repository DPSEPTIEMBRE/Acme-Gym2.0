
package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Annotation;
import domain.Gym;
import domain.Step;
import domain.Workout;
import repositories.WorkoutRepository;

@Service
@Transactional
public class WorkoutService {

	//Manage repositories

	@Autowired
	private WorkoutRepository	workoutRepository;

	//Manage services

	@Autowired
	private StepService			stepService;

	@Autowired
	private GymService			gymService;

	@Autowired
	private AnnotationService	annotationService;


	//Constructor

	public WorkoutService() {
		super();
	}

	//CRUD methods

	public Workout create() {
		Workout work = new Workout();

		work.setDescription(new String());
		work.setSteps(new ArrayList<Step>());
		work.setTitle(new String());

		return work;
	}

	public Workout save(Workout entity) {
		Assert.notNull(entity);
		Workout work = new Workout();

		if (exists(entity.getId())) {
			work = findOne(entity.getId());
			work.setDescription(entity.getDescription());
			work.setSteps(entity.getSteps());
			work.setTitle(entity.getTitle());
			work.setAnnotations(entity.getAnnotations());

			return workoutRepository.save(work);
		} else {
			return workoutRepository.save(entity);
		}

	}

	public List<Workout> findAll() {
		return workoutRepository.findAll();
	}

	public Workout findOne(Integer id) {
		Assert.notNull(id);
		return workoutRepository.findOne(id);
	}

	public List<Workout> save(List<Workout> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		return workoutRepository.save(entities);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return workoutRepository.exists(id);
	}

	public void delete(Workout entity) {
		Assert.notNull(entity);

		for (Step s : entity.getSteps()) {
			stepService.delete(s);
		}
		entity.setSteps(new ArrayList<Step>());

		Gym g = null;
		for (Gym gg : gymService.findAll()) {
			if (gg.getWorkouts().contains(entity)) {
				g = gg;
			} else {
				continue;
			}
		}
		List<Workout> works = g.getWorkouts();
		works.remove(entity);
		g.setWorkouts(works);

		for (Annotation a : entity.getAnnotations()) {
			a.setWorkout(null);
			annotationService.delete(a);
		}
		entity.setAnnotations(new ArrayList<Annotation>());

		gymService.save(g);

		workoutRepository.delete(entity);
	}

	//Other methods

	public List<Workout> workoutsByGym(int gym_id) {
		Assert.notNull(gym_id);
		return workoutRepository.workoutsByGym(gym_id);
	}

	public Object[] minAvgMaxWorkoutByGym() {
		return workoutRepository.minAvgMaxWorkoutByGym();
	}

	public List<Workout> orderByStar() {
		List<Workout> works = findAll();
		Map<Integer, List<Workout>> map = new HashMap<Integer, List<Workout>>();

		for (Workout w : works) {
			List<Annotation> anns = w.getAnnotations();
			Integer sum = 0;
			for (Annotation an : anns) {
				sum = sum + an.getRate();
			}
			List<Workout> wout = new ArrayList<Workout>();
			if (map.containsKey(sum)) {
				wout = map.get(sum);
			}
			wout.add(w);
			map.put(sum, wout);
		}
		Map<Integer, List<Workout>> treeMap = new TreeMap<Integer, List<Workout>>(map);
		works = new ArrayList<Workout>();
		for (List<Workout> lw : treeMap.values()) {
			works.addAll(lw);
		}
		return works;
	}

	public void flush() {
		workoutRepository.flush();
	}

}
