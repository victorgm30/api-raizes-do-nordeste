package com.raizesdonordeste.raizes_api.controller;

import com.raizesdonordeste.raizes_api.entity.Unit;
import com.raizesdonordeste.raizes_api.repository.UnitRepository;
import com.raizesdonordeste.raizes_api.repository.ProductRepository;
import com.raizesdonordeste.raizes_api.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController {

    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private ProductRepository productRepository;

    // Listar todas as unidades
    @GetMapping
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }
    // Obter uma unidade por ID
    @GetMapping("/{id}")
    public Unit getUnitById(@PathVariable Long id) {
        return unitRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
    }

    // Criar uma nova unidade
    @PostMapping
    public Unit createUnit(@RequestBody Unit unit) {
        return unitRepository.save(unit);
    }

    // Atualizar uma unidade existente
    @PutMapping("/{id}")
    public Unit updateUnit(@PathVariable Long id, @RequestBody Unit unitDetails) {
        
        Unit unit = unitRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
        
        unit.setName(unitDetails.getName());
        unit.setAddress(unitDetails.getAddress());
        
        return unitRepository.save(unit);
    }

    // Deletar uma unidade
    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable Long id) {

        if (productRepository.existsByUnitId(id)) {
            throw new IllegalStateException("Unit has products associated and cannot be deleted");
        }

        unitRepository.deleteById(id);
    }
}
