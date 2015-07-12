package it.polimi.awt.mpcs.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.awt.mpcs.domain.MountainPhoto;
import it.polimi.awt.mpcs.domain.SeedQuery;
import it.polimi.awt.mpcs.repository.SeedQueryRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SeedQueryRepositoryJPAImpl implements SeedQueryRepository {

	@PersistenceContext
	private EntityManager em;

	public SeedQueryRepositoryJPAImpl() {

	}
	
	@Transactional
	public List<SeedQuery> getAllSeedQueries() {

		List<SeedQuery> seeds = new ArrayList<SeedQuery>();

		TypedQuery<SeedQuery> query = em.createNamedQuery(
				"SeedQuery.findAll", SeedQuery.class);

		try {
			seeds = query.getResultList();
		} catch (NoResultException nre) {
			System.out.println("NoResultException no seeds saved");
		}
		
		//invertiamo la lista in modo che le seed più recenti vengano gestite per prime
        Collections.reverse(seeds);
		return seeds;
		
	}
	
	@Transactional
	public void saveSeedQueries(List<SeedQuery> queries) {

		for (SeedQuery seedquery : queries) {

			SeedQuery seedName = null;

			TypedQuery<SeedQuery> query = em.createNamedQuery(
					"SeedQuery.findByName", SeedQuery.class);

			query.setParameter("name", seedquery.getName());

			try {
				seedName = query.getSingleResult();
			} catch (NoResultException nre) {
				System.out.println("NoResultException seed is not yet saved");
			}

			if (seedName == null) {
				em.persist(seedquery);
			}
		}

		
	}

}
