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

	private void IsolationTestForOperator(String operator) throws Exception
	{
		Source input = inputForOperator(operator);
		Source expected = expectedForOperator(operator);

		Source actual = formatter.Format(input);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testAssignmentOperatorIsolation() throws Exception
	{
		IsolationTestForOperator("=");
	}

	@Test
	public void testEqualOperatorIsolation() throws Exception
	{
		IsolationTestForOperator("==");
	}

	@Test
	public void testNotEqualOperatorIsolation() throws Exception
	{
		IsolationTestForOperator("!=");
	}

	@Test
	public void testPlusAssignmentIsolation() throws Exception
	{
		IsolationTestForOperator("+=");
	}

	@Test
	public void testMinusAssignmentIsolation() throws Exception
	{
		IsolationTestForOperator("-=");
	}

	private Source inputForOperator(String operator)
	{
		Source input = new Source();
		input.addLine(String.format("lol%slol", operator));
		input.addLine(String.format("lol %slol", operator));
		input.addLine(String.format("lol%s lol", operator));
		input.addLine(String.format("lol %s lol", operator));
		input.addLine(String.format("lol\t%slol", operator));
		input.addLine(String.format("lol%s\tlol", operator));
		input.addLine(String.format("lol\t%slol", operator));
		input.addLine(String.format("lol\t%s\tlol", operator));
		input.addLine(String.format("lol   \t   %s     \t    lol", operator));

		return input;
	}

	private Source expectedForOperator(String operator)
	{
		Source expected = new Source();
		expected.addLine(String.format("lol %s lol", operator));
		expected.addLine(String.format("lol %s lol", operator));
		expected.addLine(String.format("lol %s lol", operator));
		expected.addLine(String.format("lol %s lol", operator));
		expected.addLine(String.format("lol %s lol", operator));
		expected.addLine(String.format("lol %s lol", operator));
		expected.addLine(String.format("lol %s lol", operator));
		expected.addLine(String.format("lol %s lol", operator));
		expected.addLine(String.format("lol %s lol", operator));
		return expected;
	}
}
