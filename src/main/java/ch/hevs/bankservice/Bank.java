package ch.hevs.bankservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.Account;

@Local
public interface Bank {
	
	public String transfer(Account compteSrc, Account compteDest, int montant) throws Exception;

	public Account getAccount(String accountDescription, String lastnameOwner);
	
	public List<Account> getAccountListFromClientLastname(String lastname);

	public List<Client> getClients();

	public Client getClient(long clientid);
}
