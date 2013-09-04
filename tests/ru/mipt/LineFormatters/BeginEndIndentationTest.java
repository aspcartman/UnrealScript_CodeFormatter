package ru.mipt.LineFormatters;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.mipt.InvalidSyntaxException;
import ru.mipt.Source;
import ru.mipt.SourceFormatter;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class BeginEndIndentationTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new BeginEndIndentationFormatter());
	}

	@After
	public void tearDown() throws Exception
	{
		formatter = null;
	}

	@Test
	public void testIndentation() throws Exception
	{
		Source input = new Source();
		input.addLine("Begin ");
		input.addLine("begin ");
		input.addLine("ololol ");
		input.addLine("end ");
		input.addLine("End ");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("Begin ");
		expected.addLine("\tbegin ");
		expected.addLine("\t\tololol ");
		expected.addLine("\tend ");
		expected.addLine("End ");

		Assert.assertEquals(expected, actual);
	}

	@Test(expected = InvalidSyntaxException.class)
	public void testWrongSyntaxAtEnd() throws Exception
	{
		Source input = new Source();
		input.addLine("Begin ");
		input.addLine("begin ");
		input.addLine("ololol ");
		input.addLine("end ");

		formatter.Format(input);
	}

	@Test(expected = InvalidSyntaxException.class)
	public void testWrongSyntax() throws Exception
	{
		Source input = new Source();
		input.addLine("Begin ");
		input.addLine("ololol ");
		input.addLine("end ");
		input.addLine("End ");

		formatter.Format(input);
	}
}
