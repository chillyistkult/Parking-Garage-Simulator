/**
 * Created by benjaminschuch on 06.08.15.
 */
class Parkade {
    private int parkingSpaces;
    private int entrances;
    private int gateways;

    Parkade(int parkingSpaces, int entrances, int gateways) {
        this.parkingSpaces = parkingSpaces;
        this.entrances = entrances;
        this.gateways = gateways;
    }

    synchronized public void enter(Car car) {
        while (entrances <= 0 || parkingSpaces <= 0) {
            if (entrances <= 0)
                System.out.println("Entrances voll: " + car + " muss warten.");
            else
                System.out.println("Parkhaus voll: " + car + " muss an der Einfahrt warten, bis ein Parkplatz frei wird.");
            try {
                wait();
            } catch (Exception e) {
            }
        }
        entrances--;
        System.out.println("Einfahrt erfolgreich: " + car + " fährt in das Parkhaus.");

        if (parkingSpaces > 0) {
            parkingSpaces--;
            System.out.println("Parkplatz reserviert: " + car + " bekommt einen Parkplatz zugewiesen.");
            status();
            car.setParking(1);
        }
        notifyAll();
    }

    synchronized public void parke(Car car) {
        entrances++;
        notifyAll();
    }

    synchronized public void leave(Car car) {
        while (gateways <= 0) {
            System.out.println("Ausfahrten besetzt: " + car + " muss warten.");
            try {
                wait();
            } catch (Exception e) {
            }
        }
        gateways--;
        parkingSpaces++;
        System.out.println("Parkplatz verlassen: " + car + " fährt zur Ausfahrt.");
    }

    synchronized public void left(Car car) {
        gateways++;
        notifyAll();
    }

    private void status() {
        System.out.println("Status: Es sind noch " + parkingSpaces + " Parkplätze frei.");
    }
}
