package org.example.readers;

import org.example.models.Wood;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class WoodReader {
    @Value("${wood.file:}")
    private String filePath;

    public List<Wood> readWood(){
        try (InputStream input = openInputStream()) {
            var factory = SAXParserFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

            List<Wood> woods = new ArrayList<>();

            factory.newSAXParser().parse(input, new DefaultHandler() {
                private String currentElement;
                private String species;
                private String type;
                private String origin;
                private String color;
                private String stock;
                private final StringBuilder value = new StringBuilder();

                @Override
                public void startElement(
                        String uri, String localName, String qName, Attributes attributes

                ) {
                    currentElement = qName;
                    value.setLength(0);
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    value.append(ch, start, length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    String text = value.toString().trim();

                    switch (qName) {
                        case "Species" -> species = text;
                        case "Type" -> type = text;
                        case "Origin" -> origin = text;
                        case "Color" -> color = text;
                        case "AvailableStock" -> stock = text;
                        case "Wood" -> {
                            if (species == null || type == null || origin == null
                                    || color == null || stock == null) {
                                throw new IllegalArgumentException(
                                        "Rekord Wood w XML nie zawiera wszystkich wymaganych pól"
                                );
                            }

                            woods.add(new Wood(
                                    species, type, origin, color, Double.parseDouble(stock)
                            ));

                            species = null;
                            type = null;
                            origin = null;
                            color = null;
                            stock = null;
                        }
                    }

                    currentElement = null;
                }
            });

            return woods;
        } catch (Exception exception) {
            throw new IllegalStateException("Nie udało się odczytać katalogu drewna", exception);
        }
    }

    private InputStream openInputStream() throws Exception {
        if (filePath != null && !filePath.isBlank()) {
            return Files.newInputStream(Path.of(filePath));
        }

        return new ClassPathResource("WoodCatalogue.xml").getInputStream();
    }

}
