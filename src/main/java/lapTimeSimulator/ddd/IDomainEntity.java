package lapTimeSimulator.ddd;

public interface IDomainEntity<ID extends IDomainID> {


  /**
   * Method to get the unique identifier of the domain entity.
   *
   * @return the unique identifier of the domain entity.
   */
  ID getId();

  /**
   * Method to check if the domain entity is equal to another object.
   *
   * @param o is the object to be compared.
   * @return true if the domain entity is equal to the object, false otherwise.
   */
  boolean equals(Object o);
}
