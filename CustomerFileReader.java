package Sprint2.Inlämningsuppgift2;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerFileReader {

    public List<GymMedlemmar> SkapaListaFrånFil(Path customersFil) {
        List<GymMedlemmar> MedlemsLista = new ArrayList<>();
        String personnummer;
        String namn = null;
        String sistaBetalningsDatum = null;
        try (Scanner läsTextFil = new Scanner(customersFil)) {
            while (läsTextFil.hasNext()) {
                personnummer = läsTextFil.next().replace(",", "").trim();
                if (läsTextFil.hasNext()) {
                    namn = läsTextFil.nextLine();
                    namn = namn.substring(1);
                    if (läsTextFil.hasNext()) {
                        sistaBetalningsDatum = läsTextFil.nextLine();

                    }
                }
                MedlemsLista.add(new GymMedlemmar(personnummer, namn, sistaBetalningsDatum));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen hittades ej");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Ett fel inträffade");
            e.printStackTrace();
            System.exit(0);
        }


        return MedlemsLista;
    }



}
