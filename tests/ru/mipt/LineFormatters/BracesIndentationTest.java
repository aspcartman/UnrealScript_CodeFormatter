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
public class BracesIndentationTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new BracesIndentationFormatter());
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
		input.addLine("lol{");
		input.addLine("ol{ol{");
		input.addLine("o");
		input.addLine("}ol}ol ");
		input.addLine("}lol");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("lol{");
		expected.addLine("\tol{ol{");
		expected.addLine("\t\t\to");
		expected.addLine("\t}ol}ol ");
		expected.addLine("}lol");

		Assert.assertEquals(expected, actual);
	}

	@Test(expected = InvalidSyntaxException.class)
	public void testWrongSyntaxAtEnd() throws Exception
	{
		Source input = new Source();
		input.addLine("{ ");
		input.addLine("{ ");
		input.addLine("ololol ");
		input.addLine("} ");

		formatter.Format(input);
	}

	@Test(expected = InvalidSyntaxException.class)
	public void testWrongSyntax() throws Exception
	{
		Source input = new Source();
		input.addLine("{ ");
		input.addLine("ololol ");
		input.addLine("} ");
		input.addLine("} ");

		formatter.Format(input);
	}
}
