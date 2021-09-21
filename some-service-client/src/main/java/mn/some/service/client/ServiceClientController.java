package mn.some.service.client;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class ServiceClientController {

	private SomeServiceClient client;

	public ServiceClientController(SomeServiceClient client) {
		this.client = client;
	}

	@Get
	public Response getAllMessages() {

		String message1 = client.getFromGet("From Get").message;
		String message2 = client.getFromPost(new MessageRequest("From Post")).message;

		return new Response(message1, message2);
	}
}
