package pl.zoltowskimarcin.petclinic.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zoltowskimarcin.petclinic.repository.entity.Address;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

import java.util.List;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;

class ClientMapperTest {

    @Test
    void map_from_model_to_entity() {
        //given
        Address address = new Address(CLIENT_JON_ADDRESS_STREET, CLIENT_JON_ADDRESS_CITY, CLIENT_JON_ADDRESS_POSTAL_CODE);
        ClientDto clientDto = new ClientDto(CLIENT_NAME_JON, CLIENT_SURNAME_SNOW, CLIENT_JON_PHONE_123_456_789, address, null, null);

        //when
        Client mappedClientEntity = new ClientMapper().mapToEntity(clientDto);
        String mappedName = mappedClientEntity.getName();
        String mappedSurname = mappedClientEntity.getSurname();
        String mappedPhone = mappedClientEntity.getPhone();
        Address mappedAddress = mappedClientEntity.getAddress();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(CLIENT_NAME_JON, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_SNOW, mappedSurname, "Surname is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_JON_PHONE_123_456_789, mappedPhone, "Phone is not mapped correctly"),
                () -> Assertions.assertEquals(address, mappedAddress, "Address is not mapped correctly")
        );
    }

    @Test
    void map_from_entity_to_model() {
        //given
        Address address = new Address(CLIENT_JON_ADDRESS_STREET, CLIENT_JON_ADDRESS_CITY, CLIENT_JON_ADDRESS_POSTAL_CODE);
        Client client = new Client(CLIENT_NAME_JON, CLIENT_SURNAME_SNOW, CLIENT_JON_PHONE_123_456_789, address, null, null);

        //when
        ClientDto mappedClient = new ClientMapper().mapToDto(client, ClientDto.class);
        String mappedName = mappedClient.getName();
        String mappedSurname = mappedClient.getSurname();
        String mappedPhone = mappedClient.getPhone();
        Address mappedAddress = mappedClient.getAddress();
        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(CLIENT_NAME_JON, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_SNOW, mappedSurname, "Surname is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_JON_PHONE_123_456_789, mappedPhone, "Phone is not mapped correctly"),
                () -> Assertions.assertEquals(address, mappedAddress, "Address is not mapped correctly")
        );
    }

    @Test
    void map_list_from_model_to_entity() {
        //given
        Address address = new Address(CLIENT_JON_ADDRESS_STREET, CLIENT_JON_ADDRESS_CITY, CLIENT_JON_ADDRESS_POSTAL_CODE);
        ClientDto clientDto1 = new ClientDto(CLIENT_NAME_JON, CLIENT_SURNAME_SNOW, CLIENT_JON_PHONE_123_456_789, address, null, null);
        ClientDto clientDto2 = new ClientDto(CLIENT_NAME_KHAL, CLIENT_SURNAME_DROGO, CLIENT_KHAL_PHONE_111_222_333, address, null, null);
        ClientDto clientDto3 = new ClientDto(CLIENT_NAME_NED, CLIENT_SURNAME_STARK, CLIENT_NED_PHONE_123_000_987, address, null, null);

        List<ClientDto> clientDtos = List.of(clientDto1, clientDto2, clientDto3);

        //when
        List<Client> mappedClients = new ClientMapper().mapToEntityList(clientDtos);

        String mappedName1 = mappedClients.get(0).getName();
        String mappedSurname1 = mappedClients.get(0).getSurname();
        String mappedPhone1 = mappedClients.get(0).getPhone();
        Address mappedAddress1 = mappedClients.get(0).getAddress();

        String mappedName2 = mappedClients.get(1).getName();
        String mappedSurname2 = mappedClients.get(1).getSurname();
        String mappedPhone2 = mappedClients.get(1).getPhone();
        Address mappedAddress2 = mappedClients.get(1).getAddress();

        String mappedName3 = mappedClients.get(2).getName();
        String mappedSurname3 = mappedClients.get(2).getSurname();
        String mappedPhone3 = mappedClients.get(2).getPhone();
        Address mappedAddress3 = mappedClients.get(2).getAddress();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(3, mappedClients.size(), "List size isn't equal 3"),
                () -> Assertions.assertEquals(CLIENT_NAME_JON, mappedName1, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_SNOW, mappedSurname1, "Surname is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_JON_PHONE_123_456_789, mappedPhone1, "Phone is not mapped correctly"),
                () -> Assertions.assertEquals(address, mappedAddress1, "Address is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_NAME_KHAL, mappedName2, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_DROGO, mappedSurname2, "Surname is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_KHAL_PHONE_111_222_333, mappedPhone2, "Phone is not mapped correctly"),
                () -> Assertions.assertEquals(address, mappedAddress2, "Address is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_NAME_NED, mappedName3, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_STARK, mappedSurname3, "Surname is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_NED_PHONE_123_000_987, mappedPhone3, "Phone is not mapped correctly"),
                () -> Assertions.assertEquals(address, mappedAddress3, "Address is not mapped correctly")
        );
    }

