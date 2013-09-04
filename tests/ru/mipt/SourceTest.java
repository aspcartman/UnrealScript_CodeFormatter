package ru.mipt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;


/**
 * MIPT
 * Autor: aspcartman
 * Date: 04.09.13
 */
public class SourceTest
{
	String[] someCode;

	@Before
	public void setUp() throws Exception
	{
		someCode = new String[] {"Some", "strings"};
	}

	@After
	public void tearDown() throws Exception
	{
		someCode = null;
	}

	@Test
	public void testStringArrayConstructor() throws Exception
	{
		Source source = new Source(someCode);

		int count = someCode.length;
		for (int i = 0; i < count; ++ i)
		{
			Assert.assertEquals(someCode[i], source.getLine(i));
		}
	}

	@Test
	public void testClone() throws Exception
	{
		Source src1 = new Source(someCode);
		Source src2 = (Source) src1.clone();

		Assert.assertEquals(src1,src2);
		Assert.assertThat(src1,not(sameInstance(src2)));
	}
}
