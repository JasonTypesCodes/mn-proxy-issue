package mn.some.service.client;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Response {
	public String message1;
	public String message2;

	public Response(String message1, String message2) {
		this.message1 = message1;
		this.message2 = message2;
	}
}
