package com.ostapenko.cryptocurrency.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.ostapenko.cryptocurrency.service.TimerService.*;

@Service
public class FileService {
    @Autowired
    CryptoCurrService cryptoCurrService;

    public ByteArrayInputStream loadFile() {
        List<String> nameList = new ArrayList<>();
        nameList.add(BTC);
        nameList.add(XPR);
        nameList.add(ETH);

        final CSVFormat csvFormat = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CSVPrinter printer = new CSVPrinter(new PrintWriter(outputStream), csvFormat);) {
                for (String name : nameList) {
                    String data = name +
                            "minPrice: " + cryptoCurrService.getMinPrice(name)
                            + "maxPrice: " + cryptoCurrService.getMaxPrice(name);
                    printer.printRecord(data);
                }
                printer.flush();
                return new ByteArrayInputStream(outputStream.toByteArray());
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
