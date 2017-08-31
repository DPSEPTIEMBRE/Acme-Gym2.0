package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AdviceService;
import services.CustomerService;
import services.StepService;
import utilities.AbstractTest;
import domain.Advice;
import domain.Customer;
import domain.Step;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdviceServiceTest extends AbstractTest {

	//The SUT
	
	@Autowired
	private AdviceService adviceService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private StepService stepService;
	
	
	//Templates
	
	/*
	 * 15.1: Write a piece of advice regarding a step in a workout.
	 */
	public void writeTemplate(final String username, String text, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.isTrue(username == "customer1");
			Assert.notNull(text);
			Advice a = adviceService.create();
			Customer c = customerService.findAll().iterator().next();
			a.setActor(c);
			a.setNick("nick");
			a.setText(text);
			Step s = stepService.findAll().iterator().next();
			a.setStep(s);
			adviceService.save(a);
			
			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	
	//Drivers

	@Test
	public void writeDriver() {

		final Object testingData[][] = {
					
			//Test #01: Correct access. Expected true.
			{"customer1", "text", null},
				
			//Test #02: Attempt to access by non registered user. Expected false.
			{"author", "text", IllegalArgumentException.class},
				
			//Test #03: Attempt to insert null text. Expected false.
			{"customer1", null, IllegalArgumentException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.writeTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
