package com.example.MusicApp.util;


import org.springframework.web.multipart.MultipartFile;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;

import java.io.InputStream;

public class DurationExtract {
    public int getDurationInSecond(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            AutoDetectParser parser = new AutoDetectParser();
            ParseContext context = new ParseContext();

            parser.parse(is, handler, metadata, context);

            // Tika extracts duration in milliseconds
            String durationStr = metadata.get("xmpDM:duration");

            if (durationStr != null) {
                double milliseconds = Double.parseDouble(durationStr);
                return (int) (milliseconds / 1000); // Convert to seconds
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Fallback if duration can't be read

    }
}
