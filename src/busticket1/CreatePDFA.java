/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busticket1;

/**
 *
 * @author Aisha
 */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.StringTokenizer;
import org.apache.jempbox.xmp.XMPMetadata;
import org.apache.jempbox.xmp.pdfa.XMPSchemaPDFAId;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import sun.util.locale.StringTokenIterator;

/**
 * This is an example that creates a simple PDF/A document.
 *
 */
public class CreatePDFA {

    /**
     * Constructor.
     */
    public CreatePDFA() {
        super();
    }

    /**
     * Create a simple PDF/A document.
     *
     * This example is based on HelloWorld example.
     *
     * As it is a simple case, to conform the PDF/A norm, are added : - the font
     * used in the document - a light xmp block with only PDF identification
     * schema (the only mandatory) - an output intent
     *
     * @param file The file to write the PDF to.
     * @param message The message to write in the file.
     *
     * @throws Exception If something bad occurs
     */
    public void print(String file, String message) throws Exception {
        // the document
        PDDocument doc = null;
        try {
            doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);
            // load the font from pdfbox.jar
            InputStream fontStream = CreatePDFA.class.getResourceAsStream("/org/apache/pdfbox/resources/ttf/ArialMT.ttf");
            PDFont font = PDTrueTypeFont.loadTTF(doc, fontStream);

            // create a page with the message where needed
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            StringTokenizer strtok = new StringTokenizer(message, "\n");
            int size = strtok.countTokens();
            for (int i = 0; i < size; i++) {
                contentStream.beginText();
                contentStream.setFont(font, 12);
                contentStream.moveTextPositionByAmount(100, 700 - 20 * i);
                contentStream.drawString(strtok.nextToken());
                contentStream.endText();

            }
            Path s = java.nio.file.Paths.get("");
            PDXObjectImage image = new PDJpeg(doc, new FileInputStream("image.jpeg"));
            contentStream.drawImage(image, 100, 700 - 20 * (size) - 280);
            contentStream.saveGraphicsState();
            contentStream.close();

            PDDocumentCatalog cat = doc.getDocumentCatalog();
            PDMetadata metadata = new PDMetadata(doc);
            cat.setMetadata(metadata);
            // jempbox version
            XMPMetadata xmp = new XMPMetadata();
            XMPSchemaPDFAId pdfaid = new XMPSchemaPDFAId(xmp);
            xmp.addSchema(pdfaid);
            pdfaid.setConformance("B");
            pdfaid.setPart(1);
            pdfaid.setAbout("");
            metadata.importXMPMetadata(xmp);

            doc.save(file);

        } finally {
            if (doc != null) {
                doc.close();
            }
        }
    }
}
