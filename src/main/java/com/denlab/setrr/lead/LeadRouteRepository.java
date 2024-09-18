package com.denlab.setrr.lead;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRouteRepository extends CrudRepository<LeadRoute, Long> {
}
