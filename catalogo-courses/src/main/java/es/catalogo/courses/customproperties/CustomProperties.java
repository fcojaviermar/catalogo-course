package es.catalogo.courses.customproperties;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:catalogue.properties")
public class CustomProperties {
	
	
}
