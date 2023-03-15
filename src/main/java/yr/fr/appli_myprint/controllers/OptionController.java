package yr.fr.appli_myprint.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yr.fr.appli_myprint.model.OptionEntity;
import yr.fr.appli_myprint.service.OptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("option")
@Tag(name = "Option")
public class OptionController {

    private final OptionService optionService;

    @PostMapping("/addNewOption")
    public ResponseEntity<Integer> save(
            @RequestBody OptionEntity option
            ) {
        return ResponseEntity.ok(optionService.save(option));
    }

    @GetMapping("/allOptions")
    public List<OptionEntity> getAllOptions() {
        return optionService.findAll();
    }

    @GetMapping("/findById{option-id}")
    public ResponseEntity<OptionEntity> findById(
            @PathVariable("option-id") Integer optionId
    )
    {
        return ResponseEntity.ok(optionService.findById(optionId));
    }

    @DeleteMapping ("/delete/{option-id}")
    public void deleteById(
            @PathVariable("option-id") Integer optionId
    )
    {
        optionService.delete(optionId);
    }

    @PutMapping("/updateOption")
    public ResponseEntity<OptionEntity> updateOption (@RequestParam(value = "idOption") Integer idOption,
           @Valid @RequestBody OptionEntity optionDetails) {
        OptionEntity option = optionService.findById(idOption);

        option.setLibelle(optionDetails.getLibelle());
        option.setPrix(optionDetails.getPrix());

        final Integer updatedOption = optionService.save(option);
        OptionEntity finalProduct = optionService.findById(updatedOption);
        return ResponseEntity.ok(finalProduct);
    }
}
