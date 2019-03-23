# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla kaksi käyttäjää voi pelata jaetulla näppäimistöllä klassisen Pong-videopelin kaltaista yksinkertaista peliä. Pelissä osapuolet liikuttavat pelikentän oikealla tai vasemmalla puolella olevia "mailoja" pystyakselin suuntaisesti tarkoituksenaan kerätä pisteitä kimmottamalla pelikentällä liikkuva pallo vastustajan puoleiseen laitaan ja torjumalla vastapuolen yrityksiä tehdä maaleja.

## Käyttöliittymäluonnos

Sovelluksen kaksi tärkeintä näkymää ovat sen päävalikko ja itse pelinäkymä.

![Päävalikkonäkymä](kuvat/paavalikkonakyma.png)


![Pelinäkymä](kuvat/pelinakyma.png)

Sovellus aukeaa päävalikkonäkymään, josta on mahdollista siirtyä pelinäkymään tai muihin mahdollisiin näkymiin.

## Perusversion tarjoama toiminnallisuus

* käyttäjä voi aloittaa ja keskeyttää pelin
* pelissä molempia mailoja liikutetaan näppäimistön avulla, mikä mahdollistaa kaksinpelin
* liikkuvan pallon osuminen mailaan aiheuttaa asianmukaisen kimpoamisen
* pallon osuessa vastustajan päätyyn, maalin tekijälle lisätään piste ja peli jatkuu aloitustilanteesta

## Jatkokehitysideoita

Perustoiminnallisuuksien toteuttamisen jälkeen sovellusta voidaan täydentää esim. seuraavilla ominaisuuksilla:

* pelimuotoina erikseen aikarajoitettu- ja rajoittamaton peli
* huippupisteiden tai -aikojen tallentaminen tiedostoon tai tietokantaan ja niiden tarkastelu
* yksinkertainen tekoäly, joka mahdollistaa yksinpelin
* vaihtoehtoisia pelikenttiä, joissa mukana myös esteitä tms.