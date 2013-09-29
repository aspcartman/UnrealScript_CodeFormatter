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
 * Date: 05.09.13
 */
public class CommentariesBlockTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new CommentariesBlockFormatter());
	}

	@After
	public void tearDown() throws Exception
	{
		formatter = null;
	}

	@Test
	public void testCommentariesIndentation() throws Exception
	{
		Source input = new Source();
		input.addLine("/*lol");
		input.addLine("*lol");
		input.addLine("*/lol");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("/*lol");
		expected.addLine(" *lol");
		expected.addLine(" */lol");

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCommentariesIgnoreOneLine() throws Exception
	{
		Source input = new Source();
		input.addLine("/**Olol ololol*/");
		input.addLine("lol");
		input.addLine("lol");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("/**Olol ololol*/");
		expected.addLine("lol");
		expected.addLine("lol");;

		Assert.assertEquals(expected, actual);
	}

	@Test (expected = InvalidSyntaxException.class)
	public void testCommentariesWrongSyntax() throws Exception
	{
		Source input = new Source();
		input.addLine("/**Olol ololol*/");
		input.addLine("*lol");
		input.addLine("*/");

		Source actual = formatter.Format(input);
	}

	@Test (expected = InvalidSyntaxException.class)
	public void testCommentariesWrongSyntaxNoEnd() throws Exception
	{
		Source input = new Source();
		input.addLine("/**Olol ololol");
		input.addLine("*lol");

		Source actual = formatter.Format(input);
	}
}
