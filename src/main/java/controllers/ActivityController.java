
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
import domain.Gym;
import domain.Trainer;
import services.ActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	private static Integer	actual	= null;

	//Services

	@Autowired
	private ActivityService	activityService;


	// Constructors -----------------------------------------------------------

	public ActivityController() {
		super();
	}

	//Actions

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam Integer a) {
		ModelAndView result;

		result = new ModelAndView("activity/list");

		result.addObject("a", a);
		result.addObject("activities", activityService.findAll());

		return result;
	}

	@RequestMapping("/listByGym")
	public ModelAndView listByGym(@RequestParam Gym q) {
		ModelAndView result;

		List<Activity> activities = new ArrayList<Activity>();
		activities.addAll(q.getActivities());

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("a", 1);

		return result;
	}

	@RequestMapping("/listByGym2")
	public ModelAndView listByGym2(@RequestParam Gym q) {
		ModelAndView result;

		List<Activity> activities = new ArrayList<Activity>();
		activities.addAll(q.getActivities());

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("a", 1);
		return result;
	}

	@RequestMapping("/listByGym3")
	public ModelAndView listByGym3(@RequestParam Gym q) {
		ModelAndView result;

		List<Activity> activities = new ArrayList<Activity>();
		activities.addAll(q.getActivities());

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("a", 2);
		return result;
	}

	@RequestMapping("/addTrainer")
	public ModelAndView addTrainer(@RequestParam Activity q) {
		ModelAndView result;

		Gym g = q.getGym();
		List<Trainer> trainers = g.getTrainers();
		trainers.removeAll(q.getTrainers());

		result = new ModelAndView("trainer/list");
		result.addObject("trainers", trainers);
		result.addObject("a", 2);

		actual = q.getId();
		return result;
	}

	@RequestMapping("/addToActivity")
	public ModelAndView addToActivity(@RequestParam Trainer q) {
		ModelAndView result;

		Trainer trainer = q;
		Activity activity = activityService.findOne(actual);
		try {
			List<Trainer> list = activity.getTrainers();
			list.add(trainer);
			activity.setTrainers(list);
			activityService.save(activity);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (Throwable e) {
			Gym g = activity.getGym();
			List<Trainer> trainers = g.getTrainers();
			trainers.removeAll(activity.getTrainers());
			result = new ModelAndView("trainer/list");
			result.addObject("trainers", trainers);
			result.addObject("a", 2);
		}
		actual = null;
		return result;

	}

	@RequestMapping("/cancel")
	public ModelAndView cancel(@RequestParam Activity q) {
		ModelAndView result;

		Activity a = q;

		try {
			activityService.delete(a);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Activity activity, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("activity/create");
			result.addObject("activity", activity);
			result.addObject("message", "activity.commit.error");
		} else {
			try {
				System.out.println(activity.getPictures().get(0));
				activityService.save(activity);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable e) {
				result = new ModelAndView("activity/create");
				result.addObject("activity", activity);
				result.addObject("message", "activity.commit.error");
			}
		}

		return result;
	}

	@RequestMapping("/avgStar")
	public ModelAndView avgStar(@RequestParam Activity q) {
		ModelAndView result;

		result = new ModelAndView("activity/avgStar");

		result.addObject("avgStar", activityService.avgStar(q));

		return result;
	}

}
