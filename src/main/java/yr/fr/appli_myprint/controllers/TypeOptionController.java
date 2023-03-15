package yr.fr.appli_myprint.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yr.fr.appli_myprint.model.TypeOptionEntity;
import yr.fr.appli_myprint.service.TypeOptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TypeOptionController {

    private final TypeOptionService typeOptionService;

    @PostMapping("/addNewTypeOption")
    public ResponseEntity<Integer> save(
            @RequestBody TypeOptionEntity typeOption
    ) {
        return ResponseEntity.ok(typeOptionService.save(typeOption));
    }

    @GetMapping("/allTypeOptions")
    public List<TypeOptionEntity> getAllOptions() {
        return typeOptionService.findAll();
    }

    @GetMapping("/findById{TypeOption-id}")
    public ResponseEntity<TypeOptionEntity> findById(
            @PathVariable("typeOption-id") Integer typeOptionId
    )
    {
        return ResponseEntity.ok(typeOptionService.findById(typeOptionId));
    }

    @DeleteMapping ("/delete/{typeOption-id}")
    public void deleteById(
            @PathVariable("typeOption-id") Integer typeOptionId
    )
    {
        typeOptionService.delete(typeOptionId);
    }

}
