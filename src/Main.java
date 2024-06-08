public class Main {

    public static void main(String[] args) {
        int n = 3000;
        int liczbaWatkow = 2;

        //przechowanie obiektów wątków
        //każdy z nich będzie obliczał sumę dzielników dla określonego zakresu liczb
        SumaDzielnikow[] watki = new SumaDzielnikow[liczbaWatkow];

        //tworzenie i uruchamianie wątków
        //aby równocześnie obliczać sumę dzielników dla różnych zakresów liczb
        for (int i = 0; i < liczbaWatkow; i++) {
            watki[i] = new SumaDzielnikow(i * (n / liczbaWatkow) + 1, (i + 1) * (n / liczbaWatkow), n);
            watki[i].start();
        }

        //kolejny watek ma się uruchomić dopiero po zakończeniu poprzedniego
        try {
            for (int i = 0; i < liczbaWatkow; i++) { //w razie zmiany liczby watkow
                watki[i].join();
            }
        } catch (InterruptedException e) { //obsluga wyjatkow
            throw new RuntimeException(e);
        }

        //sumowanie wyników z wątków
        long wynik = 0;
        for (int i = 0; i < liczbaWatkow; i++) {
            wynik += watki[i].getWynik();
        }

        System.out.println("Suma dzielników dla liczb do " + n + " to " + wynik);
    }
}