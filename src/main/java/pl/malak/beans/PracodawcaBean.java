package pl.malak.beans;

import org.springframework.stereotype.Service;
import pl.malak.beans.dao.PracodawcaDao;
import pl.malak.model.Pracodawca;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class PracodawcaBean {

    @Resource
    private PracodawcaDao pracodawcaDao;

    @Transactional
    public Pracodawca stworzPracodawce(
            String nazwa, String email, Boolean teczka, String teczkaUwagi, Boolean ocena, String ocenaUwagi,
            Boolean szkoleniaOkresowe, String szkoleniaOkresoweUwagi, Boolean szkoleniaPracodawcy,
            Date szkoleniaPracodawcyData, String szkoleniaPracodawcyUwagi, Boolean odziezowka, String odziezowkaUwagi) {
        Pracodawca pracodawca = new Pracodawca(nazwa, email, teczka, teczkaUwagi, ocena, ocenaUwagi, szkoleniaOkresowe,
                szkoleniaOkresoweUwagi, szkoleniaPracodawcy, szkoleniaPracodawcyData, szkoleniaPracodawcyUwagi,
                odziezowka, odziezowkaUwagi);
        pracodawcaDao.create(pracodawca);

        return pracodawca;
    }

    @Transactional
    public Pracodawca uaktualnijPracodawce(
            Long id, String nazwa, String email, Boolean teczka, String teczkaUwagi, Boolean ocena, String ocenaUwagi,
            Boolean szkoleniaOkresowe, String szkoleniaOkresoweUwagi, Boolean szkoleniaPracodawcy,
            Date szkoleniaPracodawcyData, String szkoleniaPracodawcyUwagi, Boolean odziezowka, String odziezowkaUwagi) {
        Pracodawca pracodawca = pracodawcaDao.load(id);

        pracodawca.setNazwa(nazwa);
        pracodawca.setEmail(email);
        pracodawca.setTeczka(teczka);
        pracodawca.setTeczkaUwagi(teczkaUwagi);
        pracodawca.setOcena(ocena);
        pracodawca.setOcenaUwagi(ocenaUwagi);
        pracodawca.setSzkoleniaOkresowe(szkoleniaOkresowe);
        pracodawca.setSzkoleniaOkresoweUwagi(szkoleniaOkresoweUwagi);
        pracodawca.setSzkoleniaPracodawcy(szkoleniaPracodawcy);
        pracodawca.setSzkoleniaPracodawcyData(szkoleniaPracodawcyData);
        pracodawca.setSzkoleniaPracodawcyUwagi(szkoleniaPracodawcyUwagi);
        pracodawca.setOdziezowka(odziezowka);
        pracodawca.setOdziezowkaUwagi(odziezowkaUwagi);

        return pracodawca;
    }
}
