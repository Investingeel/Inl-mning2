package Sprint2.Inlämningsuppgift2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;



public class Main extends CustomerFileReader {

    public static void main(String[] args) {
        Main BestGymEver = new Main();
        BestGymEver.huvudProgram();
    }

    Path customersFil= Paths.get("src/Sprint2/Inlämningsuppgift2/customers.txt");

    public void huvudProgram() {
        List<GymMedlemmar> medlemsLista = SkapaListaFrånFil(customersFil);
        while (true) {
            String inmatning = inmatning();
            System.out.println(medlemskapsStatus(inmatning, medlemsLista));
        }

    }



    public String inmatning() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ange för- och efternamn eller personnummer (10 siffror):");
        String inmatning = sc.nextLine();

        while (inmatning.isBlank()) {
            System.out.println("Ogiltig inmatning, testa igen.");
            inmatning = sc.nextLine();
        }

        return inmatning;
    }


    public boolean senasteBetalning(String betalningsDatumString) {
        LocalDate betalningsDatum = LocalDate.parse(betalningsDatumString);
        LocalDate currentDate = LocalDate.now();

        if (betalningsDatum.isAfter(currentDate.minusYears(1)) || betalningsDatum.isEqual(currentDate.minusYears(1))) {
            return true;
        } else {
            return false;
        }
    }

    final String PTtextDokument = "src/Sprint2/Inlämningsuppgift2/RegistreradeGymPass.txt";
    public void registreraGymPass(String inmatadeGymPass, GymMedlemmar medlem) {

        try (PrintWriter loggaGymPass = new PrintWriter(
                new FileOutputStream(inmatadeGymPass, true)))
        {
            loggaGymPass.append(medlem.getNamn() + " tränade " + LocalDate.now()+"\n");


        } catch (FileNotFoundException e) {
            System.out.println("Filen hittades ej");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ett fel inträffade");
            e.printStackTrace();
        }


    }


    public String medlemskapsStatus(String inmatning, List<GymMedlemmar> medlemsLista) {
        boolean medlemskap;

        for (GymMedlemmar medlem : medlemsLista) {

            if (inmatning.equalsIgnoreCase(medlem.getNamn()) || inmatning.equals(medlem.getPersonnummer())) {
                String betalningsDatum = medlem.getSistaBetalningsDatum();
                medlemskap = senasteBetalning(betalningsDatum);

                if (medlemskap) {
                    registreraGymPass(PTtextDokument, medlem);
                    return medlem.getNamn() + " har ett aktivt medlemskap. Sista betalningsdatum: " + medlem.getSistaBetalningsDatum();
                    
                } else {
                    return medlem.getNamn() + " har inte ett aktivt medlemskap. Sista betalningsdatum: " + medlem.getSistaBetalningsDatum();
                }
            }
        }
        return "Ingen medlem hittades med detta namn eller personnummer, testa igen";
    }




}