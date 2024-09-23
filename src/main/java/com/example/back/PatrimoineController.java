package com.example.back;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {

        private Map<String, Patrimoine> patrimoines = new HashMap<>();
        private static final String FILE_NAME = "patrimoines.json";
        private final PatrimoineService patrimoineService;

        public PatrimoineController(PatrimoineService patrimoineService) {
            this.patrimoineService = patrimoineService;
        }

        @PutMapping("/{id}")
        public ResponseEntity<Patrimoine> createOrUpdatePatrimoine(@PathVariable int id, @RequestBody Patrimoine patrimoine) throws IOException {
            if (!patrimoines.containsKey(id)) {
                patrimoineService.saveToFile(patrimoine);
            }
            return ResponseEntity.ok(patrimoines.get(id));
    }
        @GetMapping("/{id}")
        public ResponseEntity<Patrimoine> getPatrimoine(@PathVariable int id) {
            Patrimoine patrimoine = patrimoines.get(id);
            if (patrimoine == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(patrimoine);
        }
    }



