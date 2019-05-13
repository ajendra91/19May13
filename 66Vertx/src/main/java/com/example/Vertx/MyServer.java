package com.example.Vertx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

//@SpringBootApplication
class JavaTechVertxApplication {

	public static void main(String[] args) {
		//SpringApplication.run(JavaTechVertxApplication.class, args);

		System.out.println("Hello World!");

		// Create Vertex
		Vertx vertx = Vertx.vertx();

		// create HTTP Server
		HttpServer server = vertx.createHttpServer();

		// create Router
		Router router = Router.router(vertx);

		/*Route handler1=router.route().handler(
				routingContext -> {
				HttpServerResponse serverResponse = routingContext.response();
				serverResponse.setChunked(true).write("welcome message hit...").end();
			});

		Route handler2=router.route("/app").handler(
				routingContext -> {
				HttpServerResponse serverResponse = routingContext.response();
				serverResponse.setChunked(true).write("app hit....").end();
			}); */

		Route handler3=router.get("/myget").handler(
				routingContext -> {
					HttpServerResponse serverResponse = routingContext.response();
					serverResponse.setChunked(true).write("myget get hit...").end();
				});
		//post
		Route handler4=router.get("/myapp").handler(
				routingContext -> {
					HttpServerResponse serverResponse = routingContext.response();
					serverResponse.setChunked(true).write("myapp get hit...").end();
				});



		//Route handler1 = router.post("/getMessage").produces("*/json").handler(routingContext -> {
		//	HttpServerResponse serverResponse = routingContext.response();
		//	serverResponse.setChunked(true).end(Json.encodePrettily(new User(567, "Java Techie")));
		//});



		server.requestHandler(router::accept).listen(9098);

	}




}
