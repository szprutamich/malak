package pl.malak.beans;

import org.springframework.stereotype.Service;
import pl.malak.beans.dao.PracaDao;
import pl.malak.beans.dao.PracodawcaDao;
import pl.malak.model.Praca;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author Michał Szpruta - szprutamich@gmail.com
 */
@Service
public class PracaBean {

    @Resource
    private PracodawcaDao pracodawcaDao;

    @Resource
    private PracaDao pracaDao;

    @Transactional
    public Praca stworzPrace() {
//            Boolean kwestionariusz, String kwestionariuszUwagi, Boolean kartaSzkolenia,
//            String kartaSzkoleniaUwagi, Date kartaSzkoleniaData, Boolean szkolenie, String szkolenieUwagi,
//            Boolean instruktaz, String instruktazUwagi, Boolean ryzyko, String ryzykoUwagi, Boolean instrukcjeBhp,
//            String instrukcjeBhpUwagi, Boolean szkolenieBhp, String szkolenieBhpUwagi, Date szkolenieBhpData,
//            Boolean rachunki, String rachunkiUwagi, Boolean umowa, String umowaUwagi, Date umowaData,
//            Boolean odbiorOdziezy, String odbiorOdziezyUwagi, Date odbiorOdziezyData, Boolean zua,
//            String zuaUwagi, Boolean zus, String zusUwagi, Boolean zza, String zzaUwagi, Boolean zwua,
//            String zwuaUwagi, Date zwuaData, String nazwa, Boolean badania,
//            String badaniaUwagi, Date badaniaData, Boolean legitymacja, String legitymacjaUwagi, Boolean dowod,
//            String dowodUwagi, Boolean zyciorys, String zyciorysUwagi, Boolean zaswiadczenieSanitarne,
//            String zaswiadczenieSanitarneUwagi, Boolean zaswiadczenieStudent, String zaswiadczenieStudentUwagi,
//            Boolean wyciagKodeks, String wyciagKodeksUwagi, Long pracodawcaId) {
//        Zlecenie zlecenie = new Zlecenie(kwestionariusz, kwestionariuszUwagi, kartaSzkolenia, kartaSzkoleniaUwagi,
//                kartaSzkoleniaData, szkolenie, szkolenieUwagi, instruktaz, instruktazUwagi, ryzyko, ryzykoUwagi,
//                instrukcjeBhp, instrukcjeBhpUwagi, szkolenieBhp, szkolenieBhpUwagi, szkolenieBhpData, rachunki,
//                rachunkiUwagi, umowa, umowaUwagi, umowaData, odbiorOdziezy, odbiorOdziezyUwagi, odbiorOdziezyData, zua,
//                zuaUwagi, zus, zusUwagi, zza, zzaUwagi, zwua, zwuaUwagi, zwuaData, nazwa, badania, badaniaUwagi,
//                badaniaData, legitymacja, legitymacjaUwagi, dowod, dowodUwagi, zyciorys, zyciorysUwagi,
//                zaswiadczenieSanitarne, zaswiadczenieSanitarneUwagi, zaswiadczenieStudent, zaswiadczenieStudentUwagi,
//                wyciagKodeks, wyciagKodeksUwagi);
//
//        zlecenie.setPracodawca(pracodawcaDao.lazyLoad(pracodawcaId));
//        zlecenieDao.create(zlecenie);
//        return zlecenie;

        return null;
    }

