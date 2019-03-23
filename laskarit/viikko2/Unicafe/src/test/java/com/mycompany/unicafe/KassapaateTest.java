package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(480);
    }

    @Test
    public void luotuKassapaateOlemassa() {
        assertTrue(kassa != null);
    }

    @Test
    public void kassanRahamaaraAlussaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myytyjenLounaidenMaaraAlussaOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateismaksuToimiiEdullistenLounaidenOsalta() {
        // edullisen lounaan hinta 2.40, käteismaksu 5.00
        assertEquals(260, kassa.syoEdullisesti(500));
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        // käteismaksu 2.00
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        // käteismaksu 2.40 (tasaraha)
        assertEquals(0, kassa.syoEdullisesti(240));
        assertEquals(100480, kassa.kassassaRahaa());
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateismaksuToimiiMaukkaidenLounaidenOsalta() {
        // maukkaan lounaan hinta 4.00, käteismaksu 5.00
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        // käteismaksu 3.00
        assertEquals(300, kassa.syoMaukkaasti(300));
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        // käteismaksu 4.00 (tasaraha)
        assertEquals(0, kassa.syoMaukkaasti(400));
        assertEquals(100800, kassa.kassassaRahaa());
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttimaksuToimiiEdullistenLounaidenOsalta() {
        // edullisen lounaan hinta 2.40, kortilla 4.80
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(240, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        // kortilla jäljellä 2.40 (tasaraha)
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(0, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
        // kortilla jäljellä 0.0
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(0, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttimaksuToimiiMaukkaidenLounaidenOsalta() {
        // maukkaan lounaan hinta 4.00, kortilla 4.80
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(80, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        // kortilla jäljellä 0.8
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(80, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        // ladataan kortille 3.20
        kassa.lataaRahaaKortille(kortti, 320);
        // kortilla tasan 4.0
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(0, kortti.saldo());
        assertEquals(100320, kassa.kassassaRahaa());
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void rahanLataaminenKortilleMuuttaaKortinJaKassanSaldojaOikein(){
        kassa.lataaRahaaKortille(kortti, 520);
        assertEquals(100520, kassa.kassassaRahaa());
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void negatiivisenSummanLataaminenKortilleEiOnnistu(){
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(480, kortti.saldo());
    }

}
