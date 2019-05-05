# Ohjelmistotekniikka, harjoitustyö

# Pong-klooni

Sovelluksen avulla kaksi pelaajaa voivat pelata jaetulla näppäimistöllä klassisen Pong-videopelin kaltaista yksinkertaista reaaliaikaista pallopeliä.

## Dokumentaatio

[Käyttöohje](dokumentointi/kayttoohje.md)

[Vaatimusmäärittely](dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](dokumentointi/arkkitehtuuri.md)

[Testausdokumentti](dokumentointi/testaus.md)

[Työaikakirjanpito](dokumentointi/tuntikirjanpito.md)

## Julkaistut versiot

[Loppupalautus](https://github.com/TimoJarvenpaa/ot-harjoitustyo/releases/tag/v1.2)

[Viikko 6](https://github.com/TimoJarvenpaa/ot-harjoitustyo/releases/tag/v1.1)

[Viikko 5](https://github.com/TimoJarvenpaa/ot-harjoitustyo/releases/tag/v1.0)

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

### jar-tiedoston luominen

Suoritettavan jar-tiedoston luominen onnistuu komennolla
```
mvn package
```
Hakemistoon _Pong/target_ syntyy tiedosto _Pong-1.0-SNAPSHOT.jar_, jonka voi suorittaa komennolla
```
java -jar Pong-1.0-SNAPSHOT.jar
```

### Checkstyle

Koodille voidaan suorittaa tiedostossa [checkstyle.xml](Pong/checkstyle.xml) määriteltyjen ehtojen mukainen tyylitarkistus komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Yhteenvetoa voi tarkastella avaamalla selaimella tiedosto _Pong/target/site/checkstyle.html_

### JavaDoc

Sovelluksen JavaDoc luodaan komennolla
```
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedosto _Pong/target/site/apidocs/index.html_