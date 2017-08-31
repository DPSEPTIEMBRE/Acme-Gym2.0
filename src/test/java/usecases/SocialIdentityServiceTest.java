
package usecases;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.SocialIdentity;
import services.SocialIdentityService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SocialIdentityServiceTest extends AbstractTest {

	@Autowired
	private SocialIdentityService socialIdentityService;


	//Caso de uso positivo crear una identidad social
	@Test
	public void testpositive0() {
		template("trainer1", "trainer", "http://www.trainer.com", "trainer", null);
	}

	//Caso de uso negativo crear una identidad social sin nick
	@Test
	public void testnegative0() {
		template("trainer1", null, "http://www.trainer.com", "trainer", ConstraintViolationException.class);
	}

	//Caso de uso fallo crear una identidad social con fallo en la url
	@Test
	public void testnegative1() {
		template("trainer1", "trainer", "fallo", "trainer", ConstraintViolationException.class);
	}

	//Caso de uso drive
	@Test
	public void testDrive() {

		template("trainer1", "trainer", "http://www.trainer.com", "trainer", null);
		template("trainer1", "trainer", "http://www.trainer.com", "trainer", null);
		template("trainer1", null, "http://www.trainer.com", "trainer", ConstraintViolationException.class);
		template("trainer1", "trainer", "fallo", "trainer", ConstraintViolationException.class);
		template("manager1", "trainer", "http://www.trainer.com", "trainer", ConstraintViolationException.class);
		template("trainer1", "trainer", "http://www.trainer.com", null, ConstraintViolationException.class);

	}

	protected void template(final String username, final String nick, final String link, final String nameNetwork, final Class<?> expected) {
		Class<?> caught = null;

		try {
			authenticate(username);
			SocialIdentity socialIdentity = socialIdentityService.create();
			socialIdentity.setNick(nick);
			socialIdentity.setNameNetwork(nameNetwork);
			socialIdentity.setLink(link);
			socialIdentityService.save(socialIdentity);
			socialIdentityService.flush();

			unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		checkExceptions(expected, caught);
	}

}
