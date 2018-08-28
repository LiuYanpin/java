package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.json.JSONArray;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import static jdk.nashorn.internal.runtime.regexp.joni.constants.CCVALTYPE.SB;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
public class PosMachine {
    String line = System.lineSeparator();
    private final String startString  = String.valueOf(new StringBuilder("Receipts")
            .append(line)
            .append("------------------------------------------------------------")
            .append(line));
    private final String endString = String.valueOf(new StringBuilder("------------------------------------------------------------")
            .append(line));
    private StringBuilder countString = new StringBuilder("Price: ");
    private int totalCount = 0;
    private Map<String, Product> products = new HashMap<>(20);

    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String tempString;
        JSONArray array = new JSONArray();
        while ((tempString = bufferedReader.readLine()) != null) {
            tempString.trim();
            System.out.println(tempString.trim().substring(1, tempString.trim().length()-2));
            if (tempString != "[\n" && tempString != "]\n") {
//                List<String> tempArray = Arrays.asList(tempString.split(","));
//                Product product = gson.fromJson(tempString.trim(), Product.class);
//                System.out.println(product.getName());
            }
        }
        System.out.println(tempString);

    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        StringBuilder stringBuilder = new StringBuilder();
        if (barcodeContent == null || barcodeContent.equals("[]")) {
            return String.valueOf(stringBuilder.append(endString).append(countString).append(totalCount).append("\n"));
        }
        return "";
        // --end-->
    }
}

@SuppressWarnings("unused")
class Product {
    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}