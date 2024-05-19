package lapTimeSimulator.ddd;

public interface IDomainID extends IValueObject {

  /**
   * Method to get the unique identifier of the domain entity.
   *
   * @return the unique identifier of the domain entity.
   */
  String getId();

  /**
   * Method to check if the domain entity is equal to another object.
   *
   * @param o is the object to be compared.
   * @return true if the domain entity is equal to the object, false otherwise.
   */
  boolean equals(Object o);

  /**
   * Method to get the hash code of the domain entity.
   *
   * @return the hash code of the domain entity.
   */
  int hashCode();


}
