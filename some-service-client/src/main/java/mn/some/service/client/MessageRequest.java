package mn.some.service.client;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class MessageRequest {
	public String name;

	public MessageRequest(String name) {
		this.name = name;
	}
}
