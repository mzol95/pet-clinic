package pl.zoltowskimarcin.petclinic.web.model.cilent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.zoltowskimarcin.petclinic.web.model.appointment.BasicAppointmentDto;
import pl.zoltowskimarcin.petclinic.web.model.pet.BasicPetDto;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ClientDto {

    private Long id;
    private String name;
    private String surname;
    private String street;
    private String city;
    private String postalCode;
    private String phone;
    private List<BasicPetDto> petDtos;
    private List<BasicAppointmentDto> appointmentDtos;

    private ClientDto() {
    }

    private ClientDto(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setSurname(builder.surname);
        setStreet(builder.street);
        setCity(builder.city);
        setPostalCode(builder.postalCode);
        setPhone(builder.phone);
        setPetDtos(builder.petDtos);
        setAppointmentDtos(builder.appointmentDtos);
    }


    public static final class Builder {
        private Long id;
        private String name;
        private String surname;
        private String street;
        private String city;
        private String postalCode;
        private String phone;
        private List<BasicPetDto> petDtos;
        private List<BasicAppointmentDto> appointmentDtos;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder surname(String val) {
            surname = val;
            return this;
        }

        public Builder street(String val) {
            street = val;
            return this;
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public Builder postalCode(String val) {
            postalCode = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder petDtos(List<BasicPetDto> val) {
            petDtos = val;
            return this;
        }

        public Builder appointmentDtos(List<BasicAppointmentDto> val) {
            appointmentDtos = val;
            return this;
        }

        public ClientDto build() {
            return new ClientDto(this);
        }
    }
}
