// package org.example.controller;

// import org.example.entity.Employe;
// import org.example.entity.Stock;
// import org.example.service.StockService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.ResponseEntity;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// import java.math.BigDecimal;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;

// import javax.servlet.http.HttpSession;

// @RestController
// @RequestMapping("/stock")
// public class StockController {

//     @Autowired
//     private StockService stockService;
//     private ComposantService composantService;

//     public StockController(StockService stockService, ComposantService composantService) {
//         this.stockService = stockService;
//         this.composantService = composantService;
//     }

//     @GetMapping("/list")
//     public String listFilms(Model model) {
//         List<Stock> stocks = stockService.findAll();
//         model.addAttribute("stock", stocks);
//         return "listStock";
//     }

//     @GetMapping("/create")
//     public String showCreateForm(Model model, HttpSession session) {
//         List<Composant> composants = composantService.findAll();
//         model.addAttribute("composant", composants);
//         return "createStock";
//     }


//     @PostMapping("/save")
//     public String saveStock(
//             @RequestParam(value = "id", required = false) Long id,
//             @RequestParam("id_composant") Integer idComposant,
//             @RequestParam("date_creation") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateCreation,
//             @RequestParam("qtte_stock") BigDecimal qtte,
//             @RequestParam("nombre_jour_conservation") Integer nbJourConserv,
//             Model model){
//                 var composantOpt = composantService.findById(idComposant);
//                 Stock stock = (id != null) ? stockService.findById(id).orElse(new Stock()) : new Stock();
//                 stock.setComposant(composantOpt.get());
//                 stock.setdateCreation(dateCreation);
//                 stock.setQtteStock(qtte);
//                 stock.setNombre_jour_conservation(nbJourConserv);

//                 stockService.saveStock(stock);

//                 //model.addAttribute("succes", "Stock enregistré!");
//                 //model.addAttribute("stocks", stockService.findAll());
//                 //model.addAttribute("stock", new Stock());
//                 //model.addAttribute("composants", stockService.getAllComposant());
//                 return "redirect:/stock/list";
//             }

//     @GetMapping("/delete")
//     public String deleteStock(@RequestParam("id") Long id) {
//         stockService.deleteById(id);
//         return "redirect:/stock/list";
//     }

//     @GetMapping("/update")
//     public String showUpdateForm(@RequestParam("id") Long id, Model model) {
//         Optional<Stock> stockOpt = stockService.findById(id);
//         if (stockOpt.isPresent()) {
//             model.addAttribute("stock", stockOpt.get());
//             model.addAttribute("composants", composantService.findAll());
//             return "createStock";
//         }
//         return "redirect:/stock/list";
//     }

//     @PostMapping("/update")
//     public String updateStock(
//             @RequestParam(value = "id", required = false) Long id,
//             @RequestParam("id_composant") Integer idComposant,
//             @RequestParam("date_creation") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateCreation,
//             @RequestParam("qtte_stock") BigDecimal qtte,
//             @RequestParam("nombre_jour_conservation") Integer nbJourConserv) {
//         Optional<Stock> stockOpt = stockService.findById(id);
//         if (stockOpt.isPresent()) {
//             var composantOpt = composantService.findById(idComposant);
//             Stock stock = stockOpt.get();
//             stock.setComposant(composantOpt.get());
//             stock.setdateCreation(dateCreation);
//             stock.setQtteStock(qtte);
//             stock.setNombre_jour_conservation(nbJourConserv);
//             stockService.saveStock(stock);
//         }
//         return "redirect:/stock/list";
//     }
// }