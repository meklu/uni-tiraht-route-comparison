
import org.meklu.routecomparison.domain.*;
import org.meklu.routecomparison.util.Reitintutkija;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReitintutkijaTest {
    Ruudukko ruudukko;

    @Before
    public void setUp() throws Exception {
        this.ruudukko = new Ruudukko(6, 4);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void lyhytSuoraKunnossa() {
        Koordinaatti[] reitti = new Koordinaatti[4];
        reitti[0] = new Koordinaatti(0, 0);
        reitti[1] = new Koordinaatti(1, 0);
        reitti[2] = new Koordinaatti(2, 0);
        reitti[3] = new Koordinaatti(3, 0);
        Reitintutkija rt = new Reitintutkija(ruudukko, reitti);
        assertEquals(4, rt.getReitissaSolmuja());
        assertEquals(3, rt.getReitinPituus(), 0.0001);
        assertEquals(false, rt.onkoReitissaOngelmia());
    }

    @Test
    public void lyhytDiagonaaliOnKunnossa() {
        Koordinaatti[] reitti = new Koordinaatti[4];
        reitti[0] = new Koordinaatti(0, 0);
        reitti[1] = new Koordinaatti(1, 1);
        reitti[2] = new Koordinaatti(2, 2);
        reitti[3] = new Koordinaatti(3, 3);
        Reitintutkija rt = new Reitintutkija(ruudukko, reitti);
        assertEquals(4, rt.getReitissaSolmuja());
        assertEquals(Math.sqrt(3*3 + 3*3), rt.getReitinPituus(), 0.0001);
        assertEquals(false, rt.onkoReitissaOngelmia());
    }

    @Test
    public void kolotHavaitaan() {
        Koordinaatti[] reitti = new Koordinaatti[4];
        reitti[0] = new Koordinaatti(0, 0);
        reitti[1] = new Koordinaatti(2, 2);
        reitti[2] = new Koordinaatti(3, 3);
        reitti[3] = new Koordinaatti(5, 3);
        Reitintutkija rt = new Reitintutkija(ruudukko, reitti);
        assertEquals(4, rt.getReitissaSolmuja());
        assertEquals(Math.sqrt(3*3 + 3*3) + 2, rt.getReitinPituus(), 0.0001);
        assertEquals(true, rt.onkoReitissaKoloja());
        assertEquals(false, rt.onkoReittiOhiRuudukosta());
        assertEquals(false, rt.onkoReitissaTormayksia());
        assertEquals(false, rt.onkoReitissaPaallekkaisyyksia());
        assertEquals(true, rt.onkoReitissaOngelmia());
    }

    @Test
    public void ulkonaKulkeminenHavaitaan() {
        Koordinaatti[] reitti = new Koordinaatti[4];
        reitti[0] = new Koordinaatti(0, 0);
        reitti[1] = new Koordinaatti(0, -1);
        reitti[2] = new Koordinaatti(1, 0);
        reitti[3] = new Koordinaatti(1, 1);
        Reitintutkija rt = new Reitintutkija(ruudukko, reitti);
        assertEquals(4, rt.getReitissaSolmuja());
        assertEquals(Math.sqrt(2) + 2, rt.getReitinPituus(), 0.0001);
        assertEquals(false, rt.onkoReitissaKoloja());
        assertEquals(true, rt.onkoReittiOhiRuudukosta());
        assertEquals(false, rt.onkoReitissaTormayksia());
        assertEquals(false, rt.onkoReitissaPaallekkaisyyksia());
        assertEquals(true, rt.onkoReitissaOngelmia());
    }

    @Test
    public void tormaysHavaitaan() {
        this.ruudukko.asetaEste(true, 1, 1);
        Koordinaatti[] reitti = new Koordinaatti[4];
        reitti[0] = new Koordinaatti(0, 0);
        reitti[1] = new Koordinaatti(1, 1);
        reitti[2] = new Koordinaatti(2, 2);
        reitti[3] = new Koordinaatti(3, 3);
        Reitintutkija rt = new Reitintutkija(ruudukko, reitti);
        assertEquals(4, rt.getReitissaSolmuja());
        assertEquals(Math.sqrt(3*3 + 3*3), rt.getReitinPituus(), 0.0001);
        assertEquals(false, rt.onkoReitissaKoloja());
        assertEquals(false, rt.onkoReittiOhiRuudukosta());
        assertEquals(true, rt.onkoReitissaTormayksia());
        assertEquals(false, rt.onkoReitissaPaallekkaisyyksia());
        assertEquals(true, rt.onkoReitissaOngelmia());
    }

    @Test
    public void paallekkaisyysHavaitaan() {
        this.ruudukko.asetaEste(true, 1, 1);
        Koordinaatti[] reitti = new Koordinaatti[4];
        reitti[0] = new Koordinaatti(0, 0);
        reitti[1] = new Koordinaatti(1, 1);
        reitti[2] = new Koordinaatti(2, 2);
        reitti[3] = new Koordinaatti(2, 2);
        Reitintutkija rt = new Reitintutkija(ruudukko, reitti);
        assertEquals(4, rt.getReitissaSolmuja());
        assertEquals(Math.sqrt(2*2 + 2*2), rt.getReitinPituus(), 0.0001);
        assertEquals(false, rt.onkoReitissaKoloja());
        assertEquals(false, rt.onkoReittiOhiRuudukosta());
        assertEquals(true, rt.onkoReitissaTormayksia());
        assertEquals(true, rt.onkoReitissaPaallekkaisyyksia());
        assertEquals(true, rt.onkoReitissaOngelmia());
    }

    @Test
    public void nullReittiToimiiOdotetusti() {
        Reitintutkija rt = new Reitintutkija(ruudukko, null);
        assertEquals(-1, rt.getReitissaSolmuja());
        assertEquals(Double.NaN, rt.getReitinPituus(), 0.0001);
        assertEquals(false, rt.onkoReitissaKoloja());
        assertEquals(false, rt.onkoReittiOhiRuudukosta());
        assertEquals(false, rt.onkoReitissaTormayksia());
        assertEquals(false, rt.onkoReitissaPaallekkaisyyksia());
        assertEquals(false, rt.onkoReitissaOngelmia());
    }
}