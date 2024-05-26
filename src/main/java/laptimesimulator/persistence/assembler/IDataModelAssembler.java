package laptimesimulator.persistence.assembler;

import java.util.List;

public interface IDataModelAssembler <ID, T>{
    /**
     * Method to convert a data model into a domain entity.
     *
     * @param dataModel is the data model to be converted.
     * @return the domain entity.
     */
    ID toDomain(T dataModel);

    /**
     * Method to convert a list of data models into a list of domain entities.
     *
     * @param dataModels is the list of data models to be converted.
     * @return the list of domain entities.
     */
    List<ID> toDomain(List<T> dataModels);
}
