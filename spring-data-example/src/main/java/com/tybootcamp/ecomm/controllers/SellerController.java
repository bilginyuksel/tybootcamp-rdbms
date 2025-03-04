package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Profile;
import com.tybootcamp.ecomm.entities.Seller;
import com.tybootcamp.ecomm.repositories.ProductJpaRepository;
import com.tybootcamp.ecomm.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/seller")
public class SellerController {
    @Autowired
    private SellerRepository _sellerRepository;

    @GetMapping(path = "/")
    public ResponseEntity<?> getSellerById(@RequestParam(value = "id") long id) {
        try {
            Seller seller = _sellerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            System.out.println("The seller with id " + id + " = " + seller.toString());
            return new ResponseEntity<>(seller, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("There isn't any seller with this name.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<Seller> addNewSeller(@Valid @RequestBody Seller seller) {
        Seller sellerEntity = new Seller();
        sellerEntity.setAccountId(seller.getAccountId());
        sellerEntity.setWebsite(seller.getWebsite());
        sellerEntity.setAddress(seller.getAddress());
        sellerEntity.setEmailAddress(seller.getEmailAddress());
        sellerEntity.setBirthday(seller.getBirthday());
        sellerEntity = _sellerRepository.save(sellerEntity);
        return new ResponseEntity<>(sellerEntity, HttpStatus.OK);
    }

    @PutMapping(path = "/")
    public ResponseEntity<String> updateSeller(@Valid @RequestBody Seller seller) {
        Seller sellerEntity = _sellerRepository.findById(seller.getId()).orElse(null);
        if (sellerEntity == null) {
            return new ResponseEntity<>("This seller doesn't exists.", HttpStatus.NOT_FOUND);
        }
        sellerEntity.setAccountId(seller.getAccountId());
        sellerEntity.setFirstName(seller.getFirstName());
        sellerEntity.setLastName(seller.getLastName());
        sellerEntity.setWebsite(seller.getWebsite());
        sellerEntity.setBirthday(seller.getBirthday());
        sellerEntity.setAddress(seller.getAddress());
        sellerEntity.setEmailAddress(seller.getEmailAddress());
        sellerEntity.setGender(seller.getGender());
        sellerEntity = _sellerRepository.save(sellerEntity);
        System.out.println("__________________________________________________________________");
        System.out.println("The row of " + sellerEntity.toString() + " updated");
        return new ResponseEntity<>("The seller updated", HttpStatus.OK);
    }
}
