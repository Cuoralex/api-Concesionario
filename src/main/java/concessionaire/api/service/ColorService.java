package concessionaire.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import concessionaire.api.model.Color;
import concessionaire.api.repository.ColorRepository;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public List<Color> getColors() {
        return colorRepository.findAll();
    }

    public Color getColorById(Long id) {
        return colorRepository.findById(id).orElse(null);
    }

    public Color createColor(Color color) {
        Color existingColor = colorRepository.findByDescription(color.getDescription());
        if (existingColor == null) {
            return colorRepository.save(color);
        }
        return null;
    }

    public void deleteColor(Long id) {
        colorRepository.deleteById(id);
    }

    public Color updateColor(Long id, Color color) {
        Color existingColor = colorRepository.findById(id).orElse(null);

        if (existingColor == null)
            return null;

        existingColor.setDescription(color.getDescription());
        return colorRepository.save(existingColor);
    }
}
