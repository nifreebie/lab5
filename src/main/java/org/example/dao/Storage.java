package org.example.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.utils.BufferedLineReader;
import org.example.model.Product;
import org.example.utils.MyLinkedHashSet;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;


public class Storage {
    public static void save(Collection<Product> products, String fileName) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();
        String strXml;
        try {
            strXml = xmlMapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter writer = new FileWriter("src/main/java/org/example/data/"+fileName+".xml");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(strXml);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static LinkedHashSet<Product> read(String fileName) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.findAndRegisterModules();

        StringBuilder strXml = new StringBuilder();

        FileInputStream file = new FileInputStream("src/main/java/org/example/data/" + fileName);
        BufferedLineReader bufferedLineReader = new BufferedLineReader(file);
        while (bufferedLineReader.hasNextLine()) {

            strXml.append(bufferedLineReader.nextLine());
        }


        return mapper.readValue(strXml.toString(), new TypeReference<LinkedHashSet<Product>>() {
        });
    }


    public static MyLinkedHashSet<Product> readMyLinkedHashSet(String fileName) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.findAndRegisterModules();

        StringBuilder strXml = new StringBuilder();

        FileInputStream file = new FileInputStream("src/main/java/org/example/data/" + fileName);
        BufferedLineReader bufferedLineReader = new BufferedLineReader(file);
        while (bufferedLineReader.hasNextLine()) {

            strXml.append(bufferedLineReader.nextLine());
        }


        return mapper.readValue(strXml.toString(), new TypeReference<MyLinkedHashSet<Product>>() {
        });
    }

}
