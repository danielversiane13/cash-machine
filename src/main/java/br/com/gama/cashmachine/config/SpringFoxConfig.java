package br.com.gama.cashmachine.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
                .apis(RequestHandlerSelectors.basePackage("br.com.gama.cashmachine"))
				.paths(PathSelectors.regex("/current-account.*|/machines.*|/money-bills.*"))
				.build()
				.apiInfo(metaInfo());
	}

	@SuppressWarnings("rawtypes")
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"CashMachine",
				"CashMachine API documentation",
				"1.0",
				null,
				new Contact("Daniel Versiane", "", "daniel.versiane@gmail.com"),
				"Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0",
				new ArrayList<VendorExtension>()
		);

		return apiInfo;
	}

}
