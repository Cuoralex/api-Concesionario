package concessionaire.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import concessionaire.api.model.City;
import concessionaire.api.model.Store;
import concessionaire.api.repository.StoreRepository;
import concessionaire.api.repository.CityRepository;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CityRepository cityRepository;

    public List<Store> getStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public Store createStore(Store store) {
        Store existingStore = storeRepository.findByName(store.getName());

        if (existingStore == null) {
            City city = cityRepository.findById(store.getCity().getId()).orElse(null);

            if (city == null)
                return null;

            store.setCity(city);

            return storeRepository.save(store);
        }
        return null;
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    public Store updateStore(Long id, Store store) {
        Store existingStore = storeRepository.findById(id).orElse(null);

        if (existingStore == null)
            return null;

        City city = cityRepository.findById(store.getCity().getId()).orElse(null);

        existingStore.setAddress(store.getAddress() == null ? existingStore.getAddress() : store.getAddress());
        existingStore.setPhone(store.getPhone() == null ? existingStore.getPhone() : store.getPhone());
        existingStore.setName(store.getName() == null ? existingStore.getName() : store.getName());
        existingStore.setCity(city == null ? existingStore.getCity() : city);

        return storeRepository.save(existingStore);
    }
}