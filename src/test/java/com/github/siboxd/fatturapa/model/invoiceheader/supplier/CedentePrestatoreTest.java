package com.github.siboxd.fatturapa.model.invoiceheader.supplier;

import com.github.siboxd.fatturapa.model.invoicecommon.Anagrafica;
import com.github.siboxd.fatturapa.model.invoicecommon.IdFiscale;
import com.github.siboxd.fatturapa.model.invoicecommon.Indirizzo;
import com.github.siboxd.fatturapa.testutils.AbstractXmlSerializationTest;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * A test class for {@link CedentePrestatore}
 *
 * @author Enrico
 */
class CedentePrestatoreTest extends AbstractXmlSerializationTest {

    private static final String EXAMPLES_RESOURCE_FOLDER = "partial_examples/invoice_header/supplier";

    @Test
    void exampleCedentePrestatore_1() throws URISyntaxException {
        final String testFileName = "CedentePrestatore_1.xml";
        assumeTrue(Files.exists(resolveResourcePath(EXAMPLES_RESOURCE_FOLDER, testFileName)));

        final IdFiscale idFiscale = new IdFiscale("IT", "01234567890");

        final Anagrafica anagrafica = new Anagrafica.Builder().denominazione("AlphaBeta Srl").build();

        final DatiAnagraficiCedente datiAnagrafici = new DatiAnagraficiCedente
                .Builder(idFiscale, anagrafica, RegimeFiscale.RF01)
                .codiceFiscale("98765432109")
                .build();

        final Indirizzo indirizzo = new Indirizzo.Builder(
                "Via Nazionale, 443",
                "40100",
                "Bologna",
                "IT")
                .provincia("BO")
                .build();

        final IscrizioneREA iscrizioneREA = new IscrizioneREA();
        iscrizioneREA.setUfficio("BO");
        iscrizioneREA.setNumeroREA("102030");
        iscrizioneREA.setCapitaleSociale("500000.00");
        iscrizioneREA.setSocioUnico(SocioUnico.SU);
        iscrizioneREA.setStatoLiquidazione(StatoLiquidazione.LN);

        final Contatti contatti = new Contatti.Builder()
                .telefono("051456789")
                .fax("051456799")
                .email("amministrazione@alphabeta.it")
                .build();

        final CedentePrestatore testObj = new CedentePrestatore
                .Builder(datiAnagrafici, indirizzo)
                .iscrizioneREA(iscrizioneREA)
                .contatti(contatti)
                .riferimentoAmministrazione("AD10BB")
                .build();

        persistAndCheck(testObj, EXAMPLES_RESOURCE_FOLDER, testFileName);
    }

    @Test
    void exampleCedentePrestatore_2() throws URISyntaxException {
        final String testFileName = "CedentePrestatore_2.xml";
        assumeTrue(Files.exists(resolveResourcePath(EXAMPLES_RESOURCE_FOLDER, testFileName)));

        final IdFiscale idFiscale = new IdFiscale("IT", "09988776600");

        final Anagrafica anagrafica = new Anagrafica.Builder()
                .nome("Giovanni")
                .cognome("Verdi")
                .titolo("Dott.")
                .build();

        final DatiAnagraficiCedente datiAnagrafici = new DatiAnagraficiCedente
                .Builder(idFiscale, anagrafica, RegimeFiscale.RF02)
                .codiceFiscale("VRDGNN99B99H501P")
                .alboProfessionale("Dottori Commercialisti")
                .provinciaAlbo("RM")
                .numeroIscrizioneAlbo("111222333444")
                .dataIscrizioneAlbo("2005-10-30")
                .build();

        final Indirizzo indirizzo = new Indirizzo.Builder(
                "Piazza S. Vincenzo",
                "00133",
                "Roma",
                "IT")
                .numeroCivico("12 C")
                .provincia("RM")
                .build();

        final Contatti contatti = new Contatti.Builder()
                .telefono("3381110000")
                .fax("061234567")
                .email("gverdi@abc.it")
                .build();

        final CedentePrestatore testObj = new CedentePrestatore
                .Builder(datiAnagrafici, indirizzo)
                .contatti(contatti)
                .riferimentoAmministrazione("C109033")
                .build();

        persistAndCheck(testObj, EXAMPLES_RESOURCE_FOLDER, testFileName);
    }

}
