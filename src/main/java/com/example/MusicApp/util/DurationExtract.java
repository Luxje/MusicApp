package com.example.MusicApp.util;


import org.springframework.web.multipart.MultipartFile;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.Map;

public class DurationExtract {

    public int getDurationInSecond(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             // AudioSystem requires a marked/reset-able stream for MP3s
             InputStream bufferedIs = new BufferedInputStream(is)) {

            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(bufferedIs);

            if (fileFormat instanceof TAudioFileFormat) {
                Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
                Long microseconds = (Long) properties.get("duration");

                if (microseconds != null) {
                    System.out.println(microseconds / 1_000_000);
                    return (int) (microseconds / 1_000_000); // Direct to seconds
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }}
