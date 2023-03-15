package yr.fr.appli_myprint.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yr.fr.appli_myprint.dao.AdresseRepository;
import yr.fr.appli_myprint.dto.AdresseDTO;
import yr.fr.appli_myprint.mapper.AdresseDTOMapper;
import yr.fr.appli_myprint.model.AdresseEntity;
import yr.fr.appli_myprint.model.PersonneEntity;
import yr.fr.appli_myprint.service.AdresseService;

import java.util.List;

@RestController
@RequestMapping("/adresse")
@RequiredArgsConstructor
@Tag(name = "Adresse")
public class AdresseController {

    private final AdresseService adresseService;
    private final AdresseRepository adresseRepository;

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

    @PutMapping("/updateAdresse")
    public ResponseEntity<AdresseEntity> updateAdresse(@PathParam(value = "adresseId") Integer adresseId, @Valid @RequestBody AdresseEntity adresseDetails)  {
       AdresseEntity adresse = adresseRepository.findByIdAdresse(adresseId);
       adresse.setRue(adresseDetails.getRue());
       adresse.setComplement(adresseDetails.getComplement());
       adresse.setCodePostal(adresseDetails.getCodePostal());
       adresse.setVille(adresseDetails.getVille());

        final AdresseEntity updatedAdresse = adresseRepository.save(adresse);
        return ResponseEntity.ok(updatedAdresse);
    }
}


