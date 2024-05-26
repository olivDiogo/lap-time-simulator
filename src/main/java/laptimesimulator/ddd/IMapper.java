package laptimesimulator.ddd;

import java.util.List;

public interface IMapper <ID extends IAggregateRoot, T>{
    /**
     * Method to convert a domain entity into a DTO.
     *
     * @param domainEntity is the domain entity to be converted.
     * @return the DTO.
     */
    T toDTO(ID domainEntity);

    /**
     * Method to convert a list of domain entities into a list of DTOs.
     *
     * @param domainEntities is the list of domain entities to be converted.
     * @return the list of DTOs.
     */
    List<T> toDTO(List<ID> domainEntities);
}
