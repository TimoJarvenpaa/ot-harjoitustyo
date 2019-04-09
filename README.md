# Ohjelmistotekniikka, harjoitustyö

# Pong-klooni

## Dokumentaatio

[Vaatimusmäärittely](dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](dokumentointi/arkkitehtuuri.md)

[Työaikakirjanpito](dokumentointi/tuntikirjanpito.md)

## Komentorivitoiminnot

Sovelluksen voi käynnistää komennolla
```
mvn compile exec:java -Dexec.mainClass=pong.ui.MainMenu
```

### Testaus

Testit voidaan suorittaa komennolla
```
mvn test
```

Testikattavuusraportti voidaan luoda komennolla
```
mvn test jacoco:report
```
Raportti luodaan hakemistoon _target_ ja sitä pääsee tarkastelemaan avaamalla selaimella tiedosto _Pong/target/site/jacoco/index.html_

### Checkstyle

Koodille voidaan suorittaa tiedostossa [checkstyle.xml](Pong/checkstyle.xml) määriteltyjen ehtojen mukainen tyylitarkistus komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Yhteenvetoa voi tarkastella avaamalla selaimella tiedosto _Pong/target/site/checkstyle.html_