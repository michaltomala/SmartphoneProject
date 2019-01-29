package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Phone;
import pl.coderslab.repository.PhoneRepository;

public class PhoneConverter implements Converter<String, Phone> {

    @Autowired
    private PhoneRepository phoneRepository;


    @Override
    public Phone convert(String s) {
        return phoneRepository.findOne(Long.parseLong(s));
    }
}
