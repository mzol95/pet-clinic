package pl.zoltowskimarcin.petclinic.mapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.zoltowskimarcin.petclinic.repository.entity.Doctor;
import pl.zoltowskimarcin.petclinic.web.model.DoctorDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DoctorMapper {

    public <T> T mapToDto(Doctor doctor, Class<T> type) {
        ModelMapper modelMapper = new ModelMapper();

        log.info("Mapping Doctor: " + doctor + "to DoctorDto");
        T mappedDoctor = modelMapper.map(doctor, type);

        log.info("Mapped Doctor: " + doctor + "to DoctorDto: " + mappedDoctor);
        return mappedDoctor;
    }

    public Doctor mapToEntity(DoctorDto doctorDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(DoctorDto.class, Doctor.class).addMappings(mapper -> mapper.skip(Doctor::setId));
        log.info("Mapping DoctorDto: " + doctorDto + "to Doctor");
        Doctor mappedDoctor = modelMapper.map(doctorDto, Doctor.class);
        log.info("Mapped DoctorDto: " + doctorDto + "to Doctor: " + mappedDoctor);
        return mappedDoctor;
    }

    public <T> List<T> mapToDtoList(List<Doctor> doctors, Class<T> type) {
        log.info("Mapping List<Doctor> to List<DoctorDto>");
        List<T> doctorDtos = doctors.stream()
                .map(src -> mapToDto(src, type))
                .collect(Collectors.toList());
        return doctorDtos;
    }

    public List<Doctor> mapToEntityList(List<DoctorDto> doctorDtos) {
        log.info("Mapping List<DoctorDto> to List<Doctor>");
        List<Doctor> doctors = doctorDtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
        return doctors;
    }

}
