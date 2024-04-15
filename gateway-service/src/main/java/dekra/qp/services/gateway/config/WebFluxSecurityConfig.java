package dekra.qp.services.gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebFluxSecurityConfig {

//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.authorizeExchange(authorize -> authorize
//                        .pathMatchers("/v3/api-docs/**", "/swagger-ui/**", "/webjars/**", "/swagger-resources/**").permitAll()  // Permitir acceso a Swagger UI y definiciones de API sin autenticación
//                        .pathMatchers(HttpMethod.GET, "/product/v3/api-docs/**").authenticated()
//                        .pathMatchers(HttpMethod.GET, "/category/v3/api-docs/**").authenticated()
//                        .pathMatchers(HttpMethod.GET, "/inventory/v3/api-docs/**").authenticated()
//                        .pathMatchers("/inventory/**").hasAuthority("SCOPE_manager")
//                        .pathMatchers("/category/**").hasAuthority("SCOPE_manager")
//
//                        .anyExchange().authenticated())
//                .oauth2Login(withDefaults())
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//
//        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
//
//        return http.build();
//    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(auth -> auth
                        .pathMatchers("/v3/api-docs/**", "/swagger-ui/**", "/webjars/**", "/swagger-resources/**", "/favicon.ico").permitAll()
                        // Configurar acceso autenticado para la documentación API de cada servicio
                        .pathMatchers(HttpMethod.GET, "/product/v3/api-docs/**", "/inventory/v3/api-docs/**", "/category/v3/api-docs/**").permitAll()
                        // Control de acceso para operaciones normales
                        .pathMatchers("/product/**").hasAuthority("SCOPE_manager")
                        .pathMatchers("/inventory/**").hasAuthority("SCOPE_manager")
                        .pathMatchers("/category/**").hasAuthority("SCOPE_manager")
                        .anyExchange().authenticated())
                .oauth2Login(withDefaults())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:8060");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8060").description("API Gateway"))
                .info(new Info().title("API Gateway")
                        .version("v1")
                        .description("This is the gateway for all microservices"));
    }
}
