package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Workout;

@Component
@Transactional
public class WorkoutToStringConverter implements Converter<Workout, String> {

	@Override
	public String convert(Workout ar) {
		String res;
		if (ar == null) {
			res = null;
		} else {
			res = String.valueOf(ar.getId());
		}
		return res;
	}
}
