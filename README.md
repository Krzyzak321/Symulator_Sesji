# Symulator Sesji

**Symulator Sesji** to projekt w języku Java służący do symulacji przygotowań studenta do egzaminów. Pozwala na planowanie nauki, symulowanie postępów, analizę czynników wpływających na motywację oraz generowanie wizualizacji. Projekt korzysta z systemu budowania **Gradle**.

---

## Spis treści
- [Opis](#opis)
- [Wymagania](#wymagania)
- [Struktura projektu](#struktura-projektu)
- [Konfiguracja środowiska (IntelliJ/VSCode)](#konfiguracja-środowiska-intellijvscode)
- [Budowanie i uruchamianie](#budowanie-i-uruchamianie)
- [Testy jednostkowe](#testy-jednostkowe)
- [Plik fat-jar](#plik-fat-jar)

---

## Opis

Aplikacja umożliwia:
- Tworzenie i zarządzanie planem nauki studenta
- Symulowanie wpływu różnych motywatorów i przeszkód (zły chumor,szansa na stypendium, choroba, prokrastynacja itd.)
- Przechowywanie historii nauki i wyników egzaminów
- Wizualizację postępów w nauce
- Zapisywanie planu nauki do pliku

---

## Wymagania

- **Java 17 lub nowsza** (zalecane Java 21)
- **Gradle 8+** (jeśli nie masz, projekt zawiera wrappera)
- (Opcjonalnie) środowisko IDE: IntelliJ IDEA, Eclipse, VSCode

---

## Struktura projektu

```
Symulator_Sesji/
├── build.gradle
├── settings.gradle
├── gradlew / gradlew.bat
├── libs/                  # zewnętrzne biblioteki .jar (jeśli używasz)
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Main.java
│   │       ├── Student.java
│   │       └── ... (inne klasy)
│   └── test/
│       └── java/
│           ├── MainTest.java
│           └── ... (testy jednostkowe)
```

---

## Konfiguracja środowiska (IntelliJ/VSCode)

1. **Otwórz projekt przez `build.gradle` lub `settings.gradle`**  
   Wybierz "Open Project" w IDE i wskaż katalog główny projektu.

2. **Załaduj zależności Gradle**  
   IDE automatycznie wykryje plik `build.gradle` i dociągnie zależności.  
   Jeśli masz katalog `libs/` z własnymi `.jar`, są one automatycznie dołączone do projektu.

3. **Uruchamianie**  
   - W IntelliJ: Otwórz plik `Main.java` i kliknij zieloną strzałkę (lub z menu: Gradle → Tasks → application → run).
   - W VSCode: Zainstaluj rozszerzenie Java i uruchom aplikację przez "Run".

---

## Budowanie i uruchamianie

### Z linii komend

1. **Budowanie projektu:**
    ```sh
    ./gradlew build
    ```

2. **Uruchamianie:**
    ```sh
    ./gradlew run
    ```
    lub uruchom bezpośrednio zbudowany fat-jar:
    ```sh
    java -jar build/libs/Symulator_Sesji.jar
    ```

### Parametr klasy głównej

Jeśli Twoja klasa main nazywa się inaczej niż `Main`, sprawdź lub zmień w `build.gradle`:
```groovy
application {
    mainClass = 'Main'
}
```
Dla klasy w pakiecie, np. `symulator.Main`, wpisz pełną ścieżkę pakietu.

---

## Testy jednostkowe

Testy są w katalogu `src/test/java/`.  
Uruchom je poleceniem:
```sh
./gradlew test
```
Raporty znajdziesz w `build/reports/tests/test/index.html`.

---

## Plik fat-jar

Po kompilacji (`./gradlew build`) gotowy plik JAR znajdziesz w `build/libs/Symulator_Sesji.jar`.  
Możesz go uruchomić na dowolnym komputerze z Javą:
```sh
java -jar build/libs/Symulator_Sesji.jar
```

---
