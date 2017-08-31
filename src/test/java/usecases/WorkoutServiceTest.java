package usecases;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Annotation;
import domain.Gym;
import domain.Step;
import domain.Workout;
import services.AnnotationService;
import services.GymService;
import services.StepService;
import services.WorkoutService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class WorkoutServiceTest extends AbstractTest {

	//The SUT
	
	@Autowired
	private WorkoutService		workoutService;

	@Autowired
	private GymService			gymService;

	@Autowired
	private AnnotationService	annotationService;

	@Autowired
	private StepService			stepService;
	
	
	//Templates
	
	/*
	 * 2.1: Browse the catalogue of workouts and display information about them.
	 */
	public void browseTemplate(final String username, final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
			
			workoutService.findAll();
			Workout w = workoutService.findOne(id);
			w.getId();

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 4.1: A manager can manage his or her workouts: list, create, edit and delete them.
	 */
	protected void template(final String username, final String title, final String description, final List<Step> steps, final List<Annotation> annotations, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);
			
			Assert.notNull(username);
			workoutService.workoutsByGym(57);
			Workout workout = workoutService.create();
			workout.setTitle(title);
			workout.setDescription(description);
			workout.setSteps(steps);
			workout.setAnnotations(annotations);
			workoutService.save(workout);
			workoutService.flush();

			unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}

	//Drivers

	//Test #01: Correct operation. Expected true.
	@Test
	public void testPositive0() {

		template("manager1", "hola", "hola", new ArrayList<Step>(), new ArrayList<Annotation>(), null);
	}

	//Test #02: Correct deletion of workout. Expected true.
	@Test
	public void testPositivo1() {
		authenticate("manager1");
		Workout workout = workoutService.findAll().iterator().next();

		workoutService.delete(workout);
		unauthenticate();

	}

	//Test #03: Correct listing of a manager's workouts. Expected true.
	@Test
	public void testPositivo2() {
		authenticate("manager1");
		Gym gym = gymService.findAll().iterator().next();

		List<Workout> workouts = workoutService.workoutsByGym(gym.getId());
		Assert.isTrue(!workouts.isEmpty());

		unauthenticate();
	}
	
	//Driver with several test cases.
	@Test
	public void testDrive() {

		List<Annotation> annotations = annotationService.findAll();
		template("manager1", "hola", "hola", new ArrayList<Step>(), annotations, null);
		template("manager2", "hola", "hola", null, new ArrayList<Annotation>(), ConstraintViolationException.class);
		template("manager2", "hola", null, new ArrayList<Step>(), new ArrayList<Annotation>(), ConstraintViolationException.class);

	}


	//Test #04: Attempt to create a routine without title. Expected false.
	@Test
	public void testnegative0() {

		template("manager1", "", "hola", new ArrayList<Step>(), new ArrayList<Annotation>(), ConstraintViolationException.class);
	}

	//Test #05: Attempt to create a routine without description. Expected false.
	@Test
	public void testNegative1() {

		template("manager1", "hola", null, new ArrayList<Step>(), new ArrayList<Annotation>(), ConstraintViolationException.class);
	}

	//Test #06: Attempt to create a routine without steps. Expected false.
	@Test
	public void testNegative2() {

		template("manager1", "hola", "hola", null, new ArrayList<Annotation>(), ConstraintViolationException.class);
	}

	//Test #07: Attempt to create a routine without annotations. Expected false.
	@Test
	public void testNegative3() {

		template("manager1", "hola", "hola", new ArrayList<Step>(), null, ConstraintViolationException.class);
	}

	//Test #08: Attempt to create by unauthorized user. Expected false
	@Test
	public void testNegative4() {
		Step step = stepService.findAll().iterator().next();
		step.setVideoTutorial("hola");

		List<Step> steps = new ArrayList<Step>();
		steps.add(step);
		template("trainer1", "hola", "hola", steps, new ArrayList<Annotation>(), ConstraintViolationException.class);
	}
	
	//Test #09: Attempt to create by anonymous user. Expected false
	@Test
	public void testNegative5() {
		Step step = stepService.findAll().iterator().next();
		step.setVideoTutorial("hola");

		List<Step> steps = new ArrayList<Step>();
		steps.add(step);
		template(null, "hola", "hola", steps, new ArrayList<Annotation>(), IllegalArgumentException.class);
	}
	
	//Test #10: All null fields. Expected false.
	@Test
	public void testNegative6() {

		template("manager1", null, null, null, null, ConstraintViolationException.class);
	}
	
	@Test
	public void browseDriver() {

		final Object testingData[][] = {
					
			//Test #01: . Expected true.
			{"customer1", 82, null},
				
			//Test #02: . Expected false.
			{"author", 82, IllegalArgumentException.class},
				
			//Test #03: . Expected false.
			{"customer1", 182, NullPointerException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.browseTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
