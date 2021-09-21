package mn.some.service;

import javax.validation.constraints.NotNull;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Request {
	@NotNull
	public String name;
}
