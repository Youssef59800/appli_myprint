package yr.fr.appli_myprint.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yr.fr.appli_myprint.dto.AdresseDTO;
import yr.fr.appli_myprint.service.AdresseService;

import java.util.List;

@RestController
@RequestMapping("/adresse")
@RequiredArgsConstructor
@Tag(name = "Adresse")
public class AdresseController {

    private final AdresseService adresseService;

    @PostMapping("/addNewAdresse")
    public ResponseEntity<Integer> save(
            @RequestBody AdresseDTO adresseDTO
    ) {
        return ResponseEntity.ok(adresseService.save(adresseDTO));
    }

    @GetMapping("/allAdresses")
    public List<AdresseDTO> getAllAdresses() {
        return adresseService.findAll();
    }

    @GetMapping("/findById{adresse-id}")
    public ResponseEntity<AdresseDTO> findById(
            @PathVariable("adresse-id") Integer addressId
    )
    {
        return ResponseEntity.ok(adresseService.findById(addressId));
    }

    @DeleteMapping ("/delete/{adresse-id}")
    public void deleteById(
            @PathVariable("adresse-id") Integer addressId
    )
    {
        adresseService.delete(addressId);
    }

    @PutMapping("/updateAdresse/{id}")
    public Integer updateAdresse(
            @PathVariable(value = "id") Integer adresseId,
            @Valid @RequestBody AdresseDTO adresse)  {
        return null;
    }
}


