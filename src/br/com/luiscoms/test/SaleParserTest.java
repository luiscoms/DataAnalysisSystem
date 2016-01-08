package br.com.luiscoms.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.luiscoms.data.Sale;
import br.com.luiscoms.parse.SaleParser;

public class SaleParserTest {
    BufferedReader bufferedReader;
    private SaleParser salesParser;
    
    private String generateLine(boolean valid) {
        if (valid) {
            return "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
        }
        return RandomStringUtils.randomAlphanumeric(Integer.parseInt(RandomStringUtils.randomNumeric(2)));
    }
    
    private BufferedReader getBuferReader() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        return bufferedReader;
    }
    
    @Before
    public void setUp() throws Exception {
        salesParser = new SaleParser();
    }

    @After
    public void tearDown() throws Exception {
        bufferedReader = null;
        salesParser = null;
    }

    @Test
    public void shouldReturnSalesListEmpty() throws IOException {
        bufferedReader = getBuferReader();
        when(bufferedReader.readLine())
            .thenReturn(generateLine(false))
            .thenReturn(null);

        ArrayList<Sale> salesList = salesParser.parse(bufferedReader);
        
        assertEquals(0, salesList.size());
    }
    
    @Test
    public void shouldReturnSalesmanListWith5Items() throws IOException {
        bufferedReader = getBuferReader();
        when(bufferedReader.readLine())
            .thenReturn(generateLine(false))
            .thenReturn(generateLine(false))
            .thenReturn(generateLine(true))
            .thenReturn(generateLine(true))
            .thenReturn(generateLine(false))
            .thenReturn(generateLine(false))
            .thenReturn(generateLine(true))
            .thenReturn(generateLine(false))
            .thenReturn(generateLine(true))
            .thenReturn(generateLine(true))
            .thenReturn(generateLine(false))
            .thenReturn(null);

        ArrayList<Sale> salesList = salesParser.parse(bufferedReader);

        assertEquals(5, salesList.size());
        assertEquals(3, salesList.get(3).getItems().size());
    }
}
