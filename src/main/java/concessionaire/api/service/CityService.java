package concessionaire.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import concessionaire.api.model.City;
import concessionaire.api.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City createCity(City city) {
        City existingCity = cityRepository.findByName(city.getName());
        if (existingCity == null) {
            return cityRepository.save(city);
        }
        return null;
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    public City updateCity(Long id, City city) {
        City existingCity = cityRepository.findById(id).orElse(null);

        if (existingCity == null)
            return null;

        existingCity.setName(city.getName());
        return cityRepository.save(existingCity);
    }
}
