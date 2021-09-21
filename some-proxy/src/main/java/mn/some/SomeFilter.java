package mn.some;

import io.micronaut.http.annotation.Filter;
import io.micronaut.http.client.ProxyHttpClient;
import io.micronaut.http.filter.FilterChain;
import io.micronaut.http.filter.HttpFilter;

import static io.micronaut.http.annotation.Filter.MATCH_ALL_PATTERN;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;

@Filter(MATCH_ALL_PATTERN)
public class SomeFilter implements HttpFilter {

	private String remoteBaseUrl;
	private String remoteHost;
	private ProxyHttpClient proxyClient;
	private Logger log = LoggerFactory.getLogger(SomeFilter.class);

	public SomeFilter(
		@Value("${remote.url}") String remoteBaseUrl,
		@Value("${remote.host}") String remoteHost
	) throws MalformedURLException, URISyntaxException {
		log.info("Creating SomeFilter...");
		this.remoteBaseUrl = remoteBaseUrl;
		this.remoteHost = remoteHost;
		this.proxyClient = ProxyHttpClient.create(new URI(this.remoteBaseUrl).toURL());
	}

	@Override
	public Publisher<? extends HttpResponse<?>> doFilter(HttpRequest<?> request, FilterChain chain) {
		MutableHttpRequest<?> mutableReq = request instanceof MutableHttpRequest ? (MutableHttpRequest<?>) request : request.mutate();

		log.info("Received request: " + request.getUri());
		log.info(request.getPath());

		mutableReq.getHeaders().remove(HttpHeaders.HOST);
		mutableReq.getHeaders().set(HttpHeaders.HOST, remoteHost);

		return proxyClient.proxy(request);
	}

}
