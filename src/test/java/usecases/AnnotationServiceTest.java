package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AnnotationService;
import utilities.AbstractTest;
import domain.Annotation;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AnnotationServiceTest extends AbstractTest {

	//The SUT
	
	@Autowired
	private AnnotationService		annotationService;
	
	
	//Templates
	
	/*
	 * 3.1: An authenticated actor can write an annotation.
	 */
	public void writeTemplate(final String username, String text, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);
				
			Assert.notNull(username);
			Assert.notNull(text);
			Annotation an = annotationService.create();
			an.setRate(1);
			an.setText(text);
			annotationService.save(an);

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
				
			//Test #02: Attempt to access by anonymous user. Expected false.
			{null, "text", IllegalArgumentException.class},
				
			//Test #03: Attempt to insert null text. Expected false.
			{"customer1", null, InvalidDataAccessApiUsageException.class}

		};
		for (int i = 0; i < testingData.length; i++)
			this.writeTemplate((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
