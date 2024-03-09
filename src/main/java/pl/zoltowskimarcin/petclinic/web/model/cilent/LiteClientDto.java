package pl.zoltowskimarcin.petclinic.web.model.cilent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class LiteClientDto {

    private Long id;
    private String name;
    private String surname;

    LiteClientDto() {
    }

    private LiteClientDto(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setSurname(builder.surname);
    }


    public static final class Builder {
        private Long id;
        private String name;
        private String surname;

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

        public LiteClientDto build() {
            return new LiteClientDto(this);
        }
    }
}
