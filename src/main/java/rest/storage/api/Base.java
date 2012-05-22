package rest.storage.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.datastore.EntityNotFoundException;

import rest.storage.api.exception.OwnerNotFoundException;
import rest.storage.api.model.Owner;
import rest.storage.api.repository.OwnerRepository;

public class Base {
	static Logger log = LoggerFactory.getLogger(Base.class);
	
	public final Owner getCurrentOwner() throws OwnerNotFoundException {
		Owner pwalter = new Owner();
		pwalter.setId(4711);
		pwalter.setIdentifier("mail@pascalwalter.de");
		pwalter.setName("Pascal Walter");
		
		Owner owner = pwalter;
		/*try {
			// TODO: remote this static Identifier and replace with some context data
			owner = OwnerRepository.getByIdentifier("mail@pascalwalter.de");
		} catch (EntityNotFoundException e) {
			
			Owner pwalter = new Owner();
			pwalter.setId(4711);
			pwalter.setIdentifier("mail@pascalwalter.de");
			pwalter.setName("Pascal Walter");
			OwnerRepository.save(pwalter);
			
			log.info("Owner created with Identifier " + pwalter.getIdentifier());
			
			throw new OwnerNotFoundException();
		}*/
		
		return owner;
	}
}
