package com.denlab.setrr.lead;

import java.util.List;

public class DomainEntity<T> {
    private List<DomainEvent<? extends T>> domainEvents;
}
