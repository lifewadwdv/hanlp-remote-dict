/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import utils.ExtDictionary;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class SampleWebFluxApplication {


	private static ExtDictionary extDictionary;
	/**
	 * 入口，注意 ExtDictionary.getSingleton().getRemoteExtDictionarys().stream().forEach(System.out::println);不可少
	 * 只有getSingleton()构造实例时才会启动异步加载词库任务
	 */
	public static void main(String[] args) {
		extDictionary=ExtDictionary.getSingleton();
		extDictionary.getRemoteExtDictionarys().stream().forEach(System.out::println);
		SpringApplication.run(SampleWebFluxApplication.class);
	}

	@Bean
	public RouterFunction<ServerResponse> monoRouterFunction(EchoHandler echoHandler) {
		//这一步不可少
		return route(POST("/echo"), echoHandler::echo);
	}

}
