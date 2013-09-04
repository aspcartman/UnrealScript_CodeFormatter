package ru.mipt.LineFormatters;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.mipt.Source;
import ru.mipt.SourceFormatter;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class TrimTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new TrimFormatter());
	}

	@After
	public void tearDown() throws Exception
	{
		formatter = null;
	}

	@Test
	public void testExcessSpaces() throws Exception
	{
		Source input = new Source();
		input.addLine(" Ololo     lo  \t \t");
		input.addLine("     ");
		input.addLine("\t\t");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("Ololo     lo");
		expected.addLine("");
		expected.addLine("");

		Assert.assertEquals(expected, actual);
	}
}
