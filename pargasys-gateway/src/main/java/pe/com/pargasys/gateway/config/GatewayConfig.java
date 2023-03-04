package pe.com.pargasys.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pe.com.pargasys.gateway.service.GatewayService;

@Configuration
public class GatewayConfig {

    @Autowired
    private GatewayService gatewayService;

    @Bean
    @Profile("neu") /* No eureka */
    public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/trackings/**", "/charges/**")
                        .uri("http://localhost:8081/"))
                .build();
    }

    @Bean
    @Profile("eur") /* Eureka */
    public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/trackings/**", "/charges/**")
                        .filters(f -> f.filter(gatewayService))
                        .uri("lb://pargasys-backend"))
                .route(r -> r.path("/auth/**")
                        .uri("lb://pargasys-auth"))
                .build();
    }

    @Bean
    @Profile("ecb") /* Eureka circuit breaker */
    public RouteLocator configLocalEurekaCB(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/trackings/**", "/charges/**")
                        /*
                        .filters(f -> {
                               f.circuitBreaker(c -> c.setName("failoverCB").setFallbackUri("forware:/charges/**").setRouteId("failover"));
                               f.filter(gatewayService);
                               return f;
                        })
                        */
                        .uri("lb://pargasys-backend"))
                .build();
    }

}
