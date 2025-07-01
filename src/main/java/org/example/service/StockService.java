// package org.example.service;

// import org.example.entity.Employe;
// import org.example.entity.Poste;
// import org.example.entity.Stock;
// import org.example.repository.ComposantRepository;
// import org.example.repository.StockRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class StockService {

//     @Autowired
//     private StockRepository stockRepository;
//     @Autowired
//     private ComposantRepository composantRepository;

//     public StockService(StockRepository stockRepository, org.example.service.ComposantRepository composantRepository) {
//         this.stockRepository = stockRepository;
//         this.composantRepository = composantRepository;
//     }

//     // Récupérer tous les stocks
//     public List<Stock> findAll() {
//         return stockRepository.findAll();
//     }

//     // Récupérer un stock par ID
//     public Optional<Stock> findById(Long id) {
//         return stockRepository.findById(id);
//     }

//     public void saveStock(Stock s) {
//         stockRepository.save(s);
//     }


//     // Supprimer un stock
//     @Transactional
//     public void deleteById(Long id) {
//         stockRepository.deleteById(id);
//     }

//     public List<Poste> getAllComposant() {
//     return composantRepository.findAll();
// }
// }