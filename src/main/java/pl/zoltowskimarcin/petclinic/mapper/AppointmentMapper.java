package pl.zoltowskimarcin.petclinic.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    private static ModelMapper modelMapper;

    public static ModelMapper getMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }

}
