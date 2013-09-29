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
 * Date: 29.09.13
 */
public class LabelFormatterTest
{
	private SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new BracesIndentationFormatter(),new LabelFormatter());
	}

	@After
	public void tearDown() throws Exception
	{
		formatter = null;
	}

	@Test
	public void testStateMachine() throws Exception
	{
		Source input = new Source();
		input.addLine("{");
		input.addLine("label1:");
		input.addLine("lol");
		input.addLine("{");
		input.addLine("lol");
		input.addLine("}");
		input.addLine("lol");
		input.addLine("}");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("{");
		expected.addLine("label1:");
		expected.addLine("\tlol");
		expected.addLine("\t{");
		expected.addLine("\t\tlol");
		expected.addLine("\t}");
		expected.addLine("\tlol");
		expected.addLine("}");

		Assert.assertEquals(expected, actual);

	}
}
