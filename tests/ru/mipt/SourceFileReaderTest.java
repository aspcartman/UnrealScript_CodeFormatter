package ru.mipt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 03.09.13
 */
public class SourceFileReaderTest
{
	SourceFileReader reader;
	@Before
	public void setUp() throws Exception
	{
		reader = new SourceFileReader("testAssets/SourceReader.testAsset");
	}

	@After
	public void tearDown() throws Exception
	{
		reader.Close();
	}

	@Test
	public void testGetSourceCode() throws Exception
	{
		Source expected = new Source();
		expected.add("Some");
		expected.add("    test");
		expected.add("\tsource");
		expected.add("");
		expected.add("  ");

		Source actual = reader.GetSourceCode();

		Assert.assertEquals(expected,actual);
	}
}
