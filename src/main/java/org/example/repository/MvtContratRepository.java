// package org.example.repository;

// import org.example.entity.MvtContrat;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.util.List;

// public interface MvtContratRepository extends JpaRepository<MvtContrat, Integer> {
//     @Query("SELECT m FROM MvtContrat m WHERE m.entreprise.id = :entrepriseId")
//     List<MvtContrat> findByEntrepriseId(@Param("entrepriseId") Integer entrepriseId);
// }
