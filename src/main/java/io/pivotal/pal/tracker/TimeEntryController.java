package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(@RequestBody TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity<>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry entry = timeEntryRepository.update(timeEntryId,expected);
        if(ObjectUtils.isEmpty(entry)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(entry , HttpStatus.OK);
    }

    @DeleteMapping(value = "/time-entries/{timeEntryId}", consumes = "application/json")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        TimeEntry entry = timeEntryRepository.find(timeEntryId);
        if(ObjectUtils.isEmpty(entry)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            timeEntryRepository.delete(timeEntryId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry entry = timeEntryRepository.find(timeEntryId);
        if(ObjectUtils.isEmpty(entry)){
            return new ResponseEntity<>(entry , HttpStatus.NOT_FOUND);
        }
        else
        return new ResponseEntity<>(entry , HttpStatus.OK);
    }

}
