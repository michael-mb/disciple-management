package disciple.online.portal.scopes.report.repositories;


import disciple.online.portal.scopes.report.entities.GlobalTicket;
import disciple.online.portal.scopes.user.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.List;


public interface ReportGlobalRepository extends CrudRepository<GlobalTicket,Long>  {
    @Override
    Streamable<GlobalTicket> findAll();

    @Query("select e from #{#entityName} as e order by e.week desc ")
    List<GlobalTicket> findAllByOrderByWeekDesc();
}
