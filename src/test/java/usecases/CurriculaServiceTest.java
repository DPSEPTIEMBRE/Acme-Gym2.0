
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
	
	//The SUT

	@Autowired
	private CurriculaService	curriculaService;

	@Autowired
	private TrainerService		trainerService;
	
	
	//Templates
	
	/*
	 * 10.1: Browse the curriculum of a trainer.
	 */
	public void browseTemplate(final String username, final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.notNull(id);
			Trainer t = trainerService.findOne(id);
			t.getCurriculas();

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	/*
	 * 11.1: A trainer can edit his or her curriculum.
	 */
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

	//Drivers
	
	//Test #01: Correct creation. Expected true.
	@Test
	public void testPositive0() {

		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "pepe", specialities, "trainer", "5 years", new ArrayList<SocialIdentity>(), null);
	}

	//Test #02: Correct creation with a social identity. Expected true.
	@Test
	public void testPositive2() {

		Trainer trainer = trainerService.findAll().iterator().next();
		List<Curricula> curriculas = curriculaService.curriculumsByTrainer(trainer.getId());
		Assert.isTrue(!curriculas.isEmpty());
	}
	
	//Test #03: Attempt to create with empty name. Expected false.
	@Test
	public void testNegative0() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "", specialities, "trainer", "5 years", new ArrayList<SocialIdentity>(), ConstraintViolationException.class);

	}

	//Test #04: Attempt to create without specialties. Expected false.
	@Test
	public void testNegative1() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "pepe", null, "trainer", "5 years", new ArrayList<SocialIdentity>(), ConstraintViolationException.class);

	}

	//Test #05: Attempt to create with null social identities. Expected false.
	@Test
	public void testNegative2() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "pepe", specialities, "trainer", "5 years", null, ConstraintViolationException.class);

	}
	
	//Test #06: Attempt to create with null education. Expected false.
	@Test
	public void testNegative3() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "pepe", specialities, "trainer", null, new ArrayList<SocialIdentity>(), ConstraintViolationException.class);

	}

	//Test #07: Attempt to create without specialist. Expected false.
	public void testNegative4() {
		List<String> specialities = new ArrayList<String>();
		specialities.add("Footing");
		specialities.add("aerobic");

		template("trainer1", "pepe", null, "trainer", null, new ArrayList<SocialIdentity>(), ConstraintViolationException.class);

	}

	//Driver for several use cases.
	@Test
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
	
	@Test
	public void browseDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"customer1", 61, null},
				
			//Test #02: Attempt to access null trainer. Expected false.
			{"customer1", null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access another entity as trainer. Expected false.
			{"customer1", 161, NullPointerException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.browseTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
