package laptimesimulator.ddd;

import java.util.List;
import java.util.Optional;

public interface IRepository<ID extends IDomainID, T extends IAggregateRoot<ID>> {

  /**
   * Method to save a domain entity.
   *
   * @param entity is the domain entity to be saved.
   * @return the saved domain entity.
   */
  T save(T entity);

  /**
   * Method to find all domain entities.
   *
   * @return the list of domain entities.
   */
  List<T> findAll();

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  Optional<T> ofIdentity(ID objectID);

  /**
   * Method to check if a domain entity exists by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return true if the domain entity exists, false otherwise.
   */
  boolean containsOfIdentity(ID objectID);

}