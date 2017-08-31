
package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Activity;
import domain.Curricula;
import domain.Trainer;
import security.LoginService;
import services.CurriculaService;
import services.TrainerService;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

	//Services

	@Autowired
	private LoginService		loginService;

	@Autowired
	private CurriculaService	curriculaService;
	@Autowired
	private TrainerService		trainerService;


	// Constructors -----------------------------------------------------------

	public TrainerController() {
		super();
	}

	//Actions

	@RequestMapping("/listByActivity.do")
	public ModelAndView list(@RequestParam Activity q) {
		ModelAndView result;

		List<Trainer> trainers = new ArrayList<Trainer>();

		trainers.addAll(q.getTrainers());

		result = new ModelAndView("trainer/list");
		result.addObject("trainers", trainers);

		return result;
	}

	@RequestMapping("/curriculas/list")
	public ModelAndView listCurriculas(@RequestParam Integer q) {
		ModelAndView result;

		Trainer trainer = (Trainer) loginService.findActorByUserName(q);

		result = new ModelAndView("curricula/list");
		result.addObject("curriculas", trainer.getCurriculas());
		result.addObject("a", 1);

		return result;
	}

	@RequestMapping("/curricula/edit")
	public ModelAndView editCurriculas(@RequestParam Curricula q) {
		ModelAndView result;

		result = new ModelAndView("curricula/edit");
		result.addObject("curricula", q);
		result.addObject("url", "trainer/curricula/save.do");

		return result;
	}

	@RequestMapping(value = "/curricula/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCurriculas(@Valid Curricula curricula, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("curricula/edit");
			result.addObject("curricula", curricula);
			result.addObject("message", "curricula.commit.error");
		} else {
			try {

				curriculaService.save(curricula);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (Throwable e) {
				result = new ModelAndView("curricula/edit");
				result.addObject("curricula", curricula);
				result.addObject("message", "curricula.commit.error");
			}
		}

		return result;
	}

	@RequestMapping("/avgStar")
	public ModelAndView avgStar(@RequestParam Trainer q) {
		ModelAndView result;

		result = new ModelAndView("trainer/avgStar");

		result.addObject("avgStar", trainerService.avgStar(q));

		return result;
	}

}
