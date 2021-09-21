package mn.some.service.client;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

@Client("${some.service.url}")
public interface SomeServiceClient {

	@Get("/message?name={name}")
	public MessageResponse getFromGet(String name);

	@Post("/message")
	public MessageResponse getFromPost(@Body MessageRequest req);

}
