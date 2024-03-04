package pl.zoltowskimarcin.petclinic.web.model.cilent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class BasicClientDto {

    private Long id;
    private String name;
    private String surname;
    private String street;
    private String city;
    private String postalCode;
    private String phone;

    BasicClientDto() {
    }

    private BasicClientDto(Builder builder) {
        setName(builder.name);
        setSurname(builder.surname);
        setStreet(builder.street);
        setCity(builder.city);
        setPostalCode(builder.postalCode);
        setPhone(builder.phone);
    }


    public static final class Builder {
        private String name;
        private String surname;
        private String street;
        private String city;
        private String postalCode;
        private String phone;

        public Builder() {
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

        public BasicClientDto build() {
            return new BasicClientDto(this);
        }
    }
}
