package br.com.xmacedo.repository;

import java.util.List;
import java.util.Optional;

import br.com.xmacedo.entity.Lane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LaneRepository extends JpaRepository<Lane, Long> {
    Optional<List<Lane>> findByNomeIgnoreCase(@Param("nome") String nome);

    Optional<Lane> findByIdLinhaPoaTransporte(@Param("idLinhaPoaTransporte") Long idLinhaPoaTransporte);

    @Query("SELECT ln FROM Lane ln join Itinerary it ON ln.idLinhaPoaTransporte = it.idLinha " +
        " join Location lc ON it.idLocalizacao = lc.id  " +
        " WHERE (lc.lat >= :la1 AND lc.lat <= :la2) " +
        "   AND (lc.lng >= :ln1 AND lc.lng <= :ln2)")
    List<Lane> findByLatMinAndLatMaxAndLongMinAndLonMax(@Param("la1")Double latMin, @Param("la2")Double latMax,
        @Param("ln1")Double lonMin, @Param("ln2")Double lonMax);
}