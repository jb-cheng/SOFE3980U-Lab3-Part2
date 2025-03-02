package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    /*************************
     * Test return Strings
     *************************/
    // Test for adding two binary numbers and expecting the result as a string
    @Test
    public void addTest() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }

    // Test for adding two binary numbers with empty operands and expecting the result as a string
    @Test
    public void addTestEmpty() throws Exception {
        this.mvc.perform(get("/add").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // Test for AND operation between two binary numbers
    @Test
    public void andTest() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("10"));
    }

    // Test for AND operation with empty operands
    @Test
    public void andTestEmpty() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // Test for OR operation between two binary numbers
    @Test
    public void orTest() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }

    // Test for OR operation with empty operands
    @Test
    public void orTestEmpty() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    // Test for multiply operation between two binary numbers
    @Test
    public void multiplyTest() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1000110"));
    }

    // Test for multiply operation with empty operands
    @Test
    public void multiplyTestEmpty() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }


    /*************************
     * Test return JSON
     *************************/
    // Test for adding two binary numbers and expecting the result in JSON format
    @Test
    public void addTestJSON() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // Test for multiplying two binary numbers and expecting the result in JSON format
    @Test
    public void testMultiplication() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1000110))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    // Test for OR operation on two binary numbers and expecting the result in JSON format
    @Test
    public void testOr() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    // Test for AND operation on two binary numbers and expecting the result in JSON format
    @Test
    public void testAnd() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    // Test for adding two binary numbers with empty operands and expecting the result in JSON format
    @Test
    public void testAddJSONWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // Test for OR operation on two binary numbers with empty operands and expecting the result in JSON format
    @Test
    public void testOrWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    // Test for AND operation on two binary numbers with empty operands and expecting the result in JSON format
    @Test
    public void testAndWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    // Test for multiplying two binary numbers with empty operands and expecting the result in JSON format
    @Test
    public void testMultiplyWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }
}
