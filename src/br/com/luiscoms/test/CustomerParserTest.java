package br.com.luiscoms.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.luiscoms.data.Customer;
import br.com.luiscoms.parse.CustomerParser;

public class CustomerParserTest {
    BufferedReader bufferedReader;
    private CustomerParser customerParser;
    
    private String generateLine(boolean valid) {
        if (valid) {
            return "002ç2345675434544345çJosedaSilvaçRural";
        }
        return RandomStringUtils.randomAlphanumeric(Integer.parseInt(RandomStringUtils.randomNumeric(2)));
    }
    
    private BufferedReader getBuferReader() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        return bufferedReader;
    }
    
    @Before
    public void setUp() throws Exception {
        customerParser = new CustomerParser();
    }

    @After
    public void tearDown() throws Exception {
        bufferedReader = null;
        customerParser = null;
    }

    @Test
    public void shouldReturnCustomerListEmpty() throws IOException {
        bufferedReader = getBuferReader();
        when(bufferedReader.readLine())
            .thenReturn(generateLine(false))
            .thenReturn(null);

        ArrayList<Customer> customerList = customerParser.parse(bufferedReader);
        
        assertEquals(0, customerList.size());
    }
    
    @Test
    public void shouldReturnCustomerListWith5Items() throws IOException {
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

        ArrayList<Customer> customerList = customerParser.parse(bufferedReader);

        assertEquals(5, customerList.size());
    }

}
