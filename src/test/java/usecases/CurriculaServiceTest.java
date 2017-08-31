
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

import domain.Curricula;
import domain.SocialIdentity;
import domain.Trainer;
import services.CurriculaService;
import services.TrainerService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CurriculaServiceTest extends AbstractTest {

	@Autowired
	private CurriculaService	curriculaService;

	@Autowired
	private TrainerService		trainerService;


	//Caso de uso positivo crear una curricula
	@Test
	public void testPositive0() {

		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "pepe", specialities, "trainer", "5 years", new ArrayList<SocialIdentity>(), null);
	}

	//Caso de uso positivo crear una curricula con un socialIdentity
	@Test
	public void testPositive2() {

		Trainer trainer = trainerService.findAll().iterator().next();
		List<Curricula> curriculas = curriculaService.curriculumsByTrainer(trainer.getId());
		Assert.isTrue(!curriculas.isEmpty());
	}
	//Caso de uso negativo crear una curricula con el nombre del entrenador vacio
	@Test
	public void testNegative0() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "", specialities, "trainer", "5 years", new ArrayList<SocialIdentity>(), ConstraintViolationException.class);

	}

	//Caso de uso negativo crear una curricula sin especialidades
	@Test
	public void testNegative1() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "pepe", null, "trainer", "5 years", new ArrayList<SocialIdentity>(), ConstraintViolationException.class);

	}

	//Caso de uso negativo crear una curricula sin Identidades sociales
	@Test
	public void testNegative2() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "pepe", specialities, "trainer", "5 years", null, ConstraintViolationException.class);

	}
	@Test
	//Caso de uso negativo crear una curricula sin educacion
	public void testNegative3() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "pepe", specialities, "trainer", null, new ArrayList<SocialIdentity>(), ConstraintViolationException.class);

	}

	//Caso de uso negativo crear una curricula sin especialista
	public void testNegative4() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "pepe", null, "trainer", null, new ArrayList<SocialIdentity>(), ConstraintViolationException.class);

	}

	@Test
	//Caso de uso positivo driver
	public void testDrive() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");
		template("trainer1", "pepe", specialities, "trainer", "5 years", new ArrayList<SocialIdentity>(), null);
		template("trainer2", "pepe", specialities, "trainer", "5 years", new ArrayList<SocialIdentity>(), null);
		template("trainer1", "pepe", null, "trainer", null, new ArrayList<SocialIdentity>(), ConstraintViolationException.class);
		template("trainer2", "pepe", specialities, "trainer", null, new ArrayList<SocialIdentity>(), ConstraintViolationException.class);
		template("trainer1", null, specialities, "trainer", "5 years", new ArrayList<SocialIdentity>(), ConstraintViolationException.class);
		template("customer1", "pepe", specialities, "trainer", "5 years", new ArrayList<SocialIdentity>(), ConstraintViolationException.class);

	}

	protected void template(final String username, final String nameTrainer, final List<String> specialities, final String education, final String workExperience, final List<SocialIdentity> socialIdentitys, final Class<?> expected) {
		Class<?> caught = null;

		try {

			authenticate(username);
			Curricula curricula = curriculaService.create();
			curricula.setNameTrainer(nameTrainer);
			curricula.setSpecialities(specialities);
			curricula.setEducation(education);
			curricula.setWorkExperience(workExperience);
			curricula.setSocialIdentitys(socialIdentitys);
			curriculaService.save(curricula);
			curriculaService.flush();
			unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}

}
