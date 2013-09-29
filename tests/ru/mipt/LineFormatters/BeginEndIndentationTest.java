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
		input.addLine("Begin Object");
		input.addLine("begin Object");
		input.addLine("ololol ");
		input.addLine("end Object");
		input.addLine("End Object");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("Begin Object");
		expected.addLine("\tbegin Object");
		expected.addLine("\t\tololol ");
		expected.addLine("\tend Object");
		expected.addLine("End Object");

		Assert.assertEquals(expected, actual);
	}

	@Test(expected = InvalidSyntaxException.class)
	public void testWrongSyntaxAtEnd() throws Exception
	{
		Source input = new Source();
		input.addLine("Begin Object");
		input.addLine("begin Object");
		input.addLine("ololol ");
		input.addLine("end Object");

		formatter.Format(input);
	}

	@Test(expected = InvalidSyntaxException.class)
	public void testWrongSyntax() throws Exception
	{
		Source input = new Source();
		input.addLine("Begin Object");
		input.addLine("end Object");
		input.addLine("End Object");

		formatter.Format(input);
	}

//	@Test
//	public void testStateMachine() throws Exception
//	{
//		Source input = new Source();
//		input.addLine("auto State PendingMatch");
//		input.addLine("{");
//		input.addLine("Begin:");
//		input.addLine("StartMatch();");
//		input.addLine("}");
//		input.addLine("lol");
//
//		Source expected = new Source();
//		expected.addLine("auto State PendingMatch");
//		expected.addLine("{");
//		expected.addLine("Begin:");
//		expected.addLine("StartMatch();");
//		expected.addLine("}");
//		expected.addLine("lol");
//
//	}
}
