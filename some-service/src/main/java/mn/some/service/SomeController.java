package mn.some.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("/message")
public class SomeController {

	@Get
	public Response getMessage(@Parameter @NotNull String name) {
		return new Response(name);
	}

	@Post
	public Response getMessageFromPost(@Body @Valid Request body) {
		return new Response(body.name);
	}
}
