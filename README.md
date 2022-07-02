## Hibernate Product Store - Demo Project

### Overview
Ten projekt został stworzony w celu zaimplementowania połączenia z bazą danych,
i użycia Hibernate (ORM - Object Relational Mapper).
W ramach projektu został stworzony model 'Product' który reprezentuje produkt w sklepie.

Obsługa aplikacji odbywa się przez parser konsolowy.

### Niezbędne zależności
Do uruchomienia projektu niezbędne jest posiadanie:
- (lokalnej lub zdanlej) bazy danych MySQL
- Java JRE (Java Runetime Enviroment)(min. Java 11)
- 
### Jak uruchomić
Do uruchomienia konieczne jest skonfigurowanie połączenia bazodanowego. Wzór pliku
konfiguracyjnego `hibernate.cfg.xml` znajduje się w katalogu `scr/main/resources`.
Plik należy skopiować i zmienić treść następujących pól.
```xml
        <property name="connection.url"></property> <!--TODO: zmienić adres połączenia-->
        <property name="connection.username"></property> <!-- TODO: wypełnić nazwę użytkownika-->
        <property name="connection.password"></property> <!--TODO: wypełnić hasło uzytkownika-->
```
Po zmianie ustawień należy zmienić nazwę pliku z `template_hibernate.cfg.xml` na `hibernate.cfg.xml`

### Autorzy
Oliwier Sidor (2022) (R)