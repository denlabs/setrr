package com.denlab.setrr.mediator;

public interface Mediator {

    <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest request);
}
