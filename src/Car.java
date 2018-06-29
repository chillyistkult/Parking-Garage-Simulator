/**
 * Created by benjaminschuch on 06.08.15.
 */
class Car extends Thread {
    private String tag;
    private Parkade parkade;
    private int parkingtime;
    private int drivetime;
    private int entertime;
    private int leavetime;
    private int parking;

    Car(String tag, Parkade parkade, int drivetime, int entertime, int parkingtime, int leavetime) {
        this.tag = tag;
        this.parkade = parkade;
        this.parkingtime = parkingtime;
        this.drivetime = drivetime;
        this.entertime = entertime;
        this.leavetime = leavetime;
        parking = 0;
    }

    public void run() {
        //Auto fährt los
        System.out.println("Auto losgefahren: " + this);

        //Anfahrtszeit wird simuliert
        try {
            sleep(drivetime);
        } catch (Exception e) {
        }
        //Parkhaus wird erreicht
        System.out.println("Parkade erreicht: " + this);

        //Auto fährt in Parkhaus
        parkade.enter(this);
        try {
            sleep(entertime);
        } catch (Exception e) {
        }

        //Parken
        parkade.park(this);
        if (parking == 1) {
            System.out.println("Parking besetzt: " + this + " parkt für " + parkingtime + " Minuten.");
            try {
                sleep(parkingtime);
            } catch (Exception e) {
            }
        }

        //Aus dem Parkhaus fahren
        parkade.leave(this);
        try {
            sleep(leavetime);
        } catch (Exception e) {
        }

        //Auto verlässt das Parkhaus
        parkade.left(this);
        System.out.println("Ausfahrt erfolgreich: " + this);
    }

    public void setParking(int i) {
        parking = i;
    }

    public int getParking() {
        return parking;
    }

    public String toString() {
        return tag;
    }
}