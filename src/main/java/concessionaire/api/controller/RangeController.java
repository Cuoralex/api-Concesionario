package concessionaire.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import concessionaire.api.model.Range;
import concessionaire.api.service.RangeService;

@RestController
@RequestMapping("/api/ranges")
public class RangeController {

    @Autowired
    private RangeService rangeService;

    @GetMapping("")
    public List<Range> getRanges() {
        return rangeService.getRanges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Range> getRange(@PathVariable Long id) {
        Range range = rangeService.getRangeById(id);

        if (range == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(range, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Range> createRange(@RequestBody Range range) {
        Range newRange = rangeService.createRange(range);
        if (newRange == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newRange, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Range> deleteRange(@PathVariable Long id) {
        rangeService.deleteRange(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Range> updateRange(@PathVariable Long id, @RequestBody Range range) {
        Range updatedRange = rangeService.updateRange(id, range);

        if (updatedRange == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updatedRange, HttpStatus.OK);
    }
}
