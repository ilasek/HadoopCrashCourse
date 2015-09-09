/*
 * Copyright © 2015 Merck Sharp & Dohme Corp., a subsidiary of Merck & Co., Inc.
 * All rights reserved.
 */
package com.msd.gic.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ivo Lasek <ivo.lasek@merck.com> on 07/09/15.
 */
public class ParsePubmedUDF extends UDF {
    Pattern ANNOTATION_PATERN = Pattern.compile("<(Long|Short) id=(.*?)>");

    public Text evaluate(Text input) {
        Set<String> foundIds = new HashSet<String>();
        Matcher m = ANNOTATION_PATERN.matcher(input.toString());
        while (m.find()) {
            foundIds.add(m.group(2));
        }

        return new Text(foundIds.size() + "");
    }
}
