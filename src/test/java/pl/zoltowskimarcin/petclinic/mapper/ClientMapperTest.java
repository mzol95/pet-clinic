package pl.zoltowskimarcin.petclinic.mapper;

class ClientMapperTest {

    private static final String TEST_CLIENT_ADDRESS_STREET = "Test street";
    private static final String TEST_CLIENT_CITY = "Test city";
    private static final String TEST_CLIENT_POSTAL_CODE = "Test postalCode";
    private static final String TEST_CLIENT_NAME = "Test name";
    private static final String TEST_CLIENT_SURNAME = "Test surname";
    private static final String TEST_CLIENT_PHONE = "Test phone";

//    @Test
//    void map_from_model_to_entity() {
//        //given
//        ClientDto clientDto = new ClientDto.Builder()
//                .name(TEST_CLIENT_NAME)
//                .surname(TEST_CLIENT_SURNAME)
//                .street(TEST_CLIENT_ADDRESS_STREET)
//                .city(TEST_CLIENT_CITY)
//                .postalCode(TEST_CLIENT_POSTAL_CODE)
//                .phone(TEST_CLIENT_PHONE)
//                .build();
//
//        //when
//        //todo
////        Client mappedClientEntity = ClientMapper.getMapper().map(clientDto, Client.class);
////        String mappedName = mappedClientEntity.getName();
////        String mappedSurname = mappedClientEntity.getSurname();
////        String mappedStreet = mappedClientEntity.getAddresses().getStreet();
////        String mappedCity = mappedClientEntity.getAddresses().getCity();
////        String mappedPostalCode = mappedClientEntity.getAddresses().getPostalCode();
////        String mappedPhone = mappedClientEntity.getPhone();
//
//        //then
////        Assertions.assertAll(
////                () -> Assertions.assertEquals(TEST_CLIENT_NAME, mappedName, "Name is not mapped correctly"),
////                () -> Assertions.assertEquals(TEST_CLIENT_SURNAME, mappedSurname, "Surname is not mapped correctly"),
////                () -> Assertions.assertEquals(TEST_CLIENT_ADDRESS_STREET, mappedStreet, "Street is not mapped correctly"),
////                () -> Assertions.assertEquals(TEST_CLIENT_CITY, mappedCity, "City is not mapped correctly"),
////                () -> Assertions.assertEquals(TEST_CLIENT_POSTAL_CODE, mappedPostalCode, "PostalCode is not mapped correctly"),
////                () -> Assertions.assertEquals(TEST_CLIENT_PHONE, mappedPhone, "Phone is not mapped correctly")
////        );
//    }

//    @Test
//    void map_from_entity_to_model() {
//        //given
//        Address address = new Address(TEST_CLIENT_ADDRESS_STREET, TEST_CLIENT_CITY, TEST_CLIENT_POSTAL_CODE);
//
//        Client client = new Client.Builder()
//                .name(TEST_CLIENT_NAME)
//                .surname(TEST_CLIENT_SURNAME)
//                .addresses(address)
//                .phone(TEST_CLIENT_PHONE)
//                .build();
//
//        //when
//        ClientDto mappedClient = ClientMapper.getMapper().map(client, ClientDto.class);
//        String mappedName = mappedClient.getName();
//        String mappedSurname = mappedClient.getSurname();
//        String mappedStreet = mappedClient.getStreet();
//        String mappedCity = mappedClient.getCity();
//        String mappedPostalCode = mappedClient.getPostalCode();
//        String mappedPhone = mappedClient.getPhone();
//
//        //then
//        Assertions.assertAll(
//                () -> Assertions.assertEquals(TEST_CLIENT_NAME, mappedName, "Name is not mapped correctly"),
//                () -> Assertions.assertEquals(TEST_CLIENT_SURNAME, mappedSurname, "Surname is not mapped correctly"),
//                () -> Assertions.assertEquals(TEST_CLIENT_ADDRESS_STREET, mappedStreet, "Street is not mapped correctly"),
//                () -> Assertions.assertEquals(TEST_CLIENT_CITY, mappedCity, "City is not mapped correctly"),
//                () -> Assertions.assertEquals(TEST_CLIENT_POSTAL_CODE, mappedPostalCode, "PostalCode is not mapped correctly"),
//                () -> Assertions.assertEquals(TEST_CLIENT_PHONE, mappedPhone, "Phone is not mapped correctly")
//        );
//    }
}