package com.msd.gic.hive.udf;

import junit.framework.Assert;
import org.apache.hadoop.io.Text;

import static org.junit.Assert.*;

/**
 * @author Ivo Lasek <ivo.lasek@merck.com> on 07/09/15.
 */
public class ParsePubmedUDFTest {

    @org.junit.Test
    public void testEvaluate() throws Exception {
        ParsePubmedUDF parsePubmedUDF = new ParsePubmedUDF();
        assertEquals(
                "2",
                parsePubmedUDF.evaluate(new Text("The Novel Adaptor Protein, Mti1p, and Vrp1p, a Homolog of " +
                    "<Long id=2>Wiskott-Aldrich Syndrome Protein-Interacting Protein</Long> (<Short id=2>WIP</Short>), May " +
                    "Antagonistically Regulate Type I Myosins in Saccharomyces cerevisiae. <Long id=3>Other Protein</Long>")).toString()
        );
    }
}