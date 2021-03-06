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
public class CloseBraceTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new CloseBraceFormatter());
	}

	@After
	public void tearDown() throws Exception
	{
		formatter = null;
	}

	@Test
	public void testCloseBraceOnSeperateLine() throws Exception
	{
		Source input = new Source();
		input.addLine("}o}o}}o}");
		input.addLine("}");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("}");
		expected.addLine("o");
		expected.addLine("}");
		expected.addLine("o");
		expected.addLine("}");
		expected.addLine("}");
		expected.addLine("o");
		expected.addLine("}");
		expected.addLine("}");

		Assert.assertEquals(expected, actual);
	}
}
