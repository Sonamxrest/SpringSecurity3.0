package com.xrest.nchl.controller;

import com.xrest.nchl.dto.RestResponseDto;
import com.xrest.nchl.model.Customer;
import com.xrest.nchl.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController<C, I> {
    private final BaseService<C, I> baseService;

    public BaseController(BaseService<C, I> baseService) {
        this.baseService = baseService;
    }


    @PostMapping("/save")
    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<?> save(@Valid @RequestBody C c){
        return new RestResponseDto().successModel(baseService.save(c));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") I id) {
        return new RestResponseDto().successModel(baseService.findOne(id));
    }

    @GetMapping("/getByAll")
    public ResponseEntity<?> getAll() {
        return new RestResponseDto().successModel(baseService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") I id) {
        baseService.deleteById(id);
        return new RestResponseDto().successModel("Deleted");

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
