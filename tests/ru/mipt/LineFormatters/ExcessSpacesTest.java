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
public class ExcessSpacesTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new ExcessSpacesFormatter());
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
		input.addLine(" o o  o    o      o     o  ");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine(" o o o o o o ");

		Assert.assertEquals(expected, actual);
	}
}
