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

import domain.Step;
import domain.Workout;
import services.StepService;
import services.WorkoutService;

@Controller
@RequestMapping("/step")
public class StepController extends AbstractController {

	private static Integer toSave=null;
	
	//Services

	@Autowired
	private StepService steptService;
	
	@Autowired
	private WorkoutService workoutService;


	// Constructors -----------------------------------------------------------

	public StepController() {
		super();
	}

	//Actions

	@RequestMapping("/listByWorkout")
	public ModelAndView list(@RequestParam Workout q) {
		ModelAndView result;

		result = new ModelAndView("step/list");

		result.addObject("steps",q.getSteps());

		return result;
	}

	@RequestMapping("/create")
	public ModelAndView create(@RequestParam Workout q) {
		ModelAndView result;

		result = new ModelAndView("step/create");

		result.addObject("step",steptService.create());
		result.addObject("url","step/save.do");
		
		toSave=q.getId();

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Step step,BindingResult binding) {
		ModelAndView result;

		if(binding.hasErrors()) {
			System.out.println(binding.getFieldErrors());
			result= new ModelAndView("step/create");
			result.addObject("step", step);
			result.addObject("message", "step.commit.error");
		}else {
			try {
				step= steptService.save(step);
				Workout work= workoutService.findOne(toSave);
				List<Step> steps=work.getSteps();
				steps.add(step);
				work.setSteps(steps);
				workoutService.save(work);
				result=new ModelAndView("redirect:/welcome/index.do");
			}catch (Throwable e) {
				result= new ModelAndView("step/create");
				result.addObject("step", step);
				result.addObject("message", "step.commit.error");
			}
		}

		return result;
	}
}
