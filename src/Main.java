public class Main {

    public static void main(String[] args) {
        Parkade parkade = new Parkade(10, 1, 1); //Parkplätze, Anzahl Einfahrten, Anzahl Ausfahrten
        //Autos werden generiert
        for (int i = 100; i < 201; i++)
        {
            new Car("B-HTW-"+i, parkade,
                    (int) (Math.random()*2000),
                    (int) (Math.random()*100),
                    (int) (Math.random()*2000),
                    (int) (Math.random()*100)).start();
        }
    }
}
