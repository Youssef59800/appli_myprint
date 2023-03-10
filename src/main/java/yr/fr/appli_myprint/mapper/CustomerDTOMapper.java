package yr.fr.appli_myprint.mapper;

import org.springframework.stereotype.Component;
import yr.fr.appli_myprint.dto.CustomerDTO;
import yr.fr.appli_myprint.model.PersonneEntity;

@Component
public class CustomerDTOMapper {

    public CustomerDTO toDto(PersonneEntity customer) {
        return new CustomerDTO(
                customer.getIdPersonne(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getRoles(),
                customer.getAdresseList()
        );
    }

    public PersonneEntity toCustomer(CustomerDTO customerDTO) {
        return new PersonneEntity(customerDTO.getIdPersonne(),
                                  customerDTO.getFirstName(),
                                  customerDTO.getLastName(),
                                  customerDTO.getEmail(),
                                  customerDTO.getRoles(),
                                  customerDTO.getAdresseList());
    }
}