    @Transactional
    public Praca uaktualnijZlecenie() {
//            Long id, Boolean kwestionariusz, String kwestionariuszUwagi, Boolean kartaSzkolenia,
//            String kartaSzkoleniaUwagi, Date kartaSzkoleniaData, Boolean szkolenie, String szkolenieUwagi,
//            Boolean instruktaz, String instruktazUwagi, Boolean ryzyko, String ryzykoUwagi, Boolean instrukcjeBhp,
//            String instrukcjeBhpUwagi, Boolean szkolenieBhp, String szkolenieBhpUwagi, Date szkolenieBhpData,
//            Boolean rachunki, String rachunkiUwagi, Boolean umowa, String umowaUwagi, Date umowaData,
//            Boolean odbiorOdziezy, String odbiorOdziezyUwagi, Date odbiorOdziezyData, Boolean zua,
//            String zuaUwagi, Boolean zus, String zusUwagi, Boolean zza, String zzaUwagi, Boolean zwua,
//            String zwuaUwagi, Date zwuaData, String nazwa, Boolean badania,
//            String badaniaUwagi, Date badaniaData, Boolean legitymacja, String legitymacjaUwagi, Boolean dowod,
//            String dowodUwagi, Boolean zyciorys, String zyciorysUwagi, Boolean zaswiadczenieSanitarne,
//            String zaswiadczenieSanitarneUwagi, Boolean zaswiadczenieStudent, String zaswiadczenieStudentUwagi,
//            Boolean wyciagKodeks, String wyciagKodeksUwagi) {
//        Zlecenie zlecenie = zlecenieDao.load(id);
//
//        zlecenie.setNazwa(nazwa);
//        zlecenie.setKwestionariusz(kwestionariusz);
//        zlecenie.setKwestionariuszUwagi(kwestionariuszUwagi);
//        zlecenie.setKartaSzkolenia(kartaSzkolenia);
//        zlecenie.setKartaSzkoleniaUwagi(kartaSzkoleniaUwagi);
//        zlecenie.setKartaSzkoleniaData(kartaSzkoleniaData);
//        zlecenie.setSzkolenie(szkolenie);
//        zlecenie.setSzkolenieUwagi(szkolenieUwagi);
//        zlecenie.setInstruktaz(instruktaz);
//        zlecenie.setInstruktazUwagi(instruktazUwagi);
//        zlecenie.setRyzyko(ryzyko);
//        zlecenie.setRyzykoUwagi(ryzykoUwagi);
//        zlecenie.setInstrukcjeBhp(instrukcjeBhp);
//        zlecenie.setInstrukcjeBhpUwagi(instrukcjeBhpUwagi);
//        zlecenie.setSzkolenieBhp(szkolenieBhp);
//        zlecenie.setSzkolenieBhpUwagi(szkolenieBhpUwagi);
//        zlecenie.setSzkolenieBhpData(szkolenieBhpData);
//        zlecenie.setRachunki(rachunki);
//        zlecenie.setRachunkiUwagi(rachunkiUwagi);
//        zlecenie.setUmowa(umowa);
//        zlecenie.setUmowaUwagi(umowaUwagi);
//        zlecenie.setUmowaData(umowaData);
//        zlecenie.setOdbiorOdziezy(odbiorOdziezy);
//        zlecenie.setOdbiorOdziezyUwagi(odbiorOdziezyUwagi);
//        zlecenie.setOdbiorOdziezyData(odbiorOdziezyData);
//        zlecenie.setZua(zua);
//        zlecenie.setZuaUwagi(zuaUwagi);
//        zlecenie.setZwua(zwua);
//        zlecenie.setZwuaUwagi(zwuaUwagi);
//        zlecenie.setZwuaData(zwuaData);
//        zlecenie.setBadania(badania);
//        zlecenie.setBadaniaData(badaniaData);
//        zlecenie.setBadaniaUwagi(badaniaUwagi);
//        zlecenie.setDowod(dowod);
//        zlecenie.setDowodUwagi(dowodUwagi);
//        zlecenie.setLegitymacja(legitymacja);
//        zlecenie.setLegitymacjaUwagi(legitymacjaUwagi);
//        zlecenie.setNazwa(nazwa);
//        zlecenie.setWyciagKodeks(wyciagKodeks);
//        zlecenie.setWyciagKodeksUwagi(wyciagKodeksUwagi);
//        zlecenie.setZaswiadczenieSanitarne(zaswiadczenieSanitarne);
//        zlecenie.setZaswiadczenieSanitarneUwagi(zaswiadczenieSanitarneUwagi);
//        zlecenie.setZaswiadczenieStudent(zaswiadczenieStudent);
//        zlecenie.setZaswiadczenieStudentUwagi(zaswiadczenieStudentUwagi);
//        zlecenie.setZus(zus);
//        zlecenie.setZusUwagi(zusUwagi);
//        zlecenie.setZwua(zwua);
//        zlecenie.setZwuaData(zwuaData);
//        zlecenie.setZwuaUwagi(zwuaUwagi);
//        zlecenie.setZyciorys(zyciorys);
//        zlecenie.setZyciorysUwagi(zyciorysUwagi);
//        zlecenie.setZza(zza);
//        zlecenie.setZzaUwagi(zzaUwagi);
//
//        return zlecenie;

        return null;
    }
}
