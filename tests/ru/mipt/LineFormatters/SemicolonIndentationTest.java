package ru.mipt.LineFormatters;

import org.junit.After;
import org.junit.Before;
import ru.mipt.SourceFormatter;

/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class SemicolonIndentationTest
{
	SourceFormatter formatter;

	@Before
	public void setUp() throws Exception
	{
		formatter = new SourceFormatter(new SemicolonIndentationFormatter());
	}

	@After
	public void tearDown() throws Exception
	{
		formatter = null;
	}

}
