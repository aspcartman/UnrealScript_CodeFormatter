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
public class DefaultPropertiesTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new DefaultPropertiesFormatter());
	}

	@After
	public void tearDown() throws Exception
	{
		formatter = null;
	}

	@Test
	public void testDefaultProperties() throws Exception
	{
		Source input = new Source();
		input.addLine("defaultproperties");
		input.addLine("defaultproperties");

		Source actual = formatter.Format(input);

		Source expected = new Source();
		expected.addLine("DefaultProperties");
		expected.addLine("DefaultProperties");

		Assert.assertEquals(expected,actual);
	}
}
