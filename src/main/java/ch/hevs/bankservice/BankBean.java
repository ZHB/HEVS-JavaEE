package ch.hevs.bankservice;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.Account;

@Stateful
public class BankBean implements Bank {
	
	@PersistenceContext(name = "BankPU", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	public String transfer(Account srcAccount, Account destAccount, int amount) {

		em.persist(srcAccount);
		em.persist(destAccount);
		srcAccount.debit(amount);
		destAccount.credit(amount);
				
		String transactionResult="Success!";
	
		return transactionResult;
	}

	public Account getAccount(String accountDescription, String lastnameOwner) {
		Query query = em.createQuery("FROM Account a WHERE a.description=:description AND a.owner.lastname=:lastname");
		query.setParameter("description", accountDescription);
		query.setParameter("lastname", lastnameOwner);
		
		return (Account) query.getSingleResult();
	}
	
	public List<Account> getAccountListFromClientLastname(String lastname) {
		return (List<Account>) em.createQuery("SELECT c.accounts FROM Client c where c.lastname=:lastname").setParameter("lastname", lastname).getResultList();
	}

	public List<Client> getClients() {
		return em.createQuery("FROM Client").getResultList();
	}
	
	public Client getClient(long clientid) {
		return (Client) em.createQuery("FROM Client c where c.id=:id").setParameter("id", clientid).getSingleResult();
	}
}
