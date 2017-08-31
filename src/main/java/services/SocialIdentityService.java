
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.SocialIdentity;
import repositories.SocialIdentityRepository;

@Service
@Transactional
public class SocialIdentityService {

	//Manage repositories

	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;

	//Services
	@Autowired
	private CurriculaService			curriculaService;

	@Autowired
	private TrainerService				trainerService;


	//Constructor

	public SocialIdentityService() {
		super();
	}

	//CRUD Methods

	public SocialIdentity create() {
		SocialIdentity social = new SocialIdentity();

		social.setLink(new String());
		social.setNameNetwork(new String());
		social.setNick(new String());

		return social;
	}

	public SocialIdentity save(SocialIdentity entity) {
		Assert.notNull(entity);
		SocialIdentity social = new SocialIdentity();

		if (exists(entity.getId())) {
			social = findOne(entity.getId());
			social.setLink(entity.getLink());
			social.setNameNetwork(entity.getNameNetwork());
			social.setNick(entity.getNick());
			social = socialIdentityRepository.save(social);

		} else {

			social = socialIdentityRepository.save(entity);

		}
		return social;

	}

	public List<SocialIdentity> findAll() {
		return socialIdentityRepository.findAll();
	}

	public SocialIdentity findOne(Integer id) {
		Assert.notNull(id);
		return socialIdentityRepository.findOne(id);
	}

	public List<SocialIdentity> save(List<SocialIdentity> entities) {
		Assert.notNull(entities);
		Assert.noNullElements(entities.toArray());
		return socialIdentityRepository.save(entities);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return socialIdentityRepository.exists(id);
	}

	public void delete(SocialIdentity entity) {
		Assert.notNull(entity);
		socialIdentityRepository.delete(entity);
	}

	//Other Methods

	public Object[] minAvgMaxSocialIdentitiesByTrainer() {
		return socialIdentityRepository.minAvgMaxSocialIdentitiesByTrainer();
	}

	public SocialIdentity copy(SocialIdentity p) {
		SocialIdentity socialIdentity = new SocialIdentity();
		socialIdentity.setNick(new String(p.getNick()));
		socialIdentity.setNameNetwork(new String(p.getNameNetwork()));
		socialIdentity.setLink(new String(p.getLink()));
		return socialIdentityRepository.save(socialIdentity);
	}

	public void flush() {
		socialIdentityRepository.flush();
	}

}
