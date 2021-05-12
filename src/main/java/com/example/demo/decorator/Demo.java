package com.example.demo.decorator;

import com.example.demo.decorator.datasource.DataSource;
import com.example.demo.decorator.datasource.FileDataSource;
import com.example.demo.decorator.decorators.CompressionDecorator;
import com.example.demo.decorator.decorators.DataSourceDecorator;
import com.example.demo.decorator.decorators.EncryptionDecorator;

public class Demo {
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                                          new EncryptionDecorator(
                                              new FileDataSource("out/OutputDemo.txt")));
        DataSource plain = new FileDataSource("out/OutputDemo.txt");

        encoded.writeData(salaryRecords);

        System.out.println("- Input --------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
