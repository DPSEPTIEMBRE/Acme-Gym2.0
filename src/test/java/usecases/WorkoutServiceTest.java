
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
import services.ManagerService;
import services.StepService;
import services.WorkoutService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class WorkoutServiceTest extends AbstractTest {

	@Autowired
	private WorkoutService		workoutService;

	@Autowired
	private GymService			gymService;

	@Autowired
	private AnnotationService	annotationService;

	@Autowired
	private StepService			stepService;

	@Autowired
	private ManagerService		managerService;


	//Caso de uso positivo crear una rutina
	@Test
	public void testPositive0() {

		template("manager1", "hola", "hola", new ArrayList<Step>(), new ArrayList<Annotation>(), null);
	}

	//Caso de uso positivo borrar una rutina
	@Test
	public void testPositivo1() {
		authenticate("manager1");
		Workout workout = workoutService.findAll().iterator().next();

		workoutService.delete(workout);
		unauthenticate();

	}

	//Caso de uso positivo listar las rutinas de un gimnasio 

	@Test
	public void testPositivo2() {
		authenticate("manager1");
		Gym gym = gymService.findAll().iterator().next();

		List<Workout> workouts = workoutService.workoutsByGym(gym.getId());
		Assert.isTrue(!workouts.isEmpty());

		unauthenticate();
	}
	//Caso de uso Drive
	@Test
	public void testDrive() {

		List<Annotation> annotations = annotationService.findAll();
		template("manager1", "hola", "hola", new ArrayList<Step>(), annotations, null);
		template("manager2", "hola", "hola", null, new ArrayList<Annotation>(), ConstraintViolationException.class);
		template("manager2", "hola", null, new ArrayList<Step>(), new ArrayList<Annotation>(), ConstraintViolationException.class);

	}
	//Caso de uso positivo listar las rutinas 
	@Test
	public void testPositivo3() {
		workoutService.findAll();
	}

	//Caso de uso positivo escribir una nota en la rutina
	@Test
	public void testPositivo4() {
		List<Annotation> annotations = annotationService.findAll();

		template("manager1", "hola", "hola", new ArrayList<Step>(), annotations, null);
	}

	@Test
	public void testPositivo5() {
		List<Annotation> annotations = annotationService.findAll();

		template("manager1", "hola", "hola", new ArrayList<Step>(), annotations, null);
	}

	////Caso de uso negativo crear una rutina sin titulo
	@Test
	public void testnegative0() {

		template("manager1", "", "hola", new ArrayList<Step>(), new ArrayList<Annotation>(), ConstraintViolationException.class);
	}

	//Caso de uso negativo crear una rutina sin descripcion
	@Test
	public void testNegative1() {

		template("manager1", "hola", null, new ArrayList<Step>(), new ArrayList<Annotation>(), ConstraintViolationException.class);
	}

	//Caso de uso negativo crear una rutina  sin pasos
	@Test
	public void testNegative2() {

		template("manager1", "hola", "hola", null, new ArrayList<Annotation>(), ConstraintViolationException.class);
	}

	//Caso de uso negativo crear una rutina sin annotation
	@Test
	public void testNegative3() {

		template("manager1", "hola", "hola", new ArrayList<Step>(), null, ConstraintViolationException.class);
	}

	//Caso de uso negativo crear una rutina con un actor distinto
	@Test
	public void testNegative4() {
		Step step = stepService.findAll().iterator().next();
		step.setVideoTutorial("hola");

		List<Step> steps = new ArrayList<Step>();
		steps.add(step);
		template("trainer1", "hola", "hola", steps, new ArrayList<Annotation>(), ConstraintViolationException.class);
	}

	protected void template(final String username, final String title, final String description, final List<Step> steps, final List<Annotation> annotations, final Class<?> expected) {
		Class<?> caught = null;

		try {

			authenticate(username);
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

}