    @Test
    void map_list_from_entity_to_model() {
        //given
        Address address = new Address(CLIENT_JON_ADDRESS_STREET, CLIENT_JON_ADDRESS_CITY, CLIENT_JON_ADDRESS_POSTAL_CODE);
        Client clientDto1 = new Client(CLIENT_NAME_JON, CLIENT_SURNAME_SNOW, CLIENT_JON_PHONE_123_456_789, address, null, null);
        Client clientDto2 = new Client(CLIENT_NAME_KHAL, CLIENT_SURNAME_DROGO, CLIENT_KHAL_PHONE_111_222_333, address, null, null);
        Client clientDto3 = new Client(CLIENT_NAME_NED, CLIENT_SURNAME_STARK, CLIENT_NED_PHONE_123_000_987, address, null, null);

        List<Client> clients = List.of(clientDto1, clientDto2, clientDto3);

        //when
        List<ClientDto> mappedClients = new ClientMapper().mapToDtoList(clients, ClientDto.class);

        String mappedName1 = mappedClients.get(0).getName();
        String mappedSurname1 = mappedClients.get(0).getSurname();
        String mappedPhone1 = mappedClients.get(0).getPhone();
        Address mappedAddress1 = mappedClients.get(0).getAddress();

        String mappedName2 = mappedClients.get(1).getName();
        String mappedSurname2 = mappedClients.get(1).getSurname();
        String mappedPhone2 = mappedClients.get(1).getPhone();
        Address mappedAddress2 = mappedClients.get(1).getAddress();

        String mappedName3 = mappedClients.get(2).getName();
        String mappedSurname3 = mappedClients.get(2).getSurname();
        String mappedPhone3 = mappedClients.get(2).getPhone();
        Address mappedAddress3 = mappedClients.get(2).getAddress();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(3, mappedClients.size(), "List size isn't equal 3"),
                () -> Assertions.assertEquals(CLIENT_NAME_JON, mappedName1, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_SNOW, mappedSurname1, "Surname is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_JON_PHONE_123_456_789, mappedPhone1, "Phone is not mapped correctly"),
                () -> Assertions.assertEquals(address, mappedAddress1, "Address is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_NAME_KHAL, mappedName2, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_DROGO, mappedSurname2, "Surname is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_KHAL_PHONE_111_222_333, mappedPhone2, "Phone is not mapped correctly"),
                () -> Assertions.assertEquals(address, mappedAddress2, "Address is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_NAME_NED, mappedName3, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_STARK, mappedSurname3, "Surname is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_NED_PHONE_123_000_987, mappedPhone3, "Phone is not mapped correctly"),
                () -> Assertions.assertEquals(address, mappedAddress3, "Address is not mapped correctly")
        );
    }
}