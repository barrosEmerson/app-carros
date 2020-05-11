package br.com.barrostech.carros.controller;

import br.com.barrostech.carros.domain.Carro;
import br.com.barrostech.carros.dto.CarroDTO;
import br.com.barrostech.carros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<CarroDTO> getCarros(@RequestParam(value = "page", defaultValue = "0")Integer page,
                                    @RequestParam(value = "size", defaultValue = "10")Integer size){
        return carroService.getCarro(PageRequest.of(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> getCarroById(@PathVariable Long id){
        CarroDTO carro = carroService.getCarroById(id);

        return ResponseEntity.ok(carro);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getCarroByTipo(@PathVariable String tipo){

        List<CarroDTO>carros = carroService.getCarroByTipo(tipo);

        return carros.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @PostMapping
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity salvar(@RequestBody Carro carro){


            CarroDTO c = carroService.salvar(carro);
            URI location = getUri(c.getId());
            return ResponseEntity.created(location).build();

    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id ,@RequestBody Carro carro){
        CarroDTO c = carroService.update(carro, id);
        return c != null ? ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        carroService.delete(id);
        return ResponseEntity.ok().build();
    }
}
