package it.polimi.awt.mpcs.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.polimi.awt.mpcs.domain.MountainPhoto;
import it.polimi.awt.mpcs.repository.PhotoRepository;

@Repository
public class PhotoRepositoryJPAImpl implements PhotoRepository {

	@PersistenceContext
	private EntityManager em;

	public PhotoRepositoryJPAImpl() {

	}

	@Transactional
	public void savePhoto(MountainPhoto photo) {

		try {
			em.persist(photo);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public void deletePhoto() {
		// TODO Auto-generated method stub

	}

	@Transactional
	public MountainPhoto getPhotoByID(int id) {

		MountainPhoto mountain = null;

		TypedQuery<MountainPhoto> query = em.createNamedQuery(
				"MountainPhoto.findById", MountainPhoto.class);

		query.setParameter("id", id);

		try {
			mountain = query.getSingleResult();
		} catch (NoResultException nre) {
			System.out.println("NoResultException photo does not exists");
		}

		return mountain;
	}

	@Transactional
	public void savePhotos(List<MountainPhoto> mountains) {
		try {
			for (MountainPhoto mountain : mountains) {

				MountainPhoto mountainSource = null;

				TypedQuery<MountainPhoto> query = em.createNamedQuery(
						"MountainPhoto.findBySource", MountainPhoto.class);

				query.setParameter("source", mountain.getSource());

				try {
					mountainSource = query.getSingleResult();
				} catch (NoResultException nre) {
					System.out.println("NoResultException new photo finded");
				}

				if (mountainSource == null) {

					em.persist(mountain);

				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Transactional
	public List<MountainPhoto> findAll() {

		List<MountainPhoto> mountains = new ArrayList<MountainPhoto>();

		TypedQuery<MountainPhoto> query = em.createNamedQuery(
				"MountainPhoto.findAll", MountainPhoto.class);

		try {
			mountains = query.getResultList();
		} catch (NoResultException nre) {
			System.out.println("NoResultException no photos in the gallery");
		}

		return mountains;
	}

	@Transactional
	public void updatePhoto(MountainPhoto photo) {
		try {
			em.merge(photo);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Transactional
	public List<MountainPhoto> findSaved() {

		List<MountainPhoto> mountains = new ArrayList<MountainPhoto>();

		TypedQuery<MountainPhoto> query = em.createNamedQuery(
				"MountainPhoto.findSaved", MountainPhoto.class);

		try {
			mountains = query.getResultList();
		} catch (NoResultException nre) {
			System.out.println("NoResultException no photos in the gallery");
		}

		return mountains;
	}

}
