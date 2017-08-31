
package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Curricula;
import domain.SocialIdentity;
import services.CurriculaService;
import services.SocialIdentityService;

@Controller
@RequestMapping("/socialIdentity")
public class SocialIdentityController extends AbstractController {

	private static Integer			tosave	= null;
	//Services

	@Autowired
	private SocialIdentityService	socialIdentityService;

	@Autowired
	private CurriculaService		curriculaService;


	// Constructors -----------------------------------------------------------

	public SocialIdentityController() {
		super();
	}

	//Actions

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam Curricula q) {
		ModelAndView result;

		result = new ModelAndView("socialIdentity/list");

		result.addObject("socialIdentitys", q.getSocialIdentitys());

		return result;
	}

	@RequestMapping(value = "/trainer/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Curricula q) {
		ModelAndView result;

		result = createNewModelAndView(socialIdentityService.create(), null);
		tosave = q.getId();
		return result;
	}

	@RequestMapping(value = "/trainer/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Integer q) {
		ModelAndView result;

		SocialIdentity socialIdentity = socialIdentityService.findOne(q);

		List<Curricula> curriculas = curriculaService.findAll();
		for (Curricula e : curriculas) {
			if (e.getSocialIdentitys().contains(socialIdentity)) {
				tosave = e.getId();
				System.out.println("id ->" + tosave);
				break;
			}
		}

		result = createEditModelAndView(socialIdentity, null);

		return result;

	}

	@RequestMapping(value = "/trainer/saveCreate.do", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid SocialIdentity socialIdentity, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {

			result = createNewModelAndView(socialIdentity, null);
		} else {
			try {
				if (tosave == null) {
					result = createNewModelAndView(socialIdentity, "socialidentity.commit.error");
				} else {
					socialIdentity = socialIdentityService.save(socialIdentity);
					Curricula curicula = curriculaService.findOne(tosave);
					List<SocialIdentity> sl = curicula.getSocialIdentitys();
					sl.add(socialIdentity);

					curicula.setSocialIdentitys(sl);
					curriculaService.save(curicula);
					result = new ModelAndView("redirect:/welcome/index.do");
				}

			} catch (Throwable th) {
				result = createNewModelAndView(socialIdentity, "socialidentity.commit.error");
			}
		}
		tosave = null;
		return result;
	}

	@RequestMapping(value = "/trainer/saveEdit.do", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid SocialIdentity socialIdentity, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(socialIdentity, null);
		} else {
			try {
				Curricula curicula = curriculaService.findOne(tosave);
				System.out.println("id2 ->" + curicula.getId());
				List<SocialIdentity> sl = curicula.getSocialIdentitys();
				for (int i = 0; i <= sl.size(); i++) {
					if (sl.get(i).getId() == socialIdentity.getId()) {
						socialIdentity = socialIdentityService.save(socialIdentity);
						sl.set(i, socialIdentity);
						System.out.println("ok");
						break;
					} else {
						System.out.println("nda");
						continue;
					}

				}
				curicula.setSocialIdentitys(sl);
				curriculaService.save(curicula);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable th) {
				result = createEditModelAndView(socialIdentity, "socialidentity.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(SocialIdentity socialIdentity, String message) {
		ModelAndView result;
		result = new ModelAndView("socialIdentity/create");
		result.addObject("socialIdentity", socialIdentity);
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView createEditModelAndView(SocialIdentity socialIdentity, String message) {
		ModelAndView result;
		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);
		result.addObject("message", message);
		return result;
	}

}
