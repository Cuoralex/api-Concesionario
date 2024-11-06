package concessionaire.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import concessionaire.api.model.Range;
import concessionaire.api.repository.RangeRepository;

@Service
public class RangeService {

    @Autowired
    private RangeRepository rangeRepository;

    public List<Range> getRanges() {
        return rangeRepository.findAll();
    }

    public Range getRangeById(Long id) {
        return rangeRepository.findById(id).orElse(null);
    }

    public Range createRange(Range range) {
        Range existingRange = rangeRepository.findByDescription(range.getDescription());
        if (existingRange == null) {
            return rangeRepository.save(range);
        }
        return null;
    }

    public void deleteRange(Long id) {
        rangeRepository.deleteById(id);
    }

    public Range updateRange(Long id, Range range) {
        Range existingRange = rangeRepository.findById(id).orElse(null);

        if (existingRange == null)
            return null;

        existingRange.setDescription(range.getDescription());
        return rangeRepository.save(existingRange);
    }
}
