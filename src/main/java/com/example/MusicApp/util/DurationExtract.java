package com.example.MusicApp.util;


import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.helpers.DefaultHandler;


public class DurationExtract {
    public double getDurationWithTika(MultipartFile file) throws Exception {
        Mp3Parser parser = new Mp3Parser();
        Metadata metadata = new Metadata();

        // Tika processes the stream directly!
        parser.parse(file.getInputStream(), new DefaultHandler(), metadata, new ParseContext());

        // Tika returns duration as a String in milliseconds
        String durationStr = metadata.get("xmpDM:duration");
        System.out.println(durationStr);
//        return durationStr != null ? Double.parseDouble(durationStr) / 1000 : 0;
        return Double.parseDouble(durationStr);
    }
}

