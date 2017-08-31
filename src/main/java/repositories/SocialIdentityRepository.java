
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer> {

	//El m�nimo, media y m�ximo de identidades sociales por entrenador.
	@Query("select min(c.socialIdentitys.size),avg(c.socialIdentitys.size),max(c.socialIdentitys.size) from Trainer t join t.curriculas c")
	Object[] minAvgMaxSocialIdentitiesByTrainer();
}
