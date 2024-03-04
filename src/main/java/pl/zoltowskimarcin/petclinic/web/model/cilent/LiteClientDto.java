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

    public LiteClientDto(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
