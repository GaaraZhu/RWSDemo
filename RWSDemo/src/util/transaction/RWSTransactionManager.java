package util.transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class RWSTransactionManager extends AbstractPlatformTransactionManager {

	private static final long serialVersionUID = -1369860968344021154L;

	@Override
	protected Object doGetTransaction() throws TransactionException {
		return new RWSJPATransactionObject();
	}

	@Override
	protected void doBegin(Object txObject, TransactionDefinition definition)
			throws TransactionException {
		RWSJPATransactionObject rwsTransaction = (RWSJPATransactionObject) txObject;
		EntityManager entityManager = EntityManagerHolder.getInstance()
				.initializeResource(definition.isReadOnly());
		EntityTransaction transaction = entityManager.getTransaction();
		rwsTransaction.setTransaction(transaction);

		if (!definition.isReadOnly()) {
			rwsTransaction.getTransaction().begin();
		}
	}

	@Override
	protected void doCommit(DefaultTransactionStatus transactionStatus)
			throws TransactionException {
		if (transactionStatus.isReadOnly()) {
			return;
		}

		RWSJPATransactionObject rwsTransaction = (RWSJPATransactionObject) transactionStatus
				.getTransaction();
		rwsTransaction.getTransaction().commit();
	}

	@Override
	protected void doRollback(DefaultTransactionStatus transactionStatus)
			throws TransactionException {
		if (transactionStatus.isReadOnly()) {
			return;
		}

		RWSJPATransactionObject rwsTransaction = (RWSJPATransactionObject) transactionStatus
				.getTransaction();
		rwsTransaction.getTransaction().rollback();
	}

	private class RWSJPATransactionObject {
		private EntityTransaction transaction;

		public EntityTransaction getTransaction() {
			return transaction;
		}

		public void setTransaction(EntityTransaction transaction) {
			this.transaction = transaction;
		}

		public RWSJPATransactionObject() {
			super();
		}
	}

}
