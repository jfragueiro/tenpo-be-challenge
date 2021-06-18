package com.tenpo.api;

import com.tenpo.model.HistoryResource;
import com.tenpo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<HistoryResource> findAll() {
        return historyService.getAll();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<HistoryResource>> getHistory(@RequestParam(required = false) Integer size,
                                                            @RequestParam(required = false) Integer page_number,
                                                            @RequestParam(required = false) String sort_by) {
        try {
            Page<HistoryResource> history = historyService.getAllHistory(size, page_number, sort_by);
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
