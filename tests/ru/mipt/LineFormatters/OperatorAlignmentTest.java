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
public class OperatorAlignmentTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new OperatorAlignmentFormatter());
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
		input.addLine("");
		input.addLine("someVar = lol;");
		input.addLine("someOtherVar = lol;");
		input.addLine("");
		input.addLine("someVar = lol;");
		input.addLine("s = t");
		input.addLine("smth=nothing");
		input.addLine("");


		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("");
		expected.addLine("someVar      = lol;");
		expected.addLine("someOtherVar = lol;");
		expected.addLine("");
		expected.addLine("someVar = lol;");
		expected.addLine("s       = t");
		expected.addLine("smth    =nothing");
		expected.addLine("");


		Assert.assertEquals(expected, actual);
	}
}
