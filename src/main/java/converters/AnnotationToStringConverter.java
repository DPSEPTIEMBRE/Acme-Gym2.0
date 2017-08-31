
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Annotation;

@Component
@Transactional
public class AnnotationToStringConverter implements Converter<Annotation, String> {

	@Override
	public String convert(Annotation ar) {
		String res;
		if (ar == null) {
			res = null;
		} else {
			res = String.valueOf(ar.getId());
		}
		return res;
	}
}