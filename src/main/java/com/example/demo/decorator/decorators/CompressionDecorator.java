package com.example.demo.decorator.decorators;

import com.example.demo.decorator.datasource.DataSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class CompressionDecorator extends DataSourceDecorator {
    private int compLevel = 5;

    public CompressionDecorator(DataSource wrappee) {
        super(wrappee);
    }

    public int getCompLevel() {
        return compLevel;
    }

    public void setCompLevel(int compLevel) {
        this.compLevel = compLevel;
    }

    @Override
    public void writeData(String data) {
        super.writeData(compress(data));
    }

    private String compress(String strData) {
        byte[] data = strData.getBytes();
        try (
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
            DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(compLevel));
        ){
            dos.write(data);
            return Base64.getEncoder().encodeToString(bout.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String readData() {
        return deCompress(super.readData());
    }

    private String deCompress(String strData) {
        byte[] data = Base64.getDecoder().decode(strData);
        try(
            InputStream in = new ByteArrayInputStream(data);
            InflaterInputStream iin = new InflaterInputStream(in);
            ByteArrayOutputStream bout = new ByteArrayOutputStream(512)
        ){
            int b;
            while ((b = iin.read()) != -1){
                bout.write(b);
            }

            return new String(bout.toByteArray());
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
