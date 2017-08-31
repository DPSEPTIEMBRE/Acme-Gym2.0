
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

import domain.Gym;
import domain.Workout;
import services.GymService;
import services.WorkoutService;

@Controller
@RequestMapping("/workout")
public class WorkoutController extends AbstractController {

	private static Integer	toSave	= null;

	//Services

	@Autowired
	private WorkoutService	workoutService;

	@Autowired
	private GymService		gymService;


	// Constructors -----------------------------------------------------------

	public WorkoutController() {
		super();
	}

	//Actions

	@RequestMapping("/listByGym")
	public ModelAndView list(@RequestParam Gym q) {
		ModelAndView result;

		result = new ModelAndView("workout/list");

		result.addObject("workouts", q.getWorkouts());
		result.addObject("a", 1);

		return result;
	}

	@RequestMapping("/listByGym2")
	public ModelAndView list2(@RequestParam Gym q) {
		ModelAndView result;

		result = new ModelAndView("workout/list");

		result.addObject("workouts", q.getWorkouts());
		result.addObject("a", 2);

		return result;
	}

	@RequestMapping("/orderByStar")
	public ModelAndView orderByStar() {
		ModelAndView result;

		result = new ModelAndView("workout/list");

		result.addObject("workouts", workoutService.orderByStar());

		return result;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam Workout q) {
		ModelAndView result;

		try {
			workoutService.delete(q);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam Workout q) {
		ModelAndView result;

		result = new ModelAndView("workout/edit");

		result.addObject("workout", q);
		result.addObject("url", "workout/save.do");

		toSave = null;

		return result;
	}

	@RequestMapping("/create")
	public ModelAndView create(@RequestParam Gym q) {
		ModelAndView result;

		result = new ModelAndView("workout/edit");

		result.addObject("workout", workoutService.create());
		result.addObject("url", "workout/save.do");

		toSave = q.getId();

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Workout workout, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getFieldErrors());
			result = new ModelAndView("workout/edit");
			result.addObject("workout", workout);
			result.addObject("message", "workout.commit.error");
		} else {
			try {
				if (toSave != null) {
					workout = workoutService.save(workout);
					result = new ModelAndView("redirect:/welcome/index.do");

					Gym g = gymService.findOne(toSave);
					List<Workout> works = g.getWorkouts();
					works.add(workout);
					g.setWorkouts(works);
					gymService.save(g);
				} else {
					workoutService.save(workout);
					result = new ModelAndView("redirect:/welcome/index.do");
				}

			} catch (Throwable e) {
				System.out.println("ERROR");
				result = new ModelAndView("workout/edit");
				result.addObject("workout", workout);
				result.addObject("message", "workout.commit.error");
			}
		}

		return result;
	}

}
