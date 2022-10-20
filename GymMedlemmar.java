package Sprint2.Inlämningsuppgift2;

public class GymMedlemmar {

    String personnummer;
    String namn;
    String sistaBetalningsDatum;


    public GymMedlemmar(String personnummer, String namn, String sistaBetalningsDatum){
        this.personnummer=personnummer;
        this.namn = namn;
        this.sistaBetalningsDatum=sistaBetalningsDatum;
    }

    public String getPersonnummer() {
        return personnummer;
    }

    public String getNamn() {
        return namn;
    }

    public String getSistaBetalningsDatum() {
        return sistaBetalningsDatum;
    }
}

