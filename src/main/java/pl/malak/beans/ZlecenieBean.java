package pl.malak.beans;

import org.springframework.stereotype.Service;
import pl.malak.beans.dao.PracodawcaDao;
import pl.malak.beans.dao.ZlecenieDao;
import pl.malak.model.Zlecenie;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ZlecenieBean {

    @Resource
    private PracodawcaDao pracodawcaDao;

    @Resource
    private ZlecenieDao zlecenieDao;

    @Transactional
    public Zlecenie stworzZlecenie(
            Boolean kwestionariusz, String kwestionariuszUwagi, Boolean kartaSzkolenia, String kartaSzkoleniaUwagi,
            Date kartaSzkoleniaData, Boolean szkolenie, String szkolenieUwagi, Boolean instruktaz,
            String instruktazUwagi, Boolean ryzyko, String ryzykoUwagi, Boolean instrukcjeBhp,
            String instrukcjeBhpUwagi, Boolean szkolenieBhp, String szkolenieBhpUwagi, Date szkolenieBhpData,
            Boolean rachunki, String rachunkiUwagi, Boolean umowa, String umowaUwagi, Date umowaData,
            Boolean odbiorOdziezy, String odbiorOdziezyUwagi, Date odbiorOdziezyData, Boolean zua, String zuaUwagi,
            Boolean zza, String zzaUwagi, Boolean zwua, String zwuaUwagi, String nazwa, Boolean badania,
            String badaniaUwagi, Date badaniaData, Boolean dowod, String dowodUwagi, Boolean zyciorys,
            String zyciorysUwagi, Boolean zaswiadczenieStudent, String zaswiadczenieStudentUwagi, Long pracodawcaId) {
        Zlecenie zlecenie = new Zlecenie(kwestionariusz, kwestionariuszUwagi, kartaSzkolenia, kartaSzkoleniaUwagi,
                kartaSzkoleniaData, szkolenie, szkolenieUwagi, instruktaz, instruktazUwagi, ryzyko, ryzykoUwagi,
                instrukcjeBhp, instrukcjeBhpUwagi, szkolenieBhp, szkolenieBhpUwagi, szkolenieBhpData, rachunki,
                rachunkiUwagi, umowa, umowaUwagi, umowaData, odbiorOdziezy, odbiorOdziezyUwagi, zua,
                zuaUwagi, zza, zzaUwagi, zwua, zwuaUwagi, nazwa, badania, badaniaUwagi,
                badaniaData, dowod, dowodUwagi, zyciorys, zyciorysUwagi,
                zaswiadczenieStudent, zaswiadczenieStudentUwagi
        );

        zlecenie.setPracodawca(pracodawcaDao.lazyLoad(pracodawcaId));
        zlecenieDao.create(zlecenie);
        return zlecenie;
    }

    @Transactional
    public Zlecenie uaktualnijZlecenie(
            Long id, Boolean kwestionariusz, String kwestionariuszUwagi, Boolean kartaSzkolenia,
            String kartaSzkoleniaUwagi, Date kartaSzkoleniaData, Boolean szkolenie, String szkolenieUwagi,
            Boolean instruktaz, String instruktazUwagi, Boolean ryzyko, String ryzykoUwagi, Boolean instrukcjeBhp,
            String instrukcjeBhpUwagi, Boolean szkolenieBhp, String szkolenieBhpUwagi, Date szkolenieBhpData,
            Boolean rachunki, String rachunkiUwagi, Boolean umowa, String umowaUwagi, Date umowaData,
            Boolean odbiorOdziezy, String odbiorOdziezyUwagi, Boolean zua,
            String zuaUwagi, Boolean zza, String zzaUwagi, Boolean zwua, String zwuaUwagi, String nazwa,
            Boolean badania, String badaniaUwagi, Date badaniaData, Boolean dowod, String dowodUwagi, Boolean zyciorys,
            String zyciorysUwagi, Boolean zaswiadczenieStudent, String zaswiadczenieStudentUwagi) {
        Zlecenie zlecenie = zlecenieDao.load(id);

        zlecenie.setNazwa(nazwa);
        zlecenie.setKwestionariusz(kwestionariusz);
        zlecenie.setKwestionariuszUwagi(kwestionariuszUwagi);
        zlecenie.setKartaSzkolenia(kartaSzkolenia);
        zlecenie.setKartaSzkoleniaUwagi(kartaSzkoleniaUwagi);
        zlecenie.setKartaSzkoleniaData(kartaSzkoleniaData);
        zlecenie.setSzkolenie(szkolenie);
        zlecenie.setSzkolenieUwagi(szkolenieUwagi);
        zlecenie.setInstruktaz(instruktaz);
        zlecenie.setInstruktazUwagi(instruktazUwagi);
        zlecenie.setRyzyko(ryzyko);
        zlecenie.setRyzykoUwagi(ryzykoUwagi);
        zlecenie.setInstrukcjeBhp(instrukcjeBhp);
        zlecenie.setInstrukcjeBhpUwagi(instrukcjeBhpUwagi);
        zlecenie.setSzkolenieBhp(szkolenieBhp);
        zlecenie.setSzkolenieBhpUwagi(szkolenieBhpUwagi);
        zlecenie.setSzkolenieBhpData(szkolenieBhpData);
        zlecenie.setRachunki(rachunki);
        zlecenie.setRachunkiUwagi(rachunkiUwagi);
        zlecenie.setUmowa(umowa);
        zlecenie.setUmowaUwagi(umowaUwagi);
        zlecenie.setUmowaData(umowaData);
        zlecenie.setOdbiorOdziezy(odbiorOdziezy);
        zlecenie.setOdbiorOdziezyUwagi(odbiorOdziezyUwagi);
        zlecenie.setZua(zua);
        zlecenie.setZuaUwagi(zuaUwagi);
        zlecenie.setZwua(zwua);
        zlecenie.setZwuaUwagi(zwuaUwagi);
        zlecenie.setBadania(badania);
        zlecenie.setBadaniaData(badaniaData);
        zlecenie.setBadaniaUwagi(badaniaUwagi);
        zlecenie.setDowod(dowod);
        zlecenie.setDowodUwagi(dowodUwagi);
        zlecenie.setNazwa(nazwa);
        zlecenie.setZaswiadczenieStudent(zaswiadczenieStudent);
        zlecenie.setZaswiadczenieStudentUwagi(zaswiadczenieStudentUwagi);
        zlecenie.setZwua(zwua);
        zlecenie.setZwuaUwagi(zwuaUwagi);
        zlecenie.setZyciorys(zyciorys);
        zlecenie.setZyciorysUwagi(zyciorysUwagi);
        zlecenie.setZza(zza);
        zlecenie.setZzaUwagi(zzaUwagi);

        return zlecenie;
    }
}
