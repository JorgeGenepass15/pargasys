package pe.com.pargasys.gateway.service;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import pe.com.pargasys.gateway.constant.Constant;
import pe.com.pargasys.gateway.exception.BadRequestException;
import pe.com.pargasys.gateway.model.dto.TokenDTO;
import reactor.core.publisher.Mono;

@Service
public class GatewayService implements GatewayFilter {

    @Value("${pargasys.auth.validate}")
    private String validate;
    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new BadRequestException(Constant.BR_FILTER);
        }
        String authToken = exchange.getRequest()
                .getHeaders()
                .get(org.springframework.http.HttpHeaders.AUTHORIZATION)
                .get(0);
        String[] chunks = authToken.split(Constant.SPACE);
        if (chunks.length != 2 || !chunks[0].equals(Constant.BEARER)) {
            throw new BadRequestException(Constant.BR_FILTER);
        }
        return webClient.build()
                .post()
                .uri(validate.concat(chunks[1]))
                .retrieve()
                .bodyToMono(TokenDTO.class)
                .map(t ->  exchange)
                .flatMap(chain::filter);
    }

}
