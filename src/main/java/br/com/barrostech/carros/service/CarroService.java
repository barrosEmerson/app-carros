package br.com.barrostech.carros.service;

import br.com.barrostech.carros.domain.Carro;
import br.com.barrostech.carros.domain.exception.ObjecNotFoundException;
import br.com.barrostech.carros.dto.CarroDTO;
import br.com.barrostech.carros.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> getCarro(Pageable pageable){
        return carroRepository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO getCarroById(Long id){
        Optional<Carro>carro = carroRepository.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjecNotFoundException("Carro não encontrado"));
    }

    public List<CarroDTO> getCarroByTipo(String tipo) {
        return carroRepository.findCarroByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO salvar(Carro carro) {
        Assert.isNull(carro.getId(),"Não foi possível salvar o registro");
        return CarroDTO.create(carroRepository.save(carro));
    }

    public CarroDTO update(Carro carro, Long id){
        Assert.notNull(id,"Não foi possível atualizar o registro");

        Optional<Carro> optional = carroRepository.findById(id);
        if(optional.isPresent()){
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());

            carroRepository.save(db);
            return CarroDTO.create(db);
        }else{
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }

    }

    public void delete(Long id) {
            carroRepository.deleteById(id);
    }
}
