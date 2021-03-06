package com.github.davidmoten.odata.client.generator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.davidmoten.odata.client.generator.Imports;

public class ImportsTest {

    @Test
    public void testNoClassWithParentClass() {
        Imports imports = new Imports("Something");
        assertEquals("fred.Something", imports.add("fred.Something"));
    }

    @Test
    public void test() {
        Imports imports = new Imports("Something");
        assertEquals("Boo", imports.add("fred.Boo"));
        assertEquals("jill.Boo", imports.add("jill.Boo"));
        assertEquals("Boo", imports.add("fred.Boo"));
    }

    
}
