package com.denlab.setrr.mediator;

public interface RequestHandler<TRequest extends Request<TResponse>, TResponse> {

    TResponse handle(TRequest command);

}
