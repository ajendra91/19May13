package com.example.Vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

class MyClient {

	public static void main(String[] args) {
		
		Vertx vertx=Vertx.vertx();
		WebClient client =WebClient.create(vertx);
		client.get(9098, "localhost", "/myget").send(response->{
			if(response.succeeded()) {
				HttpResponse<Buffer> res=response.result();
				System.out.println(res.bodyAsString());
			}else {
				System.out.println(response.cause().getMessage());
			}
		});
		
	
	}

}
