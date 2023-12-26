package com.challenge.app;

import com.challenge.app.entity.Exchange;
import com.challenge.app.entity.MoneyExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppApplicationTests {

	@Autowired
	WebTestClient client;

	@Test
	public void testCrearExchange() {

		Exchange exchange = new Exchange();
		exchange.setExchangeRate(0.22);
		exchange.setOrigenCurrency("PEN");
		exchange.setDestinyCurrency("MXN");

		client
				.post()
				.uri("/api/exchange")
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpc3MiOiJhZG1pbiIsInN1YiI6IjY1N2FhYWMxZjQyZTRmMjA0YTkwOWMwYyIsImlhdCI6MTcwMjYyNDE3OSwianRpIjoiYmJjZTRmNTktOTA3ZC00YmFhLWJjZTMtMjY2M2FlZDUzMjc2IiwiZXhwIjoxNzAyNjUyOTc5fQ.BrS2Irg3wCERhe0Q4hei7B8cercSX8fzRbCYIU5kBfM")
				.accept(MediaType.APPLICATION_JSON)
				.body(fromValue(exchange))
				.exchange()
				.expectStatus()
				.isCreated()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON)
				.expectBody(Exchange.class)
				.consumeWith(response -> {
					Exchange e = response.getResponseBody();
					Assertions.assertNotNull(e.getId());
				});

	}

	@Test
	public void listarExchange() {
		client
				.get()
				.uri("/api/exchange")
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpc3MiOiJhZG1pbiIsInN1YiI6IjY1N2FhYWMxZjQyZTRmMjA0YTkwOWMwYyIsImlhdCI6MTcwMjYyNDE3OSwianRpIjoiYmJjZTRmNTktOTA3ZC00YmFhLWJjZTMtMjY2M2FlZDUzMjc2IiwiZXhwIjoxNzAyNjUyOTc5fQ.BrS2Irg3wCERhe0Q4hei7B8cercSX8fzRbCYIU5kBfM")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isOk()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Exchange.class)
				.hasSize(3);
	}

	@Test
	public void verExchange() {
		client
				.get()
				.uri("/api/exchange/PEN/USD")
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpc3MiOiJhZG1pbiIsInN1YiI6IjY1N2FhYWMxZjQyZTRmMjA0YTkwOWMwYyIsImlhdCI6MTcwMjYyNDE3OSwianRpIjoiYmJjZTRmNTktOTA3ZC00YmFhLWJjZTMtMjY2M2FlZDUzMjc2IiwiZXhwIjoxNzAyNjUyOTc5fQ.BrS2Irg3wCERhe0Q4hei7B8cercSX8fzRbCYIU5kBfM")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isOk()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON)
				.expectBody(Exchange.class);
	}


	@Test
	public void testExchange() {

		MoneyExchange moneyExchange = new MoneyExchange();
		moneyExchange.setAmount(500.0);
		moneyExchange.setOrigenCurrency("PEN");
		moneyExchange.setDestinyCurrency("USD");

		client
				.post()
				.uri("/api/exchange/amount")
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpc3MiOiJhZG1pbiIsInN1YiI6IjY1N2FhYWMxZjQyZTRmMjA0YTkwOWMwYyIsImlhdCI6MTcwMjYyNDE3OSwianRpIjoiYmJjZTRmNTktOTA3ZC00YmFhLWJjZTMtMjY2M2FlZDUzMjc2IiwiZXhwIjoxNzAyNjUyOTc5fQ.BrS2Irg3wCERhe0Q4hei7B8cercSX8fzRbCYIU5kBfM")
				.accept(MediaType.APPLICATION_JSON)
				.body(fromValue(moneyExchange))
				.exchange()
				.expectStatus()
				.isOk()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON)
				.expectBody(MoneyExchange.class)
				.consumeWith(response -> {
					MoneyExchange me = response.getResponseBody();
					Assertions.assertEquals(me.getAmountExchange(), 1865.0);
				});

	}


	@Test
	public void testUpdateExchange() {

		Exchange exchange = new Exchange();
		exchange.setExchangeRate(0.24);
		exchange.setOrigenCurrency("PEN");
		exchange.setDestinyCurrency("MXN");

		client
				.put()
				.uri("/api/exchange/{id}", Collections.singletonMap("id", "657bff1ffba8972b5ccaf02d"))
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbIlJPTEVfVVNFUiJdLCJpc3MiOiJhZG1pbiIsInN1YiI6IjY1N2FhYWMxZjQyZTRmMjA0YTkwOWMwYyIsImlhdCI6MTcwMjYyNDE3OSwianRpIjoiYmJjZTRmNTktOTA3ZC00YmFhLWJjZTMtMjY2M2FlZDUzMjc2IiwiZXhwIjoxNzAyNjUyOTc5fQ.BrS2Irg3wCERhe0Q4hei7B8cercSX8fzRbCYIU5kBfM")
				.accept(MediaType.APPLICATION_JSON)
				.body(fromValue(exchange))
				.exchange()
				.expectStatus()
				.isCreated()
				.expectHeader()
				.contentType(MediaType.APPLICATION_JSON)
				.expectBody(Exchange.class)
				.consumeWith(response -> {
					Exchange e = response.getResponseBody();
					Assertions.assertEquals(e.getExchangeRate(), exchange.getExchangeRate());
				});

	}


}
