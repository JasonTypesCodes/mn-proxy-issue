package mn.some.service;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Response {
	public String message;

	public Response(String name) {
		this.message = "Hello, " + name + "!";
	}
}
