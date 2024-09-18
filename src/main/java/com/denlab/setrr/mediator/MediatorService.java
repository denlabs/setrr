package com.denlab.setrr.mediator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class MediatorService implements Mediator {

    private final ApplicationContext context;

    private final Map<Class<?>, RequestHandler<?, ?>> requestHandlers;

    public MediatorService(ApplicationContext context) {

        this.context = context;
        requestHandlers = new HashMap<>();
    }

    @PostConstruct
    protected void postConstruct() throws IllegalStateException {
        final String[] names = context.getBeanNamesForType(RequestHandler.class);
        for (String name : names) {
            registerHandler(name);
        }
    }

    @SuppressWarnings("unchecked")
    public <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest request) {

        RequestHandler<TRequest, TResponse> handler = (RequestHandler<TRequest, TResponse>) requestHandlers.get(request.getClass());

        if (isNull(handler)) {
            log.error("No handler found for request: {}", request.getClass());
        }
        return handler.handle(request);
    }

    private void registerHandler(String name) throws IllegalStateException {

        RequestHandler<?, ?> handler = (RequestHandler<?, ?>) context.getBean(name);
        final Class<?>[] typeArguments = GenericTypeResolver.resolveTypeArguments(handler.getClass(), RequestHandler.class);

        assert typeArguments != null;

        Class<?> requestType = typeArguments[0];
        if (!requestHandlers.containsKey(requestType)) {
            requestHandlers.put(requestType, handler);
            log.info("Register handler {} for request {}", handler.getClass().getSimpleName(), requestType.getSimpleName());
        } else {
            log.error("Error registering handler {} for request {}; it's already in the registry", handler.getClass().getSimpleName(),
                    requestType.getSimpleName());
            throw new IllegalStateException();
        }

    }

}
