package com.javalord.reactapp.spring_react_app.service;

import org.springframework.stereotype.Service;

import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class FileService {

    private Deflater deflater;
    private Inflater inflater;

    public FileService() {
        deflater = new Deflater(Deflater.BEST_COMPRESSION);
        inflater = new Inflater();
    }

}
