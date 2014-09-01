package util.transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang.StringUtils;

public class EntityManagerHolder {

	private Random random = new Random();

	private String PERSISTENCE_UNIT_HEADER_MASTER = "MASTER_";

	private String PERSISTENCE_UNIT_HEADER_SLAVE = "SLAVE_";

	private final ThreadLocal<EntityManager> THREAD_LOCAL = new ThreadLocal<EntityManager>();

	private final Map<String, EntityManagerFactory> RESOURCE = new HashMap<String, EntityManagerFactory>();

	private static EntityManagerHolder emHolder;

	private EntityManagerHolder() {
	}

	public static EntityManagerHolder getInstance() {
		if (null == emHolder) {
			emHolder = new EntityManagerHolder();
		}
		return emHolder;
	}

	EntityManager initializeResource(boolean isReadOnly) {
		// get random persistence unit, dependents on master and slave
		// quantities
		// configured in persistence.xml
		String puSuffix = "";
		if (isReadOnly) {
			puSuffix = StringUtils.leftPad(String.valueOf(random.nextInt(1)),
					3, "0");
		} else {
			puSuffix = StringUtils.leftPad(String.valueOf(random.nextInt(1)),
					3, "0");
		}

		String persistenceUnit = (isReadOnly ? PERSISTENCE_UNIT_HEADER_SLAVE
				: PERSISTENCE_UNIT_HEADER_MASTER) + puSuffix;

		return createEntityManager(persistenceUnit);
	}

	private EntityManager createEntityManager(String persistenceUnit) {
		if (null == RESOURCE.get(persistenceUnit)) {
			EntityManagerFactory entityManagerFactory = Persistence
					.createEntityManagerFactory(persistenceUnit);
			RESOURCE.put(persistenceUnit, entityManagerFactory);
		}

		EntityManager entityManager = THREAD_LOCAL.get();

		if (null == entityManager || !entityManager.isOpen()) {
			entityManager = RESOURCE.get(persistenceUnit).createEntityManager();
			THREAD_LOCAL.set(entityManager);
		}

		return entityManager;
	}

	public EntityManager getEntityManager() {
		EntityManager entityManager = THREAD_LOCAL.get();
		if (null == entityManager) {
			throw new IllegalArgumentException();
		}
		return entityManager;
	}

	public void closeEntityManager() {
		EntityManager entityManager = THREAD_LOCAL.get();
		THREAD_LOCAL.set(null);
		if (null != entityManager)
			entityManager.close();
	}

}
