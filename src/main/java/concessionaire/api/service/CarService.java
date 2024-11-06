package concessionaire.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import concessionaire.api.model.Brand;
import concessionaire.api.model.Car;
import concessionaire.api.model.City;
import concessionaire.api.model.Color;
import concessionaire.api.model.Range;
import concessionaire.api.repository.BrandRepository;
import concessionaire.api.repository.CarRepository;
import concessionaire.api.repository.ColorRepository;
import concessionaire.api.repository.RangeRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RangeRepository rangeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car createCar(Car car) {
        Color color = colorRepository.findById(car.getColor().getId()).orElse(null);
        Range range = rangeRepository.findById(car.getRange().getId()).orElse(null);
        Brand brand = brandRepository.findById(car.getBrand().getId()).orElse(null);

        if (color == null || range == null || brand == null )
            return null;

        car.setBrand(brand);
        car.setColor(color);
        car.setRange(range);

        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car updateCar(Long id, Car car) {
        Car existingCar = carRepository.findById(id).orElse(null);

        if (existingCar == null)
            return null;

        Color color = colorRepository.findById(car.getColor().getId()).orElse(null);
        Range range = rangeRepository.findById(car.getRange().getId()).orElse(null);
        Brand brand = brandRepository.findById(car.getBrand().getId()).orElse(null);

        existingCar.setBrand(brand == null ? existingCar.getBrand() : brand);
        existingCar.setColor(color == null ? existingCar.getColor() : color);
        existingCar.setRange(range== null ? existingCar.getRange() : range);
        existingCar.setCapacity(car.getCapacity() == null ? existingCar.getCapacity() : car.getCapacity());
        existingCar.setCc(car.getCc() == null ? existingCar.getCc() : car.getCc());
        existingCar.setModel(car.getModel() == null ? existingCar.getModel() : car.getModel());
        existingCar.setPrice(car.getPrice() == null ? existingCar.getPrice() : car.getPrice());

        return carRepository.save(existingCar);
    }
}
