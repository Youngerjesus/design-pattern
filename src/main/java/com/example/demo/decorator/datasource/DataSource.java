package com.example.demo.decorator.datasource;

public interface DataSource {
    void writeData(String data);

    String readData();
}
