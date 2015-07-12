package it.polimi.awt.mpcs.repository;

import java.util.List;

import it.polimi.awt.mpcs.domain.SeedQuery;

public interface SeedQueryRepository {
	
	List<SeedQuery> getAllSeedQueries();
	
	void saveSeedQueries(List<SeedQuery> queries);

}
