package yr.fr.appli_myprint.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yr.fr.appli_myprint.dao.CategorieRepository;
import yr.fr.appli_myprint.model.CategorieEntity;
import yr.fr.appli_myprint.service.CategorieService;

import java.util.List;

@RestController
@RequestMapping("/categorie")
@RequiredArgsConstructor
@Tag(name = "Categorie")
public class CategorieController {

    private final CategorieService categorieService;
    private final CategorieRepository categorieRepository;

    @PostMapping("/addNewCategorie")
    public ResponseEntity<Integer> save(
            @RequestBody CategorieEntity categorie
            ) {
        return ResponseEntity.ok(categorieService.save(categorie));
    }

    @GetMapping("/allCategories")
    public List<CategorieEntity> getAllCategories() {
        return categorieService.findAll();
    }

    @GetMapping("/findById{categorie-id}")
    public ResponseEntity<CategorieEntity> findById(
            @PathVariable("categorie-id") Integer categorieId
    )
    {
        return ResponseEntity.ok(categorieService.findById(categorieId));
    }

    @DeleteMapping ("/delete/{categorie-id}")
    public void deleteById(
            @PathVariable("categorie-id") Integer categorieId
    )
    {
        categorieService.delete(categorieId);
    }

    @PutMapping("/updateCategorie")
    public ResponseEntity<CategorieEntity> updateAdresse(@PathParam(value = "categorieId") Integer categorieId, @Valid @RequestBody CategorieEntity categorieDetails)  {
        CategorieEntity categorie = categorieRepository.findByIdCategorie(categorieId);
        categorie.setNom(categorieDetails.getNom());
        categorie.setCollectionOfOption(categorieDetails.getCollectionOfOption());

        final CategorieEntity updatedCategorie = categorieRepository.save(categorie);
        return ResponseEntity.ok(updatedCategorie);
    }

}
