package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Step;
import services.AdviceService;

@Controller
@RequestMapping("/advice")
public class AdviceController extends AbstractController{

	//Services

	@Autowired
	private AdviceService advicetService;


	// Constructors -----------------------------------------------------------

	public AdviceController() {
		super();
	}

	//Actions

	@RequestMapping("/listByStep")
	public ModelAndView list(@RequestParam Step q) {
		ModelAndView result;

		result = new ModelAndView("advice/list");

		result.addObject("advices",q.getAdvices());

		return result;
	}

}
