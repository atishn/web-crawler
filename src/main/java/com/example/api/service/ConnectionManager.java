package com.example.api.service;

import java.io.IOException;
import java.io.InputStream;

public interface ConnectionManager {

    InputStream getResponseStream(String url) throws IOException;
}
