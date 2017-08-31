
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Curricula;
import domain.Trainer;
import security.LoginService;
import services.CurriculaService;

@Controller
@RequestMapping("/curricula")
public class CurriculaController extends AbstractController {

	//Services

	@Autowired
	private CurriculaService	curriculaService;

	@Autowired
	private LoginService		loginService;


	// Constructors -----------------------------------------------------------

	public CurriculaController() {
		super();
	}

	//Actions

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam Trainer q) {
		ModelAndView result;

		result = new ModelAndView("curricula/list");
		result.addObject("curriculas", q.getCurriculas());

		return result;
	}

	@RequestMapping(value = "/trainer/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(curriculaService.create(), null);

		return result;
	}

	@RequestMapping(value = "/trainer/saveCreate.do", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Curricula curricula, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {

			result = createNewModelAndView(curricula, null);
		} else {
			try {

				curriculaService.save(curricula);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (Throwable th) {
				result = createNewModelAndView(curricula, "curricula.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Curricula curricula, String message) {
		ModelAndView result;
		result = new ModelAndView("curricula/create");
		result.addObject("curricula", curricula);
		result.addObject("message", message);
		return result;
	}

}
