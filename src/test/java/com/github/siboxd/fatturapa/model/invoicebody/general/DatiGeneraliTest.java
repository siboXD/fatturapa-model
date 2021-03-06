package com.github.siboxd.fatturapa.model.invoicebody.general;

import com.github.siboxd.fatturapa.testutils.AbstractXmlSerializationTest;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Files;

import static com.github.siboxd.fatturapa.testutils.ResourceResolver.resolveResourcePath;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * A test class for {@link DatiGenerali} class
 *
 * @author Enrico
 */
class DatiGeneraliTest extends AbstractXmlSerializationTest {

    private static final String EXAMPLES_RESOURCE_FOLDER = "partial_examples/invoice_body/general_data";

    @Test
    void exampleDatiDDT_1() throws URISyntaxException {
        final String testFileName = "DatiGenerali_DatiDDT_1.xml";
        assumeTrue(Files.exists(resolveResourcePath(EXAMPLES_RESOURCE_FOLDER, testFileName)));

        final DatiDDT datiDDT1 = new DatiDDT
                .Builder("1452012", "2012-06-12")
                .riferimentoNumeroLinea(singletonList("1"))
                .build();

        final DatiDDT datiDDT2 = new DatiDDT
                .Builder("2202012", "2012-06-26")
                .riferimentoNumeroLinea(asList("2", "3"))
                .build();

        final DatiGenerali testObj = new DatiGenerali
                .Builder(createMandatoryDatiGeneraliContent())
                .datiDDT(asList(datiDDT1, datiDDT2))
                .build();

        persistAndCheck(testObj, EXAMPLES_RESOURCE_FOLDER, testFileName);
    }

    @Test
    void exampleDatiOrdineAcquisto_1() throws URISyntaxException {
        final String testFileName = "DatiGenerali_DatiOrdineAcquisto_1.xml";
        assumeTrue(Files.exists(resolveResourcePath(EXAMPLES_RESOURCE_FOLDER, testFileName)));

        final DatiDocumentiCorrelati datiOrdineAcquisto = new DatiDocumentiCorrelati
                .Builder("2012-07")
                .data("2012-03-21")
                .numItem("1")
                .build();

        final DatiGenerali testObj = new DatiGenerali
                .Builder(createMandatoryDatiGeneraliContent())
                .datiOrdineAcquisto(singletonList(datiOrdineAcquisto))
                .build();

        persistAndCheck(testObj, EXAMPLES_RESOURCE_FOLDER, testFileName);
    }

    @Test
    void exampleDatiOrdineAcquisto_2() throws URISyntaxException {
        final String testFileName = "DatiGenerali_DatiOrdineAcquisto_2.xml";
        assumeTrue(Files.exists(resolveResourcePath(EXAMPLES_RESOURCE_FOLDER, testFileName)));

        final DatiDocumentiCorrelati datiOrdineAcquisto = new DatiDocumentiCorrelati
                .Builder("2012-07")
                .data("2012-03-21")
                .numItem("1")
                .riferimentoNumeroLinea(asList("1", "2", "3"))
                .build();

        final DatiGenerali testObj = new DatiGenerali
                .Builder(createMandatoryDatiGeneraliContent())
                .datiOrdineAcquisto(singletonList(datiOrdineAcquisto))
                .build();

        persistAndCheck(testObj, EXAMPLES_RESOURCE_FOLDER, testFileName);
    }

    @Test
    void exampleDatiOrdineAcquisto_3() throws URISyntaxException {
        final String testFileName = "DatiGenerali_DatiOrdineAcquisto_3.xml";
        assumeTrue(Files.exists(resolveResourcePath(EXAMPLES_RESOURCE_FOLDER, testFileName)));

        final DatiDocumentiCorrelati datiOrdineAcquisto1 = new DatiDocumentiCorrelati
                .Builder("2012-05")
                .data("2012-03-07")
                .numItem("1")
                .codiceCIG("ABCD")
                .riferimentoNumeroLinea(asList("1", "2"))
                .build();

        final DatiDocumentiCorrelati datiOrdineAcquisto2 = new DatiDocumentiCorrelati
                .Builder("2012-06")
                .data("2012-03-14")
                .numItem("1")
                .codiceCIG("ABCD")
                .riferimentoNumeroLinea(asList("3", "4", "5"))
                .build();

        final DatiGenerali testObj = new DatiGenerali
                .Builder(createMandatoryDatiGeneraliContent())
                .datiOrdineAcquisto(asList(datiOrdineAcquisto1, datiOrdineAcquisto2))
                .build();

        persistAndCheck(testObj, EXAMPLES_RESOURCE_FOLDER, testFileName);
    }

    /**
     * Shorthand method to create common mandatory part of DatiGenerali xml document
     *
     * @return the mandatory part of DatiGenerali xml
     */
    private DatiGeneraliDocumento createMandatoryDatiGeneraliContent() {
        return new DatiGeneraliDocumento
                .Builder(TipoDocumento.TD01, "EUR", "2012-03-10", "A-2012-15")
                .build();
    }

}
