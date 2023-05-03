package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	  private static final String splitor = ";"; 
	
	 public Docket createDocket(String groupName, ApiInfo apiInfo, String basePackage){
	      return new Docket(DocumentationType.SWAGGER_2)
	             .groupName(groupName)
	             .apiInfo(apiInfo)
	             .select()
	             .apis(basePackage(basePackage))
	             .paths(PathSelectors.any())
	             .build();
	   }
	   @Bean
	   public Docket createFrontEndDocket() {
	      return createDocket("前後臺會員中心", ecommerceApiInfo(), "com.ecommerce.controller");
	   }

//	   @Bean
//	   public Docket createBackEndDocket(){
//	      return createDocket("後臺供應商", backEndApiInfo(), "");
//	   }

	   private ApiInfo ecommerceApiInfo() {
	      return new ApiInfoBuilder()
	             .title("FrontEnd&BackEnd SpringBoot API")
	             .description("FrontEnd&BackEnd SpringBoot API 文件")
	             .version("1.0")
	             .build();
	   }  

//	   private ApiInfo backEndApiInfo() {
//	      return new ApiInfoBuilder()
//	             .title("BackEndful API")
//	             .description("BackEndRestful API 文件")
//	             .version("1.0")
//	             .build();
//	   }  
	   public static Predicate<RequestHandler> basePackage(final String basePackage) {
		      return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
		   }

		   private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
		      return input -> {
		          for (String strPackage : basePackage.split(splitor)) {
		             boolean isMatch = input.getPackage().getName().startsWith(strPackage);
		             if (isMatch) {
		                return true;
		             }
		          }
		          return false;
		      };
		   }  

		   private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
		      return Optional.fromNullable(input.declaringClass());
		   }  
}
