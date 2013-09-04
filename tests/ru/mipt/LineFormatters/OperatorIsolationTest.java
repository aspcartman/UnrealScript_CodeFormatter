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
public class OperatorIsolationTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new OperatorIsolationFormatter());
	}

	@After
	public void tearDown() throws Exception
	{
		formatter = null;
	}

	@Test
	public void testOperatorAlignment() throws Exception
	{
		Source input = new Source();
		input.addLine("lol=lol");
		input.addLine("lol =lol");
		input.addLine("lol= lol");
		input.addLine("lol = lol");
		input.addLine("lol      =         lol");
		input.addLine("lol\t=lol");
		input.addLine("lol=\tlol");
		input.addLine("lol\t=lol");
		input.addLine("lol\t=\tlol");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("lol = lol");
		expected.addLine("lol = lol");
		expected.addLine("lol = lol");
		expected.addLine("lol = lol");
		expected.addLine("lol      =         lol");
		expected.addLine("lol = lol");
		expected.addLine("lol = lol");
		expected.addLine("lol = lol");
		expected.addLine("lol\t=\tlol");

		Assert.assertEquals(expected, actual);
	}
}
