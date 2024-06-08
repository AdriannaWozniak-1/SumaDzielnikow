class SumaDzielnikow extends Thread {
    private final int poczatek;
    private final int koniec;
    private final int n;
    private long wynik;

    public SumaDzielnikow(int poczatek, int koniec, int n) {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.n = n;
    }

    @Override
    //dodawanie do siebie sum
    public void run() {
        for (int i = poczatek; i <= koniec; i++) {
            wynik += obliczanieSumy(i, n);
        }
    }

    public long getWynik() {
        return wynik;
    }

    /*
    suma dzielników wszystkich liczb miejszych lub równych n (3000), liczba - dana liczba
    lepiej synchronized niż static, bo jest używane do unikania problemów związanych z
    współbieżnym dostępem do współdzielonych zasobów, które mogą prowadzić do tzw. wyścigów
    działa jako blokada, tylko jeden wątek jednocześnie wejdzie
    */
    private static long obliczanieSumy(int liczba, int n) {
        long suma = 0;
        //math.min - zwraca mniejszą liczbę
        for (int i = 1; i <= Math.min(liczba, n); i++) {
            // Jeśli reszta z dzielenia liczba przez i wynosi 0, to i jest dzielnikiem liczba.
            if (liczba % i == 0) {
                suma += i;
            }
        }
        return suma;
    }
}