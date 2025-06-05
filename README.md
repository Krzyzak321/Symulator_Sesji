
# Symulator Sesji

**Symulator Sesji** to projekt w języku Java służący do modelowania i symulacji przygotowań studenta do egzaminów. Pozwala planować naukę, śledzić postępy, symulować egzaminy oraz generować wizualizacje postępów. Projekt wykorzystuje zewnętrzne biblioteki z katalogu `libs/`.

---

## Spis treści

- [Opis projektu](#opis-projektu)
- [Struktura katalogów](#struktura-katalogów)
- [Wymagania](#wymagania)
- [Konfiguracja IDE (np. IntelliJ, Eclipse)](#konfiguracja-ide-np-intellij-eclipse)
- [Kompilacja i uruchamianie](#kompilacja-i-uruchamianie)
- [Plik symulator_fat.jar](#plik-symulator_fatjar)
- [Autorzy](#autorzy)

---

## Opis projektu

Projekt umożliwia:
- Tworzenie planu nauki dla studentów,
- Symulowanie motywatorów i czynników wpływających na naukę (np. przerwa na kawę, prokrastynacja),
- Przechowywanie historii nauki,
- Przeprowadzanie symulacji egzaminów,
- Wizualizację postępów i wyników (wykresy, statystyki),
- Wczytywanie oraz zapisywanie danych z plików.

## Struktura katalogów

```
libs/             # biblioteki zewnętrzne (.jar)
symulator/
  └── src/        # kod źródłowy (pliki .java)
      └── images/ # obrazy do okienek (opcjonalne)
uml/              # diagramy UML
README.md
symulator_fat.jar # zbudowany plik JAR z wszystkimi bibliotekami
```

## Wymagania

- **Java 17 lub wyższa** (zalecane Java 21 LTS)
- IDE obsługujące projekty Java (np. IntelliJ IDEA, Eclipse, NetBeans)

## Konfiguracja IDE (np. IntelliJ, Eclipse)

Projekt nie korzysta z systemu budowania (Maven/Gradle), dlatego **biblioteki musisz dodać ręcznie**:

### IntelliJ IDEA

1. **Utwórz nowy projekt Java** lub otwórz katalog `symulator` jako projekt.
2. Dodaj do projektu cały katalog `symulator/src` jako źródłowy (`Mark Directory as -> Sources Root`).
3. Wejdź w `File -> Project Structure -> Modules -> Dependencies`.
4. Kliknij `+` i wybierz `JARs or directories`, wskaż cały katalog `libs/`.
5. Zatwierdź zmiany.
6. Upewnij się, że JDK ustawione dla projektu to Java 17 lub wyższa.

### Eclipse

1. Utwórz nowy projekt Java.
2. Skopiuj pliki z `symulator/src` do katalogu `src` w projekcie.
3. Kliknij projekt PPM → Properties → Java Build Path → zakładka Libraries → Add External JARs.
4. Dodaj wszystkie pliki `.jar` z katalogu `libs/`.
5. Zatwierdź zmiany i zbuduj projekt.

## Kompilacja i uruchamianie

Zakładając, że jesteś w katalogu głównym projektu i masz Javę 17+:

### Kompilacja (ręczna, z poziomu terminala)

```bash
javac -cp "libs/*" -d out symulator/src/*.java
```

### Uruchamianie

```bash
java -cp "libs/*:out" Main
```
*(na Windows separator to średnik: `libs/*;out`)*

## Plik symulator_fat.jar

W repozytorium znajduje się plik **symulator_fat.jar** – to tzw. "fat-jar", czyli już zbudowany plik JAR zawierający wszystkie klasy oraz biblioteki.  
Możesz go uruchomić na dowolnym komputerze z zainstalowaną Javą (17 lub wyższą):

```bash
java -jar symulator_fat.jar
```

Nie wymaga żadnej dodatkowej konfiguracji ani instalowania bibliotek.

---

