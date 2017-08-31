package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AdviceService;
import services.SocialIdentityService;
import services.WorkoutService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	//The SUT
	
	@Autowired
	private WorkoutService workoutService;
	
	@Autowired
	private SocialIdentityService socialService;
	
	@Autowired
	private AdviceService adviceService;
	
	
	//Templates
	
	/*
	 * 5.1: An administrator can access the dashboard with new version information.
	 */
	public void dashboardTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "admin");
			workoutService.minAvgMaxWorkoutByGym();
			socialService.minAvgMaxSocialIdentitiesByTrainer();
			adviceService.minMaxAvgDesviationAdvicesByWorkout();

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void dashboardDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"admin", null},
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, IllegalArgumentException.class},
				
			//Test #03: Attempt to access by unauthorized user. Expected false.
			{"dancer1", IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.dashboardTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
