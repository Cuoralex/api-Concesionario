package concessionaire.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import concessionaire.api.model.Brand;
import concessionaire.api.repository.BrandRepository;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand createBrand(Brand brand) {
        Brand existingBrand = brandRepository.findByDescription(brand.getDescription());
        if (existingBrand == null) {
            return brandRepository.save(brand);
        }
        return null;
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    public Brand updateBrand(Long id, Brand brand) {
        Brand existingBrand = brandRepository.findById(id).orElse(null);

        if (existingBrand == null)
            return null;

        existingBrand.setDescription(brand.getDescription());
        return brandRepository.save(existingBrand);
    }
}
