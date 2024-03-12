package pl.zoltowskimarcin.petclinic.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zoltowskimarcin.petclinic.repository.entity.Client;
import pl.zoltowskimarcin.petclinic.web.model.cilent.ClientDto;

import static pl.zoltowskimarcin.petclinic.utils.TestUtils.*;

class ClientMapperTest {

    @Test
    void map_from_model_to_entity() {
        //given
        ClientDto clientDto = new ClientDto(CLIENT_NAME_JON, CLIENT_SURNAME_SNOW, CLIENT_JON_PHONE_123_456_789, null, null, null);

        //when
        Client mappedClientEntity = new ClientMapper().mapToEntity(clientDto);
        String mappedName = mappedClientEntity.getName();
        String mappedSurname = mappedClientEntity.getSurname();
        String mappedPhone = mappedClientEntity.getPhone();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(CLIENT_NAME_JON, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_SNOW, mappedSurname, "Surname is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_JON_PHONE_123_456_789, mappedPhone, "Phone is not mapped correctly")
        );
    }

    @Test
    void map_from_entity_to_model() {
        //given
        Client client = new Client(CLIENT_NAME_JON, CLIENT_SURNAME_SNOW, CLIENT_JON_PHONE_123_456_789, null, null, null);

        //when
        ClientDto mappedClient = new ClientMapper().mapToDto(client, ClientDto.class);
        String mappedName = mappedClient.getName();
        String mappedSurname = mappedClient.getSurname();
        String mappedPhone = mappedClient.getPhone();
        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(CLIENT_NAME_JON, mappedName, "Name is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_SURNAME_SNOW, mappedSurname, "Surname is not mapped correctly"),
                () -> Assertions.assertEquals(CLIENT_JON_PHONE_123_456_789, mappedPhone, "Phone is not mapped correctly")
        );
    }
}