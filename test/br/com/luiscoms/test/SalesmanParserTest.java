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

import br.com.luiscoms.data.Salesman;
import br.com.luiscoms.parse.SalesmanParser;

public class SalesmanParserTest {
    BufferedReader bufferedReader;
    private SalesmanParser salesmanParser;
    
    private String generateLine(boolean valid) {
        if (valid) {
            return "001ç1234567891234çDiegoç50000";
        }
        return RandomStringUtils.randomAlphanumeric(Integer.parseInt(RandomStringUtils.randomNumeric(2)));
    }
    
    private BufferedReader getBuferReader() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        return bufferedReader;
    }
    
    @Before
    public void setUp() throws Exception {
        salesmanParser = new SalesmanParser();
    }

    @After
    public void tearDown() throws Exception {
        bufferedReader = null;
        salesmanParser = null;
    }

    @Test
    public void shouldReturnSalesmanListEmpty() throws IOException {
        bufferedReader = getBuferReader();
        when(bufferedReader.readLine())
            .thenReturn(generateLine(false))
            .thenReturn(null);

        ArrayList<Salesman> salesmanList = salesmanParser.parse(bufferedReader);
        
        assertEquals(0, salesmanList.size());
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

        ArrayList<Salesman> salesmanList = salesmanParser.parse(bufferedReader);

        assertEquals(5, salesmanList.size());
    }

}