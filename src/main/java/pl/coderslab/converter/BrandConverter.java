package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Brand;
import pl.coderslab.repository.BrandRepository;

public class BrandConverter implements Converter<String, Brand> {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand convert(String s) {
        return brandRepository.findOne(Long.parseLong(s));
    }
}
