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

    private String name;
    private String surname;

    LiteClientDto() {
    }

    public LiteClientDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

}
