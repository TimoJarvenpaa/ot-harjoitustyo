# Testausdokumentti

Sovelluksen yksikkö- ja integraatiotestaus on toteutettu JUnit-kirjastoa hyödyntäen. Järjestelmätestaus on suoritettu manuaalisesti.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikkaluokat

Sovelluksen *domain*-pakkauksesta löytyviä sovelluslogiikkaluokkia *Ball* ja *Paddle* testataan *BallTest* ja *PaddleTest* luokissa. Varsinaisessa sovelluksessa käyttöliittymän pelinäkymästä vastaava luokka *PongUI* kutsuu em. luokkien metodeja ja piirtää pelikuvaa. Testeissä luokkien toimintaa joudutaan simuloimaan hieman yksinkertaistettuna, koska pallo ja mailat eivät liiku reaaliajassa ilman käyttöliittymän tarjoamaa toiminnallisuutta. Useat pallon metodit tarvitsevat toimiakseen myös pelissä käytettäviä mailoja, minkä vuoksi pallon testeihin liittyy paljon integraatiotestausta. Esimerkiksi pallon ja mailan törmäystarkistus on testattu asettamalla ko. olioiden koordinaatit päällekkäin, sillä liikkuvan pallon simulointi ilman käyttöliittymää osoittautui liian hankalaksi.

Sovelluksen *dao*-pakkauksen luokkia käyttävä *ScoreService*-luokka on testattu antamalla sen käyttöön tilapäistä testitietokantaa käyttävä *SQLScoreDAO*-olio.

### DAO-luokka

Sovelluksen *dao*-pakkauksesta löytyvää *SQLScoreDAO*:a on testattu luokassa *SQLScoreDAOTest*. Testausta varten luodaan väliaikainen, testien päätteeksi poistettava, kansio tietokantatiedostoa varten JUnitin tarjoaman TemporaryFolder-luokan avulla.

### Testikattavuus

Sovelluksen testauksessa on saavutettu 86% rivikattavuus ja 79% haarautumakattavuus. Testikattavuusraportin ulkopuolelle on jätetty käyttöliittymän muodostavat luokat. Yksityiskohtaisempaa tarkastelua varten testikattavuusraportin voi luoda komennolla ```mvn test jacoco:report```

![testikattavuus](kuvat/testikattavuus.png)

Testien ulkopuolelle jääneet rivit ja haaraumat koostuvat pääasiassa pallon liikkumiseen liittyvistä toiminnallisuuksista. En kokenut mielekkääksi testata niitä kaikkia, sillä pallon sijainti pitäisi näissä tilanteissa manuaalisesti ensin asettaa ja sitten tarkistaa.

## Järjestelmätestaus

Sovelluksen käyttöliittymän kautta tehtävä järjestelmätestaus on suoritettu käsin.

### Asennus ja konfigurointi

Sovelluksen asentamista on testattu lataamalla jar-tiedosto noudattaen [käyttöohjetta](dokumentointi/kayttoohje.md). Sovelluksen jar-tiedosto on luotu ja sitä on testattu ainoastaan Windows-ympäristössä käyttäen javan versiota 1.8.0_201.

Sovelluksen käyttämä tiedon pysyväistallennusominaisuus toimii vain, jos sovelluksen suoritushakemistossa on käyttöohjeessa kuvattu *config.properties*-tiedosto. Ilman kyseistä tiedostoa sovellus vaikuttaa toimivan muuten normaalisti, mutta aikarajoitettujen pelien loppupisteitä ei tallenneta mihinkään eikä aikaisempia pisteitä myöskään näy High-Scores -näkymässä.